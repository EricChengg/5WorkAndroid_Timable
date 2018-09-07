package au.edu.tafesa.itstudies.personal_timetable_android.models;

import java.util.Date;

public class Assignment {
    private static final int DEFAULT_ID = -1;
    private static final String DEFAULT_NAME = "None";
    private static final String DEFAULT_DUEDATE = null;
    private static final String DEFAULT_COURSEID = "None";

    private int assignmentID;
    private String assignmentName;
    private String dueDate;
    private Class theClass;

    public Assignment()
    {
        this.assignmentID = DEFAULT_ID;
        this.assignmentName = DEFAULT_NAME;
        this.dueDate = DEFAULT_DUEDATE;
    }

    public Assignment(int assignmentID, String assignmentName, String dueDate, Class theClass) {
        this.assignmentID = assignmentID;
        this.assignmentName = assignmentName;
        this.dueDate = dueDate;
        this.theClass = theClass;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Class getTheClass() {
        return theClass;
    }

    public void setTheClass(Class theClass) {
        this.theClass = theClass;
    }
}
