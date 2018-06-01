package au.edu.tafesa.itstudies.personal_timetable_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import java.sql.Time;

public class DetailActivity extends AppCompatActivity {

    ClassInfo classInfo = new ClassInfo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        int i = 0;
        String theSubject;
        int theSessionNo;
        Intent classIntent;
        classIntent = this.getIntent();

        theSubject = classIntent.getStringExtra("SUBJECT_NAME");
        theSessionNo = classIntent.getIntExtra("THE_SESSION_NO",i);







    }


    private void setSummary(String theSubjecy, int theSessionNo)
    {
        classInfo.get
        StringBuilder summary;

        summary = new StringBuilder("Time : ");
        summary.append()
    }
}
