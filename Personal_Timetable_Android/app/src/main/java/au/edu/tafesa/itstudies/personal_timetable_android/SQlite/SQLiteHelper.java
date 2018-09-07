package au.edu.tafesa.itstudies.personal_timetable_android.SQlite;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import au.edu.tafesa.itstudies.personal_timetable_android.models.Campus;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Class;
import au.edu.tafesa.itstudies.personal_timetable_android.models.ClassHasStudent;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Session;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Sessions;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String CLASS_TAG = "SQLiteHelper";
    public static final String passowrd = "5work";
    public SQLiteDatabase db;

    public ClassHasStudent theClassHasStudent;

    public List<ClassHasStudent> cs = new ArrayList<ClassHasStudent>();

    public Class theClass;

    public List<Class> ClassList = new ArrayList<Class>();

    public Sessions sessions;

    public SQLiteHelper(Context context)
    {
        super(context, "Timetable.db", null,1);
    }

    public void onCreate(SQLiteDatabase db)
    {
        try {
            //create table

            db.execSQL("CREATE TABLE IF NOT EXISTS `Student` (\n" +
                    "  `studentID` INT NOT NULL,\n" +
                    "  `studentName` VARCHAR(45) ,\n" +
                    "  `loginPassword` VARCHAR(15) ,\n" +
                    "  PRIMARY KEY (`studentID`))");

            db.execSQL("CREATE TABLE IF NOT EXISTS `Lecture` (\n" +
                    "  `lectureID` INT NOT NULL,\n" +
                    "  `lectureName` VARCHAR(45) ,\n" +
                    "  PRIMARY KEY (`lectureID`))");

            db.execSQL("CREATE TABLE IF NOT EXISTS `Subject` (\n" +
                    "  `subjectID` INT NOT NULL,\n" +
                    "  `subjectCode` VARCHAR(10) ,\n" +
                    "  `subjectName` VARCHAR(45) ,\n" +
                    "  PRIMARY KEY (`subjectID`))");

            db.execSQL("CREATE TABLE IF NOT EXISTS `Campus` (\n" +
                    "  `CampusID` INT NOT NULL,\n" +
                    "  `campusName` VARCHAR(45) ,\n" +
                    "  `campusCode` VARCHAR(5) ,\n" +
                    "  `address` VARCHAR(45) ,\n" +
                    "  PRIMARY KEY (`CampusID`))");


            db.execSQL("CREATE TABLE IF NOT EXISTS `Class` (\n" +
                    "  `classID` INT NOT NULL,\n" +
                    "  `Lecture_lectureID` INT NOT NULL,\n" +
                    "  `Subject_subjectID` INT NOT NULL,\n" +
                    "  `Campus_CampusID` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`classID`, `Subject_subjectID`),\n" +
                    //"  CONSTRAINT `fk_Class_Lecture1`\n" +
                    "    FOREIGN KEY (`Lecture_lectureID`)\n" +
                    "    REFERENCES `Lecture` (`lectureID`)\n" +
                    "    ON DELETE NO ACTION ON UPDATE NO ACTION,\n" +
                   // "  CONSTRAINT `fk_Class_Subject1`\n" +
                    "    FOREIGN KEY (`Subject_subjectID`)\n" +
                    "    REFERENCES `Subject` (`subjectID`)\n" +
                    "    ON DELETE NO ACTION ON UPDATE NO ACTION,\n" +
                   // "  CONSTRAINT `fk_Class_Campus1`\n" +
                    "    FOREIGN KEY (`Campus_CampusID`)\n" +
                    "    REFERENCES `Campus` (`CampusID`)\n" +
                    "    ON DELETE NO ACTION ON UPDATE NO ACTION)");

            db.execSQL("CREATE TABLE IF NOT EXISTS `Assignment` (\n" +
                    "  `assignmentID` INT NOT NULL,\n" +
                    "  `name` VARCHAR(45) ,\n" +
                    "  `dueDate` DATE ,\n" +
                    "  `Class_classID` INT NOT NULL,\n" +
                    "  `Class_Subject_subjectID` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`assignmentID`, `Class_classID`, `Class_Subject_subjectID`),\n" +
                   // "  CONSTRAINT `fk_Assignment_Class1`\n" +
                    "    FOREIGN KEY (`Class_classID` , `Class_Subject_subjectID`)\n" +
                    "    REFERENCES `Class` (`classID` , `Subject_subjectID`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION)");

            db.execSQL("CREATE TABLE IF NOT EXISTS `Test` (\n" +
                    "  `testID` INT NOT NULL,\n" +
                    "  `testName` VARCHAR(45) ,\n" +
                    "  `testDate` DATE ,\n" +
                    "  `Class_classID` INT NOT NULL,\n" +
                    "  `Class_Subject_subjectID` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`testID`, `Class_classID`, `Class_Subject_subjectID`),\n" +
                   // "  CONSTRAINT `fk_Test_Class1`\n" +
                    "    FOREIGN KEY (`Class_classID` , `Class_Subject_subjectID`)\n" +
                    "    REFERENCES `Class` (`classID` , `Subject_subjectID`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION)");

            db.execSQL("CREATE TABLE IF NOT EXISTS `Session` (\n" +
                    "  `sessionID` INT NOT NULL,\n" +
                    "  `topic` VARCHAR(45),\n" +
                    "  `startTime` TIME,\n" +
                    "  `endTime` TIME,\n" +
                    "  `room` VARCHAR(6),\n" +
                    "  `date` DATE,\n" +
                    "  `Class_classID` INT NOT NULL,\n" +
                    "  `Class_Subject_subjectID` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`sessionID`, `Class_classID`, `Class_Subject_subjectID`),\n" +
                    //"  CONSTRAINT `fk_Session_Class1`\n" +
                    "    FOREIGN KEY (`Class_classID` , `Class_Subject_subjectID`)\n" +
                    "    REFERENCES `Class` (`classID` , `Subject_subjectID`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION)");


            db.execSQL("CREATE TABLE IF NOT EXISTS `Class_has_Student` (\n" +
                    "  `Class_classID` INT NOT NULL,\n" +
                    "  `Class_Subject_subjectID` INT NOT NULL,\n" +
                    "  `Student_studentID` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`Class_classID`),\n"+
                  //  "  CONSTRAINT `fk_Class_has_Student_Class1`\n" +
                    "    FOREIGN KEY (`Class_classID` , `Class_Subject_subjectID`)\n" +
                    "    REFERENCES `Class` (`classID` , `Subject_subjectID`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION,\n" +
                  //  "  CONSTRAINT `fk_Class_has_Student_Student1`\n" +
                    "    FOREIGN KEY (`Student_studentID`)\n" +
                    "    REFERENCES `Student` (`studentID`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION)");

            //create index
//            db.execSQL("CREATE INDEX fk_Class_Lecture1_idx on Class(Lecture_lectureID)");
//
//            db.execSQL("CREATE INDEX fk_Class_Subject1_idx on Class (Subject_subjectID)");
//
//            db.execSQL("CREATE INDEX fk_Assignment_Class1_idx on Assignment (Class_classID, Class_Subject_subjectID)");
//
//            db.execSQL("CREATE INDEX fk_Test_Class1_idx on Test (Class_classID, Class_Subject_subjectID)");
//
//            db.execSQL("CREATE INDEX fk_Campus_has_Class_Class1_idx on Campus_has_Class (Class_courseID, Class_Subject_subjectID)");
//            db.execSQL("CREATE INDEX fk_Campus_has_Class_Campus1_idx on Campus_has_Class (Campus_CampusID)");
//
//            db.execSQL("CREATE INDEX fk_Class_has_Student_Student1_idx on Class_has_Student (Student_studentID)");
//            db.execSQL("CREATE INDEX fk_Class_has_Student_Class1_idx on Class_has_Student(Class_classID, Class_Subject_subjectID)");


            onInsert(db);

        }
        catch(Exception e)
        {
            System.out.println("Error in creating table.");
            System.out.println(e.getMessage());
        }
    }

    private static void onInsert(SQLiteDatabase db)
    {
        try
        {
            //Student Subject Lecture Class Assignment Test Campus Session Class_has_Student
            db.execSQL("INSERT INTO student values(103500,'Eric','5work')");

            db.execSQL("INSERT INTO Subject values(1,'5Work','Workplace Project')");

            db.execSQL("INSERT INTO Lecture values(1,'Kym')");

            db.execSQL("INSERT INTO Class Values(1,1,1,1)");

            db.execSQL("INSERT INTO Assignment values(1,'Assignment1','12/1/2018',1,1)");

            db.execSQL("INSERT INTO Test values(1,'Test1','12/1/2018',1,1)");

            db.execSQL("INSERT INTO Campus values(1,'Testcamput','test','testing')");

            db.execSQL("INSERT INTO Session values(1,'Testing session','14:00','16:00','31/08/2018',1,1)");

            db.execSQL("INSERT INTO Class_has_Student values(1,1,103500)");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Student");
        onCreate(db);
    }

    public int verifyStudentLogin(SQLiteDatabase db, int id, String password) {
        String[] columns = new String[2];
        String[] whereValues = new String[2];
        String table = "Student";

        columns[0]  = "studentID";
        columns[1] = "loginPassword";
        String where = "studentID =? and loginPassword =?";
        whereValues[0] = String.valueOf(id);
        whereValues[1] = password;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;
        Cursor c = db.query(table,columns,where,whereValues,groupBy,having,orderBy,limit);

        if(c!= null && c.getCount() == 1)
        {
            c.close();
            return id;
        }
        else
        {
            c.close();
            return 0;
        }
    }

    public List<ClassHasStudent> getClassHasStudent(SQLiteDatabase db,int id)
    {
//        String[] columns = {"Class_classID","Student_studentID","Class_Subject_subjectID"};
//        String[] whereValues = {"Student_studentID =?"};
//        String table = "Class_has_Student";
//        String where = "Student_studentID =?";
//        String[] whereValue ={String.valueOf(id)};

        String[] columns = new String[3];
        String[] whereValues = new String[1];
        String table = "Class_has_Student";

        columns[0] = "Class_classID";
        columns[1] = "Class_Subject_subjectID";
        columns[2] = "Student_studentID";

        String where = "Student_studentID =?";
        whereValues[0] = String.valueOf(id);


        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;

        try {
            Cursor c = db.query(table, columns, where, whereValues, groupBy, having, orderBy, limit);
            if(c != null) {
                c.moveToFirst();
                for (int i = 0; i < c.getCount(); i++) {
                    int classID = c.getInt(c.getColumnIndex("Class_classID"));
                    int subjectID = c.getInt(c.getColumnIndex("Class_Subject_subjectID"));
                    int studentID = c.getInt(c.getColumnIndex("Student_studentID"));
                    theClassHasStudent = new ClassHasStudent(classID,subjectID,studentID);
                    cs.add(theClassHasStudent);
                    c.moveToNext();
                    System.out.println(theClassHasStudent.toString());
                }

                c.close();

            }
            else
            {
                System.out.println("Cursor is null.");
                System.out.println(cs.toString());
                c.close();
                cs = null;
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error :" + e.getMessage());
        }
        return cs;
    }



    public Sessions getSessionList(SQLiteDatabase db, List<ClassHasStudent> cs) {

        String[] columns = {"sessionID", "topic", "startTime", "endTime", "room", "date", "Class_classID", "Class_Subject_subjectID"};
        String[] whereValues = new String[cs.size()*2];
        List<String> values = new ArrayList<String>();
        for (int i = 0; i < whereValues.length; i++) {
            whereValues[i] = String.valueOf(cs.get(i).getclassID());
            whereValues[i+1] = String.valueOf(cs.get(i).getSubjectID());
        }
        String table = "Session";
        String where = "Class_classID =? and Class_Subject_subjectID =?";

        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;

        try {
            Cursor c = db.query(table, columns, where, whereValues, groupBy, having, orderBy, limit);
            c.moveToFirst();

            //LocalDate date = LocalDate.parse("2018-05-05");
            SimpleDateFormat theDate = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat theTime = new SimpleDateFormat("HH:mm");

            for (int i = 0; i < c.getCount(); i++) {

                int sessionID = c.getInt(c.getColumnIndex("sessionID"));
                String topic = c.getString(c.getColumnIndex("topic"));
                Date startTime = theTime.parse(c.getString(c.getColumnIndex("startTime")));
                Date endTime = theTime.parse(c.getString(c.getColumnIndex("endTime")));
                Date date = theDate.parse(c.getString(c.getColumnIndex("date")));
                String room = c.getString(c.getColumnIndex("room"));
                int classID = c.getInt(c.getColumnIndex("Class_classID"));
                int subjectID = c.getInt(c.getColumnIndex("Class_Subject_subjectID"));

                //System.out.println(c.getString(3));
                //Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
//                Date startDate = theDate.parse(c.getString(3));
                // Date startDate = theDate.(c.getString(3));

//                Date endDate = new java.sql.Date(c.getLong(4));
//                Date starttime = new java.sql.Date(c.getLong(5));
//                Date endTime = new java.sql.Date(c.getLong(6));

                sessions.save(new Session(sessionID, topic, startTime, endTime, date, room));
                c.moveToNext();


            }
            c.close();
            return sessions;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }


    }

//    public List<Campus> getCampus(SQLException db)
//    {
//
//    }
}
