package com.example.andrew_butler_c196.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName="course_table")

public class CourseEntity {
   @PrimaryKey(autoGenerate = true)
   private int courseId;

   private String courseName;
   private int termId;
   private String startDate;
   private String endDate;
   private String mentorName;
   private String mentorEmail;
   private String mentorPhone;
   private String courseStatus;

    public CourseEntity(int termId, String courseName, String startDate, String endDate, String mentorName, String mentorEmail, String mentorPhone, String courseStatus) {
        this.termId = termId;
        this.courseName = courseName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mentorName = mentorName;
        this.mentorEmail = mentorEmail;
        this.mentorPhone = mentorPhone;
        this.courseStatus = courseStatus;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTermId(){
        return termId;
    }

    public void setTermId(int termId){
        this.termId = termId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStartDate() { return startDate; }


    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }


    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    public String getMentorPhone() {
        return mentorPhone;
    }

    public void setMentorPhone(String mentorPhone) {
        this.mentorPhone = mentorPhone;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }
}
