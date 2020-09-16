package com.example.andrew_butler_c196.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.andrew_butler_c196.Entities.NoteEntity;

import java.util.List;

@Dao
public interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NoteEntity note);

    @Delete
    void delete(NoteEntity note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY noteId asc")
    LiveData<List<NoteEntity>> getAllNotes();

    @Query("SELECT * FROM note_table WHERE courseId = :courseId ORDER BY noteId asc")
    LiveData<List<NoteEntity>> getAsscociatedNotes(int courseId);
}
