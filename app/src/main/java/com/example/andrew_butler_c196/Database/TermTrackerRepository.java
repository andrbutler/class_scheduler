package com.example.andrew_butler_c196.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.andrew_butler_c196.DAO.AssessmentDAO;
import com.example.andrew_butler_c196.DAO.CourseDAO;
import com.example.andrew_butler_c196.DAO.NoteDAO;
import com.example.andrew_butler_c196.DAO.TermDAO;
import com.example.andrew_butler_c196.Entities.AssessmentEntity;
import com.example.andrew_butler_c196.Entities.CourseEntity;
import com.example.andrew_butler_c196.Entities.NoteEntity;
import com.example.andrew_butler_c196.Entities.TermEntity;

import java.util.List;

public class TermTrackerRepository {
    private AssessmentDAO assessmentDAO;
    private CourseDAO courseDAO;
    private NoteDAO noteDAO;
    private TermDAO termDAO;
    private LiveData<List<TermEntity>> allTerms;
    private LiveData<List<CourseEntity>> allCourses;
    private LiveData<List<CourseEntity>> associatedCourses;
    private LiveData<List<AssessmentEntity>> allAssessments;
    private LiveData<List<AssessmentEntity>> associatedAssessments;
    private LiveData<List<NoteEntity>> allNotes;
    private LiveData<List<NoteEntity>> associatedNotes;
    private int termId;
    private int courseId;

    public TermTrackerRepository(Application application) {
        TermTrackerDatabase db = TermTrackerDatabase.getDatabase(application);
        assessmentDAO = db.assessmentDAO();
        courseDAO = db.courseDAO();
        noteDAO = db.noteDAO();
        termDAO = db.termDAO();
        allTerms = termDAO.getAllTerms();
        allCourses = courseDAO.getAllCourses();
        allNotes = noteDAO.getAllNotes();
        allAssessments = assessmentDAO.getAllAssessments();
        associatedCourses = courseDAO.getAsscociatedCourses(termId);
        associatedAssessments = assessmentDAO.getAsscociatedAssessments(courseId);
        associatedNotes = noteDAO.getAsscociatedNotes(courseId);
    }

    public LiveData<List<TermEntity>> getAllTerms() {
        return allTerms;
    }

    public LiveData<List<CourseEntity>> getAllCourses() {
        return allCourses;
    }

    public LiveData<List<CourseEntity>> getAssociatedCourses(int termId) {
        return associatedCourses;
    }

    public LiveData<List<AssessmentEntity>> getAllAssessments() {
        return allAssessments;
    }

    public LiveData<List<AssessmentEntity>> getAssociatedAssessments(int courseId) {
        return associatedAssessments;
    }

    public LiveData<List<NoteEntity>> getAllNotes() {
        return allNotes;
    }

    public LiveData<List<NoteEntity>> getAssociatedNotes(int courseId) {
        return associatedNotes;
    }

    public void insert(TermEntity termEntity) {
        new insertAsyncTask1(termDAO).execute(termEntity);
    }

    private static class insertAsyncTask1 extends AsyncTask<TermEntity, Void, Void> {

        private TermDAO asyncTaskDao;

        insertAsyncTask1(TermDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TermEntity... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void delete(TermEntity termEntity) {
        new deleteAsyncTask1(termDAO).execute(termEntity);
    }

    private static class deleteAsyncTask1 extends AsyncTask<TermEntity, Void, Void> {

        private TermDAO asyncTaskDao;

        deleteAsyncTask1(TermDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TermEntity... params) {
            asyncTaskDao.delete(params[0]);
            return null;
        }
    }

    public void insert(CourseEntity courseEntity) {
        new insertAsyncTask2(courseDAO).execute(courseEntity);
    }

    private static class insertAsyncTask2 extends AsyncTask<CourseEntity, Void, Void> {

        private CourseDAO asyncTaskDao;

        insertAsyncTask2(CourseDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CourseEntity... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }

    }

    public void delete(CourseEntity courseEntity) {
        new deleteAsyncTask2(courseDAO).execute(courseEntity);
    }

    private static class deleteAsyncTask2 extends AsyncTask<CourseEntity, Void, Void> {

        private CourseDAO asyncTaskDao;

        deleteAsyncTask2(CourseDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CourseEntity... params) {
            asyncTaskDao.delete(params[0]);
            return null;
        }

    }


    public void insert(AssessmentEntity assessmentEntity) {
        new insertAsyncTask3(assessmentDAO).execute(assessmentEntity);
    }

    private static class insertAsyncTask3 extends AsyncTask<AssessmentEntity, Void, Void> {

        private AssessmentDAO asyncTaskDao;

        insertAsyncTask3(AssessmentDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AssessmentEntity... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }

    }


    public void delete(AssessmentEntity assessmentEntity) {
        new deleteAsyncTask3(assessmentDAO).execute(assessmentEntity);
    }

    private static class deleteAsyncTask3 extends AsyncTask<AssessmentEntity, Void, Void> {

        private AssessmentDAO asyncTaskDao;

        deleteAsyncTask3(AssessmentDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AssessmentEntity... params) {
            asyncTaskDao.delete(params[0]);
            return null;
        }

    }

    public void insert(NoteEntity noteEntity) {
        new insertAsyncTask4(noteDAO).execute(noteEntity);
    }

    private static class insertAsyncTask4 extends AsyncTask<NoteEntity, Void, Void> {

        private NoteDAO asyncTaskDao;

        insertAsyncTask4(NoteDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final NoteEntity... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }

    }

    public void delete(NoteEntity noteEntity) {
        new deleteAsyncTask4(noteDAO).execute(noteEntity);
    }

    private static class deleteAsyncTask4 extends AsyncTask<NoteEntity, Void, Void> {

        private NoteDAO asyncTaskDao;

        deleteAsyncTask4(NoteDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final NoteEntity... params) {
            asyncTaskDao.delete(params[0]);
            return null;
        }

    }

}
