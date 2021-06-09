package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.notesapp.Util.Util;
import com.example.notesapp.data.DatebaseHelper;
import com.example.notesapp.model.RecordNotes;

import java.util.ArrayList;
import java.util.List;

public class NotesList extends AppCompatActivity {
    ListView notesListView;
    ArrayList notesArrayList;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        notesListView = findViewById(R.id.listView);
        notesArrayList = new ArrayList<>();
        DatebaseHelper db = new DatebaseHelper(NotesList.this);

        List<RecordNotes> notesList = db.fetchAllNotes();
        for (RecordNotes notes : notesList) {
            notesArrayList.add(notes.getNoteContent());
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notesArrayList);
        notesListView.setAdapter(arrayAdapter);

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent updateNoteIntent = new Intent(getApplicationContext(), updateAndDelete.class);
                updateNoteIntent.putExtra(Util.NOTE_ID, notesList.get(position).getNote_id());
                updateNoteIntent.putExtra(Util.NOTE_CONTENT, notesList.get(position).getNoteContent());

                startActivityForResult(updateNoteIntent, 1);
                finish();
            }
        });

    }
}
