package au.edu.tafesa.itstudies.personal_timetable_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CLASS_TAG = "DataBaseHelper";
    public DataBaseHelper(Context context) {
        super(context,"Class.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Subject (" +
                "subjectID int not null , " +
                "subjectName CHAR(5) NOT NULL," +
                "primary key (subjectID)," +
                "foreign key (studentID));");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Course ( " +
                "sessionNo INT NOT NULL, " +
                "sessionContent VARCHAR(45) NOT NULL, " +
                "EndTime Time(1) NOT NULL, " +
                "DateOfClass Date NOT NULL, " +
                "Topics VARCHAR(255) NOT NULL," +
                "primary key(sessionNo)," +
                "foreign key(subjectID));");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Student ( " +
                "studentID int Not null,"+
                "Name VARCHAR(45) NOT NULL," +
                "LoginPassword VARCHAR(10) Not null," +
                "primary key(studentID));" );

    }

    // for upgrade table, but need to Drop the old table frist.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(CLASS_TAG, "\"Upgrading database from version oldVersion to  newVersion which will destroy all old data which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS tbltest1");
        onCreate(db);

    }

    //Testing
}
