package au.edu.tafesa.itstudies.personal_timetable_android.models;

public class ClassHasStudent {

    private int classID;
    private int studentID;

    public ClassHasStudent() {
    }

    public ClassHasStudent(int classID, int studentID) {
        this.classID = classID;
        this.studentID = studentID;
    }

    public int getclassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }


    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return "ClassHasStudent{" +
                "classID=" + classID +
                ", studentID=" + studentID +
                '}';
    }
}
