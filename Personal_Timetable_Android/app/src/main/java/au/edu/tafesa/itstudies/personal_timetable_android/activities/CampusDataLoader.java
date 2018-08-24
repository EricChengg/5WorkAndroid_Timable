package au.edu.tafesa.itstudies.personal_timetable_android.activities;

import java.util.ArrayList;
import java.util.List;


import au.edu.tafesa.itstudies.personal_timetable_android.models.Campus;

public class CampusDataLoader {

    private List<Campus> c = new ArrayList<Campus>();

    public void Save(Campus newCampus){
        c.add(newCampus);
    }

    public void deleteById(int  id){
        for(int i = 0; i< c.size();i++){
            Campus campus = c.get(i);
            if(campus.getCampusID() == id){
                c.remove(i);
                break;
            }
        }
    }

    public Campus getById(int  id){
        for(int i = 0; i< c.size();i++){
            Campus campus = c.get(i);
            if(campus.getCampusID() == id){
                return c.get(i);
            }
        }
        return null;
    }



    public List<Campus> getAllCampuses(){
        return c;
    }
}
