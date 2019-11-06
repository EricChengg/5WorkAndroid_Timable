package au.edu.tafesa.itstudies.personal_timetable_android.models;

public class Class {
    private int classID;
    private int subjectID;
    private int lecturerID;
    private int CampusID;

    public Class() {
    }

    public Class(int classID, int subjectID, int lecturerID, int campusID) {
        this.classID = classID;
        this.subjectID = subjectID;
        this.lecturerID = lecturerID;
        CampusID = campusID;
    }

    public int getClassID() {
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

    public int getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(int lecturerID) {
        this.lecturerID = lecturerID;
    }

    public int getCampusID() {
        return CampusID;
    }

    public void setCampusID(int campusID) {
        CampusID = campusID;
    }

    @Override
    public String toString() {
        return "Class{" +
                "classID=" + classID +
                ", subjectID=" + subjectID +
                ", lecturerID=" + lecturerID +
                ", CampusID=" + CampusID +
                '}';
    }
}


//  select TableA.name form TableA
//  lift join TableA on TableB.TableA_id = TableA.id
//  Order by TableA.id;

//  select TableA.name form TableA
//  Right join TableA on TableA.id = TableB.TableA_id
//  Order by TableA.id;

//  select TableA.name form TableA
//  where TableA.id == TableB.TableA_id
//  Order by TableA.id;



