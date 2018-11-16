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
    SimpleDateFormat theDate=new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat theTime=new SimpleDateFormat("hh:mm");
    SQLiteHelper sqLiteHelper = new SQLiteHelper(DetailActivity.this);
    SQLiteDatabase database = null;

    Class c = new Class();
    Subject s = new Subject();
    List<Assessment> assessmentList = new ArrayList<Assessment>();
    Session se = new Session();
    Lecturer l = new Lecturer();
    Campus campus = new Campus();

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
        sessionID = intent.getIntExtra("TheSessionID",0);
        getDetail(classID,sessionID);

        sessionTextView=(TextView) this.findViewById(R.id.sessionTextView);
        sessionTextView.setMovementMethod(new ScrollingMovementMethod());
        sessionTextView.setBackgroundColor(Color.GREEN);

        try
        {
            sessionTextView.setText(toLayout(s, c,se, assessmentList,l, campus));
        } catch (ParseException e)
        {
            System.out.println(e);
        }
        sqLiteHelper.onUpgrade(database,1,1);
    }
    public String toLayout(Subject s, Class c, Session se, List<Assessment> a, Lecturer l, Campus camp ) throws ParseException {

        String detail;
                //=  se.toString()+ "\n" +"\n"  + s.toString() + "\n" + c.toString()+"\n"+a.toString()+"\n"+ l.toString();
                                       detail = "Class No : " +c.getClassID()
                                               + "\n \nSubject Name : "+ s.getSubjectName()+
                                               "\n \n Lecturer Name: " + l.getLecturerName()
                                               +" \n \n Campus Name:  "+ camp.getCampusName()
                                               +"\n Campus Address : " +camp.getAddress()
                                               +" \n \n Session No : "+  se.getSessionNo()
                                               +" \n Session time: "+se.getDate().toString()
                                               + printAssessment(a);
                                return detail;
    }

    public String printAssessment(List<Assessment> assessments){
        String s = "";
        for(int i = 0; i< assessments.size(); i++){
           s += "\n  \n  Assement Name : "+ assessments.get(i).getName()
                   +"\n \n  Type Of Assessment : "+assessments.get(i).getType()+
                   "\n \n  Assement Date : " + assessments.get(i).getDueDate().toString();
        }
        return s;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqLiteHelper.onUpgrade(database,1,1);
        System.out.println("onDestroy");
    }

    private void getDetail(int classID, int sessionID){
        try{
            database = sqLiteHelper.getWritableDatabase();
            c = sqLiteHelper.getClass(database,classID);
            se = sqLiteHelper.getSession(database,sessionID);
            l = sqLiteHelper.getLecture(database,c.getLecturerID());
            assessmentList = sqLiteHelper.getAssessments(database,classID);
            campus = sqLiteHelper.getCampus(database,c.getCampusID());
            s = sqLiteHelper.getSubject(database,c.getSubjectID());

        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}