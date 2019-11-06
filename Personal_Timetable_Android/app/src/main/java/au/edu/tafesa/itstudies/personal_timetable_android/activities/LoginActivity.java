package au.edu.tafesa.itstudies.personal_timetable_android.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import au.edu.tafesa.itstudies.personal_timetable_android.R;
import au.edu.tafesa.itstudies.personal_timetable_android.SQlite.SQLiteHelper;

/**
 * A login screen that offers login via email/password.
 */

public class LoginActivity extends AppCompatActivity {


    private SQLiteDatabase database = null;

    private final SQLiteHelper sqLiteHelper = new SQLiteHelper(this);

    private static final String THE_STUDENT_ID = "THE_STUDENT_ID";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // running Sqlite database
        database = sqLiteHelper.getWritableDatabase();

        // getting and setting login button
        Button btnLogin = findViewById(R.id.email_sign_in_button);
        HandleButtonLoginClick handleButtonLoginClick = new HandleButtonLoginClick();
        btnLogin.setOnClickListener(handleButtonLoginClick);
        sqLiteHelper.onUpgrade(database,1,1);


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqLiteHelper.onUpgrade(database,1,1);
        System.out.println("onDestroy");
    }

    // setting login function
    private class HandleButtonLoginClick implements View.OnClickListener {
        public void onClick(View v) {

            // getting student id and password input from edit text.
            EditText txtPassword = findViewById(R.id.password);
            EditText txtLoginID = findViewById(R.id.loginID);

            // convent edit text to String and int.
            String password = txtPassword.getText().toString();
            int id = Integer.parseInt(txtLoginID.getText().toString());

            // looking for the id and password in the Sqlite
            // if the cursor row is one, go to index page. if the cursor is not one it will display error message or another id and password.
            if ((sqLiteHelper.verifyStudentLogin(database, id, password)) != 0) {
                Intent intent = new Intent();
                intent.putExtra(THE_STUDENT_ID, id);
                intent.setClass(LoginActivity.this, IndexActivity.class);
                startActivity(intent);
            }
            else {

                Toast.makeText(LoginActivity.this, "Incorrect student ID or password.", Toast.LENGTH_LONG).show();

            }
        }
    }
}
