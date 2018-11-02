package au.edu.tafesa.itstudies.personal_timetable_android.models;

import java.util.Date;

public class Session {

    private int sessionID;
    private int sessionNo;
    private String topic;
    private Date startTime;
    private Date endTime;
    private String room;
    private Date date;
    private int classID;

    public Session() {
    }

    public Session(int sessionID, int sessionNo, String topic, Date startTime,Date endTime, String room, Date date, int classID) {
        this.sessionID = sessionID;
        this.sessionNo = sessionNo;
        this.topic = topic;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.date = date;
        this.classID = classID;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public int getSessionNo() {
        return sessionNo;
    }

    public void setSessionNo(int sessionNo) {
        this.sessionNo = sessionNo;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionID=" + sessionID +
                ", sessionNo=" + sessionNo +
                ", topic='" + topic + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", room='" + room + '\'' +
                ", Date=" + date +
                ", classID=" + classID +
                '}';
    }
}
