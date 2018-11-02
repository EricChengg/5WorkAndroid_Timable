package au.edu.tafesa.itstudies.personal_timetable_android.models;

import java.util.Date;

public class Assessment {

    private int assessmentID;
    private String name;
    private Date dueDate;
    private String type;
    private int classID;

    public Assessment() {
    }

    public Assessment(int assessmentID, String name, Date dueDate, String type, int classID) {
        this.assessmentID = assessmentID;
        this.name = name;
        this.dueDate = dueDate;
        this.type = type;
        this.classID = classID;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }
}
