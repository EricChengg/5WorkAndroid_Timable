package au.edu.tafesa.itstudies.personal_timetable_android.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import au.edu.tafesa.itstudies.personal_timetable_android.R;
import au.edu.tafesa.itstudies.personal_timetable_android.SQlite.SQLiteHelper;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Assessment;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Campus;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Class;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Lecturer;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Session;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Student;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Subject;

public class DetailActivity extends AppCompatActivity {
    public TextView sessionTextView;
    Session session;
    Student student;
    Lecturer lecture;
    Subject theSubjeet;
    Class theclass;
    Assessment assessment;
    Campus campus;
    SimpleDateFormat theDate=new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat theTime=new SimpleDateFormat("hh:mm");
    SQLiteHelper sqLiteHelper = new SQLiteHelper(DetailActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        sessionTextView=(TextView) this.findViewById(R.id.sessionTextView);
        sessionTextView.setMovementMethod(new ScrollingMovementMethod());
        sessionTextView.setBackgroundColor(Color.GREEN);


//        studentTextView=(TextView) this.findViewById(R.id.studentTextView);
//        studentTextView.setMovementMethod(new ScrollingMovementMethod());
//        studentTextView.setBackgroundColor(Color.YELLOW);
        try
        {
            theSubjeet = new Subject(1,"5Work","Test Subject");
            session = new Session(2, 5, "Testing session", theTime.parse("14:00"), theTime.parse("16:00"), "B003", theDate.parse("2018-10-13"), 1);
            theclass = new Class(1,1,1,1);
            sessionTextView.setText(toLayout(theSubjeet, theclass,session));
        } catch (ParseException e)
        {
            System.out.println(e);
        }


    }


    public String toLayout(Subject s, Class c,Session se) throws ParseException {
        //SQLiteHelper sqLiteHelper;
        //int sessionID, int sessionNo, String topic, Date startTime,Date endTime, String room, Date date, int classID
        Date d = theDate.parse("2018-10-31");
        Date t1 = theTime.parse("13:00");
        Date t2 = theTime.parse("15:00");
        // Session s = new Session(1,2,"Test Topic", t1,t2,"B003",d,1);
        //int subjectID, String subjectCode, String subjectName


        System.out.println(theSubjeet.getSubjectCode());

        String detail =  se.toString()+ "\n" +"\n"  + s.toString() + "\n" + c.toString();
        return detail;

    }
}