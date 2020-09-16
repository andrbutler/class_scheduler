package com.example.andrew_butler_c196.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.andrew_butler_c196.Database.TermTrackerRepository;
import com.example.andrew_butler_c196.Entities.CourseEntity;
import com.example.andrew_butler_c196.Entities.TermEntity;

import java.util.List;

public class TermViewModel extends AndroidViewModel {
    private TermTrackerRepository repository;
    private LiveData<List<TermEntity>> allTerms;

    public TermViewModel(Application application) {
        super(application);
        repository = new TermTrackerRepository(application);
        allTerms = repository.getAllTerms();
    }

    public LiveData<List<TermEntity>> getAllTerms() {
        return allTerms;
    }


    public void insert(TermEntity termEntity) {
        repository.insert(termEntity);
    }

    public void delete(TermEntity termEntity) {
        repository.delete(termEntity);
    }
}
