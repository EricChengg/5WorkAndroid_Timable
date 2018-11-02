package au.edu.tafesa.itstudies.personal_timetable_android.models;

public class Campus{
    private int campusID;
    private String campusName;
    private String campusCode;
    private String address;

    public Campus() {
    }

    public Campus(int campusID, String campusName, String campusCode, String address) {
        this.campusID = campusID;
        this.campusName = campusName;
        this.campusCode = campusCode;
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

    public String getCampusCode() {
        return campusCode;
    }

    public void setCampusCode(String campusCode) {
        this.campusCode = campusCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Campus{" +
                "campusID=" + campusID +
                ", campusName='" + campusName + '\'' +
                ", campusCode='" + campusCode + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}


