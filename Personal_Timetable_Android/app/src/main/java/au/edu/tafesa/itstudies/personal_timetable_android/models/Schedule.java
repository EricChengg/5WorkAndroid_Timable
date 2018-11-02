package au.edu.tafesa.itstudies.personal_timetable_android.models;

import java.util.Date;

public class Schedule {
    private int subjectID;
    private int classID;
    private int sessionID;
    private String subjectCode;
    private Date startTime;
    private Date endTime;
    private Date date;
    private String room;

    public Schedule() {
    }

    public Schedule(int subjectID, int classID, int sessionID, String subjectCode, Date startTime, Date endTime, Date date, String room) {
        this.subjectID = subjectID;
        this.classID = classID;
        this.sessionID = sessionID;
        this.subjectCode = subjectCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.room = room;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "subjectID=" + subjectID +
                ", classID=" + classID +
                ", sessionID=" + sessionID +
                ", subjectCode='" + subjectCode + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", date=" + date +
                ", room='" + room + '\'' +
                '}';
    }
}
