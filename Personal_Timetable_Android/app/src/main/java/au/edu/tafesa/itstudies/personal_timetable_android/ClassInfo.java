package au.edu.tafesa.itstudies.personal_timetable_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.method.DateTimeKeyListener;
import android.util.Log;

import java.sql.Date;
import java.sql.Time;
import java.sql.*;

public class ClassInfo {
    private String Test;
    private String subjectName; // 5TSD
    private int sessionNo; //session 1
    private String sessionContent; // Software Management Tools (SCM) & Git bash commands
    private Time StartTime; //9:00am
    private Time EndTime; // 11:00am
    private Date DateOfClass; // 12/02/2018
    private String Topics;
    // Introduction to the subject
    // Identify software management tools
    // Identify client production environment
    // Features of the selected tools
    // Introduce open source project organization structure
    // Introduce version control software – Using Git Bash commands
    // Create local repository, stage files to change and commit changes

    // All in 5TSD study schedule

    public ClassInfo()
    {}

    public ClassInfo(String subjectName, int sessionNo, String sessionContent, Time StartTime, Time EndTime, Date DateOfClass, String Topics){
        this.subjectName = subjectName;
        this.sessionNo = sessionNo;
        this.sessionContent = sessionContent;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.DateOfClass = DateOfClass;
        this.Topics = Topics;
    }

    public String getSubjectName(int i) {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSessionNo(int i) {
        return sessionNo;
    }

    public void setSessionNo(int sessionNo) {
        this.sessionNo = sessionNo;
    }

    public String getSessionContent() {
        return sessionContent;
    }

    public void setSessionContent(String sessionContent) {
        this.sessionContent = sessionContent;
    }

    public Time getStartTime(int i) {
        return StartTime;
    }

    public void setStartTime(Time startTime) {
        StartTime = startTime;
    }

    public Time getEndTime() {
        return EndTime;
    }

    public void setEndTime(Time endTime) {
        EndTime = endTime;
    }

    public Date getDateOfClass() {
        return DateOfClass;
    }

    public void setDateOfClass(Date dateOfClass) {
        DateOfClass = dateOfClass;
    }

    public String getTopics() {
        return Topics;
    }

    public void setTopics(String topics) {
        Topics = topics;
    }


    //Testing




}

