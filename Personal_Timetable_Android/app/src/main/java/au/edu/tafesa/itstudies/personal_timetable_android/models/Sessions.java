package au.edu.tafesa.itstudies.personal_timetable_android.models;

import java.util.ArrayList;
import java.util.List;

public class Sessions {

    private List<Session> sessions = new ArrayList<Session>();

    public void save(Session s)
    {
        sessions.add(s);
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
