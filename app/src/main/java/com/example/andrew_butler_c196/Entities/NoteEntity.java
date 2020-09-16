package com.example.andrew_butler_c196.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="note_table")

public class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    private int noteId;

    private int courseId;
    private String noteContent;

    public NoteEntity(int courseId, String noteContent) {
        this.noteId = noteId;
        this.courseId = courseId;
        this.noteContent = noteContent;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}
