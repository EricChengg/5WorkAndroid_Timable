package au.edu.tafesa.itstudies.personal_timetable_android.models;

import java.util.LinkedList;
import java.util.List;

public class Student {
    private int studentID;
    private String name;
    private String loginPassword;

    private List<Course>courseList;


    public Student(int studentID, String name, String loginPassword) {
        this.studentID = studentID;
        this.name = name;
        this.loginPassword = loginPassword;
        this.courseList = new LinkedList<Course>();
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Course AddCourse(Course c)
    {
        this.courseList.add(c);
        return c;
    }
}
