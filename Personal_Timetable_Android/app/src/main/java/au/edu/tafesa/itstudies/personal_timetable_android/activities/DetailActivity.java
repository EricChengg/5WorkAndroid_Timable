package au.edu.tafesa.itstudies.personal_timetable_android.activities;

import android.app.assist.AssistContent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private TextView sessionTextView;
    Session session;
    Lecturer lecture;
    Subject theSubjeet;
    Class theclass;
    Assessment assessment;
    Campus campus;
    SimpleDateFormat theDate=new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat theTime=new SimpleDateFormat("hh:mm");
    SQLiteHelper sqLiteHelper = new SQLiteHelper(DetailActivity.this);
    SQLiteDatabase database = null;



    Class c = new Class();
    Subject s = new Subject();
    List<Assessment> assessmentList = new ArrayList<Assessment>();
    Session se = new Session();
    Lecturer l = new Lecturer();

    int classID;
    int sessionID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent;
        intent = getIntent();
        classID = intent.getIntExtra("TheClassID",0);
        //subjectID = intent.getIntExtra("TheSubjectID",0);
        sessionID = intent.getIntExtra("TheSessionID",0);

        getDetail(classID,sessionID);






        sessionTextView=(TextView) this.findViewById(R.id.sessionTextView);
        sessionTextView.setMovementMethod(new ScrollingMovementMethod());
        sessionTextView.setBackgroundColor(Color.GREEN);

        try
        {
            theSubjeet = new Subject(1,"TGG4","5 WORK");
            campus  = new Campus(22, "TAFESA Capuss", "444444", "Curry Street");
            session = new Session(2, 5, "Testing session", theTime.parse("14:00"), theTime.parse("16:00"), "B003", theDate.parse("2018-10-13"), 1);
            theclass = new Class(1,1,1,1);
            //assessmentID, String name, Date dueDate, String type, int classID
            assessment = new Assessment(1, "Assesment 1", theDate.parse("2018-12-03"),"Assignment",1);
            lecture = new Lecturer(1, "Kym");
            sessionTextView.setText(toLayout(theSubjeet, theclass,session, assessment,lecture, campus));
        } catch (ParseException e)
        {
            System.out.println(e);
        }
    }


    public String toLayout(Subject s, Class c, Session se, Assessment a, Lecturer l, Campus camp ) throws ParseException {
        System.out.println(theSubjeet.getSubjectCode());

        String detail;
                //=  se.toString()+ "\n" +"\n"  + s.toString() + "\n" + c.toString()+"\n"+a.toString()+"\n"+ l.toString();
                                       detail = "Class No : " +c.getClassID()
                                               + "\n \nSubject Name : "+ s.getSubjectName()+
                                               "\n \n Lecturer Name: " + l.getLecturerName()
                                               +" \n \n Campus Name:  "+ camp.getCampusName()
                                               +"\n Campus Address : " +camp.getAddress()
                                               +" \n \n Session No : "+  se.getSessionNo()
                                               +" \n Session time: "+se.getDate().toString()
                               // +"\n  \n Subject Code: " +s.getSubjectCode()

                              //  + " \n  \n Assessment ID : " + a.getAssessmentID()
                                               +"\n  \n  Assement Name : "+a.getName()
                                               +"\n \n  Type Of Assessment : "+a.getType()+
                                               "\n \n  Assement Date : " + a.getDueDate().toString();

                                return detail;

    }

    private void getDetail(int classID, int sessionID){
        try{
            database = sqLiteHelper.getWritableDatabase();
            c = sqLiteHelper.getClass(database,classID);
            se = sqLiteHelper.getSession(database,sessionID);
            l = sqLiteHelper.getLecture(database,c.getLecturerID());
            assessmentList = sqLiteHelper.getAssessments(database,classID);
        }
        catch (Exception e){
            System.out.println(e);
        }



    }
}