package com.example.andrew_butler_c196.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.andrew_butler_c196.DAO.AssessmentDAO;
import com.example.andrew_butler_c196.DAO.CourseDAO;
import com.example.andrew_butler_c196.DAO.NoteDAO;
import com.example.andrew_butler_c196.DAO.TermDAO;
import com.example.andrew_butler_c196.Entities.AssessmentEntity;
import com.example.andrew_butler_c196.Entities.CourseEntity;
import com.example.andrew_butler_c196.Entities.NoteEntity;
import com.example.andrew_butler_c196.Entities.TermEntity;

@Database(entities = {TermEntity.class, CourseEntity.class, AssessmentEntity.class,
        NoteEntity.class}, version = 2)
public  abstract class TermTrackerDatabase extends RoomDatabase {

    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();
    public abstract NoteDAO noteDAO();


    private static volatile TermTrackerDatabase INSTANCE;

    static TermTrackerDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TermTrackerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TermTrackerDatabase.class, "term_tracker_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            //new ClearEntries(INSTANCE).execute();
        }
    };

    private static class ClearEntries extends AsyncTask<Void, Void, Void> {

        private final TermDAO termDAO;
        private final CourseDAO courseDAO;
        private final AssessmentDAO assessmentDAO;
        private final NoteDAO noteDAO;

        ClearEntries(TermTrackerDatabase db) {
            termDAO = db.termDAO();
            courseDAO = db.courseDAO();
            assessmentDAO = db.assessmentDAO();
            noteDAO = db.noteDAO();

        }

        @Override
        protected Void doInBackground(final Void... params) {
            courseDAO.deleteAllCourses();
            termDAO.deleteAllTerms();


            return null;
        }
    }

}
