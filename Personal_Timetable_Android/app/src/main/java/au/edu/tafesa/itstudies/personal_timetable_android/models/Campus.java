package au.edu.tafesa.itstudies.personal_timetable_android.models;

import java.util.List;

public class Campus{
    private int campusID;
    private String campusName;
    private String address;

    public Campus(int campusID, String campusName, String address) {
        this.campusID = campusID;
        this.campusName = campusName;
        this.address = address;
    }

    public int getCampusID() {
        return campusID;
    }

    public void setCampusID(int campusID) {
        this.campusID = campusID;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
