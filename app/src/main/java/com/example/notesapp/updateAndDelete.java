package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.Util.Util;
import com.example.notesapp.data.DatebaseHelper;
import com.example.notesapp.model.RecordNotes;

public class updateAndDelete extends AppCompatActivity {
    Button updateButton;
    Button deleteButton;
    EditText editText;
    private RecordNotes selectedNote;
    String content;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete);

        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);
        editText = findViewById(R.id.editNote);
        DatebaseHelper db = new DatebaseHelper(updateAndDelete.this);

        Intent getContentIntent = getIntent();

        String noteContent = getContentIntent.getStringExtra(Util.NOTE_CONTENT);

        Integer noteID = getContentIntent.getIntExtra(Util.NOTE_ID,0);

        editText.setText(noteContent);

        //create update button click event
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = editText.getText().toString();
                id = noteID;

                int updateIndex = db.updateNote(new RecordNotes(id, content));
                if (updateIndex > 0) {
                    Toast.makeText(updateAndDelete.this, "Notes Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(updateAndDelete.this, "Failed Updating the note", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(updateAndDelete.this,NotesList.class);
                startActivity(intent);
                finish();


            }
        });

        //create delete button click event
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = editText.getText().toString();
                id = noteID;

                int deleteIndex = db.deleteNote(new RecordNotes(id, content));
                if (deleteIndex > 0) {
                    Toast.makeText(updateAndDelete.this, "Note Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(updateAndDelete.this, "Failed to Delete the note", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(updateAndDelete.this,NotesList.class);
                startActivity(intent);
                finish();

            }
        });

    }

}