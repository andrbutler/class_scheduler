package com.example.andrew_butler_c196;

import android.content.Intent;
import android.os.Bundle;

import com.example.andrew_butler_c196.Entities.NoteEntity;
import com.example.andrew_butler_c196.UI.NoteAdapter;
import com.example.andrew_butler_c196.ViewModel.NoteViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private NoteViewModel noteViewModel;
    private EditText inputNote;
    private int position;
    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        courseId = getIntent().getIntExtra("courseId", 0);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        inputNote = findViewById(R.id.InputNote);

        int courseId = getIntent().getIntExtra("courseId", 0);


        RecyclerView recyclerView = findViewById(R.id.NoteList);
        final NoteAdapter adapter = new NoteAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(@Nullable final List<NoteEntity> words) {
                List<NoteEntity> filteredWords = new ArrayList<>();
                for (NoteEntity n : words) {
                    if (n.getCourseId() == getIntent().getIntExtra("courseId", 0)) {
                        filteredWords.add(n);
                    }
                    adapter.setWords(filteredWords);
                }
            }
        });


        final Button button = findViewById(R.id.SaveButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent replyIntent = new Intent();
                String text = inputNote.getText().toString();
               replyIntent.putExtra("courseId", courseId);
               replyIntent.putExtra("noteText", text);

                NoteEntity note = new NoteEntity(courseId, text);
                replyIntent.putExtra("noteId", note.getNoteId());
                noteViewModel.insert(note);

                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            NoteEntity note = new NoteEntity(
                    data.getIntExtra("courseId", 0),
                    data.getStringExtra("noteText"));
        }
    }
}