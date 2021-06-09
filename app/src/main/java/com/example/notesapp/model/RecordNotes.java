package com.example.notesapp.model;

import java.sql.Date;
import java.util.ArrayList;

public class RecordNotes {
    public static ArrayList<RecordNotes> notesArrayList = new ArrayList<>();
    public static String NOTE_UPDATE = "noteEdit";

    private int note_id;
    private String noteContent;

    public RecordNotes(int note_id, String noteContent)
    {
        this.note_id = note_id;
        this.noteContent = noteContent;
    }

    public RecordNotes() {

    }

    public static RecordNotes getNoteForID(int passedNoteID)
    {
        for (RecordNotes note : notesArrayList)
        {
            if (note.getNote_id() == passedNoteID)
                return  note;

        }
        return null;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}
