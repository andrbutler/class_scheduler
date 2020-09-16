package com.example.andrew_butler_c196.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Entity(tableName="assessment_table")

public class AssessmentEntity {
    @PrimaryKey(autoGenerate = true)
    private int assessmentId;

    private int courseId;
    private String assessmentType;
    private String assessmentTitle;
    private String dueDate;
    private String expectedCompletionDate;
    private String assessmentStartDate;

    public AssessmentEntity(int courseId, String assessmentType, String assessmentTitle, String dueDate, String expectedCompletionDate, String assessmentStartDate) {
        this.courseId = courseId;
        this.assessmentType = assessmentType;
        this.assessmentTitle = assessmentTitle;
        this.dueDate = dueDate;
        this.assessmentStartDate = assessmentStartDate;
        this.expectedCompletionDate = expectedCompletionDate;
    }

    public String getExpectedCompletionDate() {
        return expectedCompletionDate;
    }

    public void setExpectedCompletionDate(String expectedCompletionDate) {
        this.expectedCompletionDate = expectedCompletionDate;
    }

    public String getAssessmentStartDate() {
        return assessmentStartDate;
    }

    public void setAssessmentStartDate(String assessmentStartDate) {
        this.assessmentStartDate = assessmentStartDate;
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public String getDueDate() {
        return dueDate;
    }


    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
