package au.edu.tafesa.itstudies.personal_timetable_android.models;

public class Lecture {

    private int lecturerID;
    private String lecturerName;

    public Lecture(){

    }

    public Lecture(int lecturerID, String lecturerName) {
        this.lecturerID = lecturerID;
        this.lecturerName = lecturerName;
    }

    public int getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(int lecturerID) {
        this.lecturerID = lecturerID;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "lecturerID=" + lecturerID +
                ", lecturerName='" + lecturerName + '\'' +
                '}';
    }
}
