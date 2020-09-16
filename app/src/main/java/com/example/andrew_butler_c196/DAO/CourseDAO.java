package com.example.andrew_butler_c196.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.andrew_butler_c196.Entities.CourseEntity;

import java.util.List;

@Dao
public interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CourseEntity course);

    @Delete
    void delete(CourseEntity course);

    @Query("DELETE FROM course_table")
    void deleteAllCourses();

    @Query("SELECT * FROM course_table ORDER BY courseId asc")
    LiveData<List<CourseEntity>> getAllCourses();

    @Query("SELECT * FROM course_table WHERE termId = :termId ORDER BY termId asc")
    LiveData<List<CourseEntity>> getAsscociatedCourses(int termId);
}
