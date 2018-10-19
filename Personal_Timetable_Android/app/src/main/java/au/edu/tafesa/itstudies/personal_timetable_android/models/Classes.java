package au.edu.tafesa.itstudies.personal_timetable_android.models;

import java.util.ArrayList;
import java.util.List;

public class Classes {
    List<Class> classes = new ArrayList<Class>();

    public Classes() {
    }

    public Classes(List<Class> classes) {
        this.classes = classes;
    }

    public int getSize(){
        return this.classes.size();
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public void save(Class c){
        classes.add(c);
    }

    public Class getByClassID(int classID){
        for(int i = 0; i< classes.size();i++){
            Class c = classes.get(i);
            if(c.getClassID() == classID){
                return c;
            }
        }
        return null;
    }

    public Class get(int i){
        return classes.get(i);
    }

}
