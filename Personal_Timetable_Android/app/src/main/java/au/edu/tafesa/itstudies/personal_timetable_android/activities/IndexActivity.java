package au.edu.tafesa.itstudies.personal_timetable_android.activities;

import android.content.ClipData;
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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import au.edu.tafesa.itstudies.personal_timetable_android.R;
import au.edu.tafesa.itstudies.personal_timetable_android.SQlite.SQLiteHelper;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Class;
import au.edu.tafesa.itstudies.personal_timetable_android.models.ClassHasStudent;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Classes;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Schedule;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Session;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Sessions;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Subjects;

@SuppressWarnings("AccessStaticViaInstance")
public class IndexActivity extends AppCompatActivity {
    private static final String THE_STUDENT_ID = "THE_STUDENT_ID";
    public SimpleDateFormat theDate = new SimpleDateFormat("yyyy-MM-dd");
    public SimpleDateFormat theTime = new SimpleDateFormat("HH:mm");


    public int studentID;
    public SQLiteDatabase database = null;
    SQLiteHelper sqLiteHelper = new SQLiteHelper(IndexActivity.this);
    public List<Object> items = new ArrayList<Object>();
    Classes classes = new Classes();
    Subjects subjects = new Subjects();
    Sessions sessions = new Sessions();
    List<Schedule> schedules = new ArrayList<Schedule>();
    public List<String> header = new ArrayList<String>();
    public List<ClassHasStudent> cs = new ArrayList<ClassHasStudent>();

//    ImageButton earlyWeekButton = (ImageButton)findViewById(R.id.earlyWeekImageButton);
//    ImageButton nextWeekButton = (ImageButton)findViewById(R.id.nextWeekImageButton);
//    TextView weekTextView = (TextView) findViewById(R.id.weektextView);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        //get StudentID from LoginActivity
        Intent intent = this.getIntent();
        studentID = intent.getIntExtra(THE_STUDENT_ID,0);
        // testing it is work.
        Toast.makeText(IndexActivity.this,"The student ID: " + studentID, Toast.LENGTH_LONG).show();


        try {
            runData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
//      setting the List View
        ListView listView = (ListView) this.findViewById(R.id.lvschedule);
        listView.setAdapter(new ScheduleViewAdapter());
//        set list view to be button
        ListViewItemSelectedHandler listViewItemSelectedHandler = new ListViewItemSelectedHandler();
        listView.setOnItemClickListener(listViewItemSelectedHandler);
        sqLiteHelper.onUpgrade(database,1,1);
    }

    private class earlyweekButtonHandler implements View.OnClickListener{

        @Override
        public void onClick(View view) {

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqLiteHelper.onUpgrade(database,1,1);
        System.out.println("onDestroy");
    }

    private class ScheduleViewAdapter extends BaseAdapter{

        private static final int TYPE_ITEM = 0;
        private static final int TYPE_HEADER = 1;
        private LayoutInflater inflater;
        private List<String> date = new ArrayList<String>(7);
        private List<Schedule> theSchedule = new ArrayList<Schedule>();
        private Context c;

        @Override
        public int getViewTypeCount(){
            return 2;
        }

        @Override
        public int getCount() {
            return items.size();
        }
        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        private class RowViewComponents{
            public TextView txtTime;
            public TextView txtCourse;
            public TextView txtRoom;
            public TextView txtHeader;
        }

        // separate schedule and string in the items list.
        public int getItemViewType(int position) {
            if (getItem(position) instanceof Schedule) {
                return TYPE_ITEM;
            }
            return TYPE_HEADER;
        }

        @Override
        public View getView(int i, View v, ViewGroup viewGroup) {
            RowViewComponents theComponents;
            int rowType = getItemViewType(i);
            theComponents = new RowViewComponents();
            if(v == null){
                LayoutInflater inflater = (LayoutInflater)(IndexActivity.this).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                switch (rowType){
                    case TYPE_ITEM:
                        v = inflater.inflate(R.layout.timetable_row_layout,null);
                        break;
                    case TYPE_HEADER:
                        //if you use (R.layout.header_item,viewGroup,false) to set the view, it will display default header.
                        v = inflater.inflate(R.layout.header_item,null);
                        break;
                }
            }
            switch (rowType){
                case TYPE_ITEM:
                    theComponents.txtTime = (TextView) v.findViewById(R.id.txtTime);
                    theComponents.txtCourse = (TextView) v.findViewById(R.id.txtCourse);
                    theComponents.txtRoom = (TextView) v.findViewById(R.id.txtRoom);
                    Schedule s = (Schedule) getItem(i);
                    String theRoom = s.getRoom();
                    String startTime = theTime.format(s.getStartTime());
                    String endTime = theTime.format(s.getEndTime());
                    String subjectCode = s.getSubjectCode();
                    theComponents.txtTime.setText(startTime + " - " + endTime);
                    theComponents.txtCourse.setText(subjectCode);
                    theComponents.txtRoom.setText(theRoom);
                    break;

                case TYPE_HEADER:
                    theComponents.txtHeader = (TextView) v.findViewById(R.id.tvDate);
                    String header = (String) getItem(i);
                    theComponents.txtHeader.setText(header);

            }
            return v;
        }
    }

    public void runData() throws ParseException {

        database = sqLiteHelper.getWritableDatabase();
        cs = sqLiteHelper.getClassHasStudent(database, studentID);
        sessions = sqLiteHelper.getSessionList(database, cs);
        classes = sqLiteHelper.findClassByClasshasStudent(database,cs);
        subjects = sqLiteHelper.findSessionsBysubjectID(database, classes);
        schedules = sqLiteHelper.getSchedule(database);
        AddDate();
        addItem();
    }


    private class ListViewItemSelectedHandler implements AdapterView.OnItemClickListener , AdapterView.OnItemSelectedListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            onItemSelected(adapterView,view,i,l);
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            if(items.get(i) instanceof Schedule) {

                int classID = ((Schedule) items.get(i)).getClassID();
                int sessionID = ((Schedule) items.get(i)).getSessionID();
                int subjectID = ((Schedule) items.get(i)).getSubjectID();
                Intent intent = new Intent();
                intent.putExtra("TheClassID", classID);
                intent.putExtra("TheSessionID", sessionID);
                intent.putExtra("TheSubjectID", subjectID);
                intent.setClass(IndexActivity.this, DetailActivity.class);
                startActivity(intent);
            }
            else {

            }
        }


        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public void addItem() throws ParseException {
        for(int i = 0; i < this.schedules.size(); i++){
            for(int ii = 0; ii < 7; ii++){
                String s1 = LocalDate.now().plusDays(ii).getYear() +"-"+LocalDate.now().plusDays(ii).getMonthValue()+"-"+LocalDate.now().plusDays(ii).getDayOfMonth();
                Date d1 = theDate.parse(s1);
                if(this.schedules.get(i).getDate().equals(d1)){
                    if(this.items.contains(this.header.get(ii))){
                        this.items.add(this.schedules.get(i));
                    }
                    else{
                        this.items.add(this.header.get(ii));
                        this.items.add(this.schedules.get(i));
                    }
                }
            }
        }
    }

    // setting header output.
    public void AddDate(){
        this.header.add("Today");
        for (int i = 1; i<7; i++){
            this.header.add(toStringOfDate(LocalDateTime.now().plusDays(i)) +", "+ LocalDateTime.now().plusDays(i).getDayOfMonth() + " "+ toStringOfMonth(LocalDateTime.now().plusDays(i)));
        }
    }

    @SuppressWarnings("AccessStaticViaInstance")
    public static String toStringOfDate(LocalDateTime date){
        //noinspection AccessStaticViaInstance
        if(date.getDayOfWeek().equals(date.getDayOfWeek().MONDAY)){
            return "Mon";
        }
        else if(date.getDayOfWeek().equals(date.getDayOfWeek().TUESDAY)){
            return "Tue";
        }
        else if(date.getDayOfWeek().equals(date.getDayOfWeek().WEDNESDAY)){
            return "Wed";
        }
        else if(date.getDayOfWeek().equals(date.getDayOfWeek().THURSDAY)){
            return "Thu";
        }
        else if(date.getDayOfWeek().equals(date.getDayOfWeek().FRIDAY)){
            return "Fri";
        }
        else if(date.getDayOfWeek().equals(date.getDayOfWeek().SATURDAY)){
            return "Sat";
        }
        else if(date.getDayOfWeek().equals(date.getDayOfWeek().SUNDAY)){
            return "Sun";
        }
        else{
            return null;
        }
    }

    public static String toStringOfMonth(LocalDateTime date){
        if(date.getMonth().equals(date.getMonth().JANUARY)){
            return "Jan";
        }
        else if(date.getMonth().equals(date.getMonth().FEBRUARY)){
            return "Feb";
        }
        else if(date.getMonth().equals(date.getMonth().MARCH)){
            return "Mar";
        }
        else if(date.getMonth().equals(date.getMonth().APRIL)){
            return "Apr";
        }
        else if(date.getMonth().equals(date.getMonth().MAY)){
            return "May";
        }
        else if(date.getMonth().equals(date.getMonth().JUNE)){
            return "Jun";
        }
        else if(date.getMonth().equals(date.getMonth().JULY)){
            return "Jul";
        }
        else if(date.getMonth().equals(date.getMonth().AUGUST)){
            return "Aug";
        }
        else if(date.getMonth().equals(date.getMonth().SEPTEMBER)){
            return "Sep";
        }
        else if(date.getMonth().equals(date.getMonth().OCTOBER)){
            return "Oct";
        }
        else if(date.getMonth().equals(date.getMonth().NOVEMBER)){
            return "Nov";
        }
        else if(date.getMonth().equals(date.getMonth().DECEMBER)){
            return "Dec";
        }
        else{
            return null;
        }
    }
}

