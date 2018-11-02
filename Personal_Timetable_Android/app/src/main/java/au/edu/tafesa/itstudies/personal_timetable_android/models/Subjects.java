package au.edu.tafesa.itstudies.personal_timetable_android.models;

import java.util.ArrayList;
import java.util.List;

public class Subjects {

    private List<Subject> s = new ArrayList<Subject>();

    public Subjects() {
    }

    public Subjects(List<Subject> s) {
        this.s = s;
    }

    public void save(Subject subject) {
        s.add(subject);
    }

    public List<Subject> getS() {
        return s;
    }

    public void setS(List<Subject> s) {
        this.s = s;
    }

    public int getSize(){
        return this.s.size();
    }

    public Subject getBySubjectID(int subjectID){
        for(int i = 0 ; i < s.size(); i++)
        {
            Subject subject = s.get(i);
            if(subject.getSubjectID() == subjectID)
            {
                return subject;
            }


        }
        return null;
    }
}
