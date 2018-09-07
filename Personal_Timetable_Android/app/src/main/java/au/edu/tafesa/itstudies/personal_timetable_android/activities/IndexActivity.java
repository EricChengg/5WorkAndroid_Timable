package au.edu.tafesa.itstudies.personal_timetable_android.activities;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import au.edu.tafesa.itstudies.personal_timetable_android.R;
import au.edu.tafesa.itstudies.personal_timetable_android.SQlite.SQLiteHelper;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Class;
import au.edu.tafesa.itstudies.personal_timetable_android.models.ClassHasStudent;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Session;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Sessions;

public class IndexActivity extends AppCompatActivity {
    private static final String THE_STUDENT_ID = "THE_STUDENT_ID";

    private TextView mTextMessage;
    private int studentID;
    //public Class theClass;
    private SQLiteDatabase database = null;

    //private List<Integer> classIDList = new ArrayList<Integer>();
    private int adapterNumber;
    //SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
    public List<Class> classList = new ArrayList<Class>();
    public Sessions sessions;

    public List<ClassHasStudent> cs = new ArrayList<ClassHasStudent>();



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        //get StudentID from LoginActivity
        Intent intent = this.getIntent();
        studentID = intent.getIntExtra(THE_STUDENT_ID,0);
        // testing it is work.
        Toast.makeText(IndexActivity.this,"The student ID: " + studentID, Toast.LENGTH_LONG).show();


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(IndexActivity.this);

        //Writ data to sqlite
        database = sqLiteHelper.getWritableDatabase();
        cs = sqLiteHelper.getClassHasStudent(database, studentID);
        sessions = sqLiteHelper.getSessionList(database, cs);

        //setting the List View
        ListView listView = (ListView) this.findViewById(R.id.lvschedule);
        listView.setAdapter(new ScheduleViewAdapter());

        //set list view to be button
       // ScheduleOnClickListener scheduleOnClickListener = new ScheduleOnClickListener();
       // listView.setOnClickListener(scheduleOnClickListener);




    }

    private class ScheduleViewAdapter extends BaseAdapter{
        private TextView txtTime = (TextView) findViewById(R.id.txtTime);;
        private TextView txtCourse = (TextView) findViewById(R.id.txtCourse);;
        private TextView txtRoom = (TextView) findViewById(R.id.txtRoom);;


        @Override
        public int getCount() {
            return classList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = (LayoutInflater) (IndexActivity.this).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.timetable_row_layout,viewGroup,false);



            for(Session s : sessions.getSessions())
            {
                txtTime.setText(s.getStartTime()+ " - " + s.getEndTime());
                txtCourse.setText(s.getTopic());
                txtRoom.setText(s.getRoom());
            }

            //get course list via courseID list
//            courseDataLoader = sqLiteHelper.getCourse(database,classIDList);
//            for (Class c: ) {
//                txtTime.setText(c.getStartTime().toString()+" - "+ c.getEndTime().toString());
//                txtCourse.setText(c.getCourseCode());
//                txtRoom.setText(c.getRoom());
//                view = txtCourse;
//                view = txtRoom;
//                view = txtTime;
//
//            }
            return view;
        }
    }

    private class ScheduleOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

        }
    }



}
