package au.edu.tafesa.itstudies.personal_timetable_android.models;

import java.util.Date;

public class Test {

    private static final int DEFAULT_ID = -1;
    private static final String DEFAULT_NAME = "None";
    private static final String DEFAULT_DATE = null;

    private int testID;
    private String testName;
    private String testDate;
    private Course course;

    public Test()
    {
        this.testID = DEFAULT_ID;
        this.testName = DEFAULT_NAME;
        this.testDate = DEFAULT_DATE;
    }

    public Test(int testID, String testName, String testDate, Course course) {
        this.testID = testID;
        this.testName = testName;
        this.testDate = testDate;
        this.course = course;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
