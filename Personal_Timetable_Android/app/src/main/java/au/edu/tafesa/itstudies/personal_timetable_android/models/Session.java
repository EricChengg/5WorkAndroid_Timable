package au.edu.tafesa.itstudies.personal_timetable_android.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Session {

    private int sessionID;
    private String topic;
    private Date startTime;
    private Date endTime;
    private Date Date;
    private String room;
    private int classID;
    private int subjectID;

    public Session() {
    }

    public Session(int sessionID, String topic, java.util.Date startTime, java.util.Date endTime, java.util.Date date, String room) {
        this.sessionID = sessionID;
        this.topic = topic;
        this.startTime = startTime;
        this.endTime = endTime;
        Date = date;
        this.room = room;
    }

    public Session(int sessionID, String topic, java.util.Date startTime, java.util.Date endTime, java.util.Date date, String room, int classID, int subjectID) {
        this.sessionID = sessionID;
        this.topic = topic;
        this.startTime = startTime;
        this.endTime = endTime;
        Date = date;
        this.room = room;
        this.classID = classID;
        this.subjectID = subjectID;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public java.util.Date getStartTime() {
        return startTime;
    }

    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    public java.util.Date getEndTime() {
        return endTime;
    }

    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }
}
