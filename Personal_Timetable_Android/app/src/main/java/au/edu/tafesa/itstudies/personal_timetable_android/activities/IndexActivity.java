package au.edu.tafesa.itstudies.personal_timetable_android.activities;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import au.edu.tafesa.itstudies.personal_timetable_android.R;
import au.edu.tafesa.itstudies.personal_timetable_android.SQlite.SQLiteHelper;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Class;
import au.edu.tafesa.itstudies.personal_timetable_android.models.ClassHasStudent;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Classes;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Sessions;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Subjects;

public class IndexActivity extends AppCompatActivity {
    private static final String THE_STUDENT_ID = "THE_STUDENT_ID";

    private TextView mTextMessage;

    public int studentID;
    //public Class theClass;
    public SQLiteDatabase database = null;

    Classes classes = new Classes();

    Subjects subjects = new Subjects();

    Sessions sessions = new Sessions();
    //public List<Session> sessionList = new ArrayList<Session>();

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

        //Writ data to sqlite
//        database = sqLiteHelper.getWritableDatabase();
//        cs = sqLiteHelper.getClassHasStudent(database, studentID);
//        sessions = sqLiteHelper.getSessionList(database, cs);
        runData();

        //setting the List View
        ListView listView = (ListView) this.findViewById(R.id.lvschedule);
        listView.setAdapter(new ScheduleViewAdapter());

        //set list view to be button
       // ScheduleOnClickListener scheduleOnClickListener = new ScheduleOnClickListener();
       // listView.setOnClickListener(scheduleOnClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //SQLiteDatabase.deleteDatabase();
        System.out.println("onDestroy");
    }


    private class ScheduleViewAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            int i = sessions.getSize();
            return i;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        private class RowViewComponents{
            public TextView txtTime;
            public TextView txtCourse;
            public TextView txtRoom;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            SimpleDateFormat theDate = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat theTime = new SimpleDateFormat("hh:mm");

            View v;
            LayoutInflater inflater = (LayoutInflater) (IndexActivity.this).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.timetable_row_layout,viewGroup,false);
            RowViewComponents theComponents = new RowViewComponents();

            theComponents.txtTime = (TextView) v.findViewById(R.id.txtTime);
            theComponents.txtCourse = (TextView) v.findViewById(R.id.txtCourse);
            theComponents.txtRoom = (TextView) v.findViewById(R.id.txtRoom);

            TextView t = theComponents.txtTime;
            TextView c = theComponents.txtCourse;
            TextView r = theComponents.txtRoom;

                String startTime = theTime.format(sessions.getById(i).getStartTime());
                String endTime = theTime.format(sessions.getById(i).getEndTime());
//                String subjectCode = subjects.getById(i).getSubjectCode();
                String theRoom = sessions.getById(i).getRoom();

                t.setText(startTime + " - " + endTime);
//                c.setText(subjectCode);
                r.setText(theRoom);
            return v;
        }
    }
    public void runData()
    {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(IndexActivity.this);
        database = sqLiteHelper.getWritableDatabase();
        cs = sqLiteHelper.getClassHasStudent(database, studentID);
        sessions = sqLiteHelper.getSessionList(database, cs);
        classes = sqLiteHelper.findClassByClasshasStudent(database,cs);
        subjects = sqLiteHelper.findSessionsBysubjectID(database, classes);
        System.out.println(subjects.toString());
    }

    private class ScheduleOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

        }
    }
}
