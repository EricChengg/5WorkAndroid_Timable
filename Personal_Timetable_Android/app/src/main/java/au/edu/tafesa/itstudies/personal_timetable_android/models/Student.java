package au.edu.tafesa.itstudies.personal_timetable_android.models;

public class Student {
    private int studentID;
    private String name;
    private String loginPassword;

    public Student(int studentID, String name, String loginPassword) {
        this.studentID = studentID;
        this.name = name;
        this.loginPassword = loginPassword;
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
}
