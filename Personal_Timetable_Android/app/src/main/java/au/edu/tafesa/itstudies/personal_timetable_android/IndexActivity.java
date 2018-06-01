package au.edu.tafesa.itstudies.personal_timetable_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class IndexActivity extends AppCompatActivity {
    private ClassInfo classData;
    private ListView lvTimeTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        lvTimeTable = (ListView) this.findViewById(R.id.lvTimeTable);

    }
    private class ListViewItemSelectedHandler implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
        private static final String CLASS_TAG = "ListViewItemSelectedHandler";
        private int position = 0;


        public void onItemSelected(AdapterView<?> parent, View rowView, int position, long id) {


            String theSubject = classData.getSubjectName(position);
            int theSessionNo =  classData.getSessionNo(position);
            Activity sourceActivity = IndexActivity.this;
            Class destinationClass = DetailActivity.class;
            Intent detailIntent = new Intent(sourceActivity,destinationClass);
            if(classData.getSubjectName(position) != null)
            {
                Toast.makeText(IndexActivity.this, "Please select the class.", Toast.LENGTH_SHORT).show();
            }
            else {
                detailIntent.putExtra("THE_SESSION_NO",theSessionNo);
                detailIntent.putExtra("SUBJECT_NAME",theSubject);
                startActivity(detailIntent);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            onItemSelected(adapterView, view,position,l);
        }
    }


    private class RowViewComponents {
        public TextView txtTime;
        public TextView txtSubject;
        public TextView txtRoom;
    }



    private class MyListViewAdapter extends BaseAdapter {
        public static final String CLASS_TAG = "MyListViewAdapter";
        private DataBaseHelper theData;


        public MyListViewAdapter(DataBaseHelper theData) {
            this.theData = theData;
        }

        public int getCount() {
            return 5;
//			return 5;
        }

        public Object getItem(int position) {
            return 5;
        }

        public View

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView;
            RowViewComponents rowViewComponents;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) (IndexActivity.this).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.content_detail, parent, false);
                rowViewComponents = new RowViewComponents();
                rowViewComponents.txtRoom = (TextView)rowView.findViewById(R.id.txtRoom);
                rowViewComponents.txtTime = (TextView)rowView.findViewById(R.id.txtTime);
                rowViewComponents.txtSubject = (TextView)rowView.findViewById(R.id.txtSubject);
                rowView.setTag(rowViewComponents);
                //This allows clicking on the row in the ListView to be recognised
                ((ViewGroup)rowView).setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
            } else {
                rowView = convertView;
                rowViewComponents = (RowViewComponents) rowView.getTag();
            }
            // Setup the TextViews showing the row count and phone number
            // and enable/disable the buttons appropriately

            Log.v(CLASS_TAG, "Updating Class information.");
            if (getItem(position) == null) {
                rowView = null;
            } else {
                rowViewComponents.txtRoom.setText("");
                rowViewComponents.txtTime.setText("");
                rowViewComponents.txtSubject.setText("");
            }
            return rowView;
        }

        public  String[] listValue = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday"};
    }
}

