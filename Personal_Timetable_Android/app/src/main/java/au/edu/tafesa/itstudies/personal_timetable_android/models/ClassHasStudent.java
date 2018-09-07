package au.edu.tafesa.itstudies.personal_timetable_android.models;

public class ClassHasStudent {

    private int classID;
    private int subjectID;
    private int studentID;

    public ClassHasStudent() {
    }

    public ClassHasStudent(int classID, int subjectID, int studentID) {
        this.classID = classID;
        this.subjectID = subjectID;
        this.studentID = studentID;
    }

    public int getclassID() {
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
                ", subjectID=" + subjectID +
                ", studentID=" + studentID +
                '}';
    }
}
