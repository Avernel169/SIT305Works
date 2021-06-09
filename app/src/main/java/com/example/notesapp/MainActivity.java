package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button createNoteButton;
    Button showAllNotesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNoteButton = findViewById(R.id.createNotes);
        showAllNotesButton = findViewById(R.id.showAllNotes);

        createNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createNoteIntent = new Intent(MainActivity.this, createNote.class);
                startActivity(createNoteIntent);
            }
        });

        showAllNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showAllNotesIntent = new Intent(MainActivity.this, NotesList.class);
                startActivity(showAllNotesIntent);
            }
        });

    }

}