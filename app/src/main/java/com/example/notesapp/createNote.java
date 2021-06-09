package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.notesapp.data.DatebaseHelper;
import com.example.notesapp.model.RecordNotes;

public class createNote extends AppCompatActivity {
    DatebaseHelper db;
    Button saveButton;
    EditText writeNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        writeNote = findViewById(R.id.writeNote);
        saveButton = findViewById(R.id.saveButton);
        db = new DatebaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = String.valueOf(writeNote.getText());
                int id = RecordNotes.notesArrayList.size();

                RecordNotes newNote = new RecordNotes(id, note);
                RecordNotes.notesArrayList.add(newNote);
                db.insertNote(newNote);

                Intent noteListIntent = new Intent(getApplicationContext(), NotesList.class);
                startActivity(noteListIntent);
            }
        });
    }
}