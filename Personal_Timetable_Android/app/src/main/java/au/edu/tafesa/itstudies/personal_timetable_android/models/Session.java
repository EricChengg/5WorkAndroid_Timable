package au.edu.tafesa.itstudies.personal_timetable_android.models;

import java.util.Date;

public class Session {

    private int sessionID;
    private int sessionNo;
    private String subject;
    private String topic;
    private String sessionDate;
    private Course course;


    public Session(int sessionID, int sessionNo, String subject, String topic, String sessionDate, Course course) {
        this.sessionID = sessionID;
        this.sessionNo = sessionNo;
        this.subject = subject;
        this.topic = topic;
        this.sessionDate = sessionDate;
        this.course = course;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }




}
