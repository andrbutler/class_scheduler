package com.example.andrew_butler_c196.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.andrew_butler_c196.Database.TermTrackerRepository;
import com.example.andrew_butler_c196.Entities.NoteEntity;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    int courseId;
    private TermTrackerRepository repository;
    private LiveData<List<NoteEntity>> allNotes;
    private LiveData<List<NoteEntity>> associatedNotes;

    public NoteViewModel(Application application) {
        super(application);
        repository = new TermTrackerRepository(application);
        allNotes = repository.getAllNotes();
        associatedNotes = repository.getAssociatedNotes(courseId);
    }

    public LiveData<List<NoteEntity>> getAllNotes() {
        return allNotes;
    }

    public void insert(NoteEntity noteEntity) {
        repository.insert(noteEntity);
    }

    public void delete(NoteEntity noteEntity) {
        repository.delete(noteEntity);
    }
}
