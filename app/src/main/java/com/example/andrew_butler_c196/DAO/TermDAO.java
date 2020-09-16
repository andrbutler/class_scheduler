package com.example.andrew_butler_c196.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.andrew_butler_c196.Entities.CourseEntity;
import com.example.andrew_butler_c196.Entities.TermEntity;

import java.util.List;

@Dao
public interface TermDAO {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(TermEntity term);

        @Delete
        void delete(TermEntity term);

        @Query("DELETE FROM term_table")
        void deleteAllTerms();

        @Query("SELECT * FROM term_table ORDER BY termId asc")
        LiveData<List<TermEntity>> getAllTerms();

        @Query("SELECT * FROM course_table WHERE termId = :termId ORDER BY termId asc")
        LiveData<List<CourseEntity>> getAsscociatedCourses(int termId);
}
