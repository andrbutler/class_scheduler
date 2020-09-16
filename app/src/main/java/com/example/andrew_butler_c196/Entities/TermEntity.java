package com.example.andrew_butler_c196.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName="term_table")

public class TermEntity {
    @PrimaryKey(autoGenerate = true)
    private int termId;

    private String termName;
    private String startDate;
    private String endDate;

    public TermEntity(String termName, String startDate, String endDate) {

        this.termName = termName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() {
        return endDate;
    }


    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
