package au.edu.tafesa.itstudies.personal_timetable_android.models;

import java.util.ArrayList;
import java.util.List;

public class Sessions {

    private List<Session> sessions = new ArrayList<Session>();


    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public int getSize()
    {
        return sessions.size();
    }

    public Session getByClassID(int ClassID){
        for(int i = 0; i < sessions.size(); i++)
        {
            Session s = sessions.get(i);
            if(s.getClassID() == ClassID)
            {
                return s;
            }
        }
        return null;
    }

    public void save(Session s) {
        sessions.add(s);
    }

    @Override
    public String toString() {
        return "Sessions{" +
                "sessions=" + sessions +
                '}';
    }


}
