package com.example.notesapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.notesapp.Util.Util;
import com.example.notesapp.model.RecordNotes;

import java.util.ArrayList;
import java.util.List;

public class DatebaseHelper extends SQLiteOpenHelper {
    public DatebaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTE_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + Util.NOTE_CONTENT + " TEXT)";
        db.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_NOTE_TABLE = "DROP TABLE IF EXISTS";
        db.execSQL(DROP_NOTE_TABLE, new String[]{Util.TABLE_NAME});

        onCreate(db);

    }

    public long insertNote(RecordNotes note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.NOTE_CONTENT, note.getNoteContent());
        long newRowId = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
        return newRowId;
    }

    public boolean fetchNotes(String note)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.NOTE_ID}, Util.NOTE_CONTENT + "=?", new String[]{note}, null, null, null);
        int numberOfRows = cursor.getCount();
        db.close();

        if (numberOfRows > 0)
            return  true;
        else
            return false;
    }

    public List<RecordNotes> fetchAllNotes(){
        List<RecordNotes> notesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = " SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst())
        {
            do{
                RecordNotes note = new RecordNotes();
                note.setNote_id(cursor.getInt(0));
                note.setNoteContent(cursor.getString(1));

                notesList.add(note);
            }
            while(cursor.moveToNext());
        }

        return notesList;
    }

    public int updateNote(RecordNotes note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.NOTE_CONTENT, note.getNoteContent());

        return db.update(Util.TABLE_NAME, contentValues, Util.NOTE_ID + "=?", new String[]{String.valueOf(note.getNote_id())});
    }

    public int deleteNote(RecordNotes note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int id = note.getNote_id();
        db.delete(Util.TABLE_NAME, Util.NOTE_ID+"=?", new String[]{String.valueOf(id)});
        db.close();
        return id;
    }
}
