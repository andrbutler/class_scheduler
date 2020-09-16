package com.example.andrew_butler_c196.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.andrew_butler_c196.Database.TermTrackerRepository;
import com.example.andrew_butler_c196.Entities.CourseEntity;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {
    int termId;
    private TermTrackerRepository repository;
    private LiveData<List<CourseEntity>> allCourses;
    private LiveData<List<CourseEntity>> associatedCourses;

    public CourseViewModel(Application application) {
        super(application);
        repository = new TermTrackerRepository(application);
        allCourses = repository.getAllCourses();
        associatedCourses = repository.getAssociatedCourses(termId);
    }

    public LiveData<List<CourseEntity>> getAllCourses() {
        return allCourses;
    }

    public void insert(CourseEntity courseEntity) {
        repository.insert(courseEntity);
    }

    public void delete(CourseEntity courseEntity) {
        repository.delete(courseEntity);
    }

}

