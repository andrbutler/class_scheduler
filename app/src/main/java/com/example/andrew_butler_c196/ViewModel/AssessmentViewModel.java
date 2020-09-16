package com.example.andrew_butler_c196.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.andrew_butler_c196.Database.TermTrackerRepository;
import com.example.andrew_butler_c196.Entities.AssessmentEntity;

import java.util.List;

public class AssessmentViewModel extends AndroidViewModel {
    int courseId;
    private TermTrackerRepository repository;
    private LiveData<List<AssessmentEntity>> allAssessments;
    private LiveData<List<AssessmentEntity>> associatedAssessments;

    public AssessmentViewModel(Application application) {
        super(application);
        repository = new TermTrackerRepository(application);
        allAssessments = repository.getAllAssessments();
        associatedAssessments = repository.getAssociatedAssessments(courseId);
    }

    public LiveData<List<AssessmentEntity>> getAllAssessments() {
        return allAssessments;
    }

    public void insert(AssessmentEntity assessmentEntity) {
        repository.insert(assessmentEntity);
    }

    public void delete(AssessmentEntity assessmentEntity) {
        repository.delete(assessmentEntity);
    }
}