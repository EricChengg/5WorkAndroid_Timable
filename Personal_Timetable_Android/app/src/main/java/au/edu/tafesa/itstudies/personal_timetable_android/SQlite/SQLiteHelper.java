package au.edu.tafesa.itstudies.personal_timetable_android.SQlite;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import au.edu.tafesa.itstudies.personal_timetable_android.models.Campus;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Class;
import au.edu.tafesa.itstudies.personal_timetable_android.models.ClassHasStudent;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Classes;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Schedule;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Session;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Sessions;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Subject;
import au.edu.tafesa.itstudies.personal_timetable_android.models.Subjects;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteDatabase db;
    public ClassHasStudent theClassHasStudent;

    public List<ClassHasStudent> cs = new ArrayList<ClassHasStudent>();

    //public List<Class> ClassList = new ArrayList<Class>();

    public Sessions sessions = new Sessions();

    public Subjects subjects = new Subjects();

    public Classes classes = new Classes();

    public SQLiteHelper(Context context) {
        super(context, "Timetable.db", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
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
                    "  `Campus_CampusID` INT NOT NULL,\n" +
                    "  `Subject_subjectID` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`classID`),\n" +
                    "  CONSTRAINT `fk_Class_Lecture1`\n" +
                    "    FOREIGN KEY (`Lecture_lectureID`)\n" +
                    "    REFERENCES `Lecture` (`lectureID`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION,\n" +
                    "  CONSTRAINT `fk_Class_Subject1`\n" +
                    "    FOREIGN KEY (`Subject_subjectID`)\n" +
                    "    REFERENCES `Subject` (`subjectID`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION,\n" +
                    "  CONSTRAINT `fk_Class_Campus1`\n" +
                    "    FOREIGN KEY (`Campus_CampusID`)\n" +
                    "    REFERENCES `Campus` (`CampusID`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION)");

            db.execSQL("CREATE TABLE IF NOT EXISTS `Assessment` (\n" +
                    "  `assessmentID` INT not null,\n" +
                    "  `name` VARCHAR(45) ,\n" +
                    "  `dueDate` DATE ,\n" +
                    "  `type` VARCHAR(10) ,\n" +
                    "  `Class_classID` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`assessmentID`),\n" +
                    "  CONSTRAINT `fk_Assessment_Class1`\n" +
                    "    FOREIGN KEY (`Class_classID`)\n" +
                    "    REFERENCES `Class` (`classID`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION)");

            db.execSQL("CREATE TABLE IF NOT EXISTS `Session` (\n" +
                    "  `sessionID` INT NOT NULL,\n" +
                    "  `sessionNo` INT ,\n" +
                    "  `topic` VARCHAR(45) ,\n" +
                    "  `startTime` TIME ,\n" +
                    "  `endTime` TIME ,\n" +
                    "  `room` VARCHAR(6) ,\n" +
                    "  `date` DATE ,\n" +
                    "  `Class_classID` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`sessionID`),\n" +
                    "  CONSTRAINT `fk_Session_Class1`\n" +
                    "    FOREIGN KEY (`Class_classID`)\n" +
                    "    REFERENCES `Class` (`classID`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION)");

            db.execSQL("CREATE TABLE IF NOT EXISTS `Class_has_Student` (\n" +
                    "  `Class_classID` INT NOT NULL,\n" +
                    "  `Student_studentID` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`Class_classID`, `Student_studentID`),\n" +
                    "  CONSTRAINT `fk_Class_has_Student_Class1`\n" +
                    "    FOREIGN KEY (`Class_classID`)\n" +
                    "    REFERENCES `Class` (`classID`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION,\n" +
                    "  CONSTRAINT `fk_Class_has_Student_Student1`\n" +
                    "    FOREIGN KEY (`Student_studentID`)\n" +
                    "    REFERENCES `Student` (`studentID`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION)");
            onInsert(db);
        } catch (Exception e) {
            System.out.println("Error in creating table.");
            System.out.println(e.getMessage());
        }
    }

    private static void onInsert(SQLiteDatabase db) {
        try {
            //Student Subject Lecture Class Assignment Test Campus Session Class_has_Student
            db.execSQL("INSERT INTO student values(103500,'Eric','5work')");

            db.execSQL("INSERT INTO Subject values(1,'5Work','Workplace Project')");
            db.execSQL("INSERT INTO Subject values(2,'6Work','Testing Project')");

            db.execSQL("INSERT INTO Lecture values(1,'Kym')");

            db.execSQL("INSERT INTO Campus values(1,'Testcamput','test','testing')");

            db.execSQL("INSERT INTO Class Values(1,1,1,1)");
            db.execSQL("INSERT INTO Class Values(2,1,1,2)");

            db.execSQL("insert INTO Assessment values(1,'testing name','2012-06-18','Test',1)");

            db.execSQL("INSERT INTO Session values(1,1,'Testing session','14:00','16:00','B003','2018-10-18',1)");
            db.execSQL("INSERT INTO Session values(2,1,'Testing2 session','17:00','19:00','B002','2018-10-24',2)");
            db.execSQL("INSERT INTO Session values(3,1,'Testing3 session','17:00','19:00','B002','2018-10-27',2)");


            db.execSQL("INSERT INTO Class_has_Student values(1,103500)");
            db.execSQL("INSERT INTO Class_has_Student values(2,103500)");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Student");
        db.execSQL("DROP TABLE IF EXISTS Class_has_Student");
        db.execSQL("DROP TABLE IF EXISTS Subject");
        db.execSQL("DROP TABLE IF EXISTS Class");
        db.execSQL("DROP TABLE IF EXISTS Assessment");
        db.execSQL("DROP TABLE IF EXISTS Session");
        db.execSQL("DROP TABLE IF EXISTS Campus");
        db.execSQL("DROP TABLE IF EXISTS Lecture");

        onCreate(db);
    }

    public int verifyStudentLogin(SQLiteDatabase db, int id, String password) {
        String[] columns = {"studentID", "loginPassword"};
        String[] whereValues = new String[2];
        String table = "Student";
        String where = "studentID =? and loginPassword =?";
        whereValues[0] = String.valueOf(id);
        whereValues[1] = password;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;
        Cursor c = db.query(table, columns, where, whereValues, groupBy, having, orderBy, limit);

        if (c != null && c.getCount() == 1) {
            c.close();
            return id;
        } else {
            c.close();
            return 0;
        }
    }


    public List<ClassHasStudent> getClassHasStudent(SQLiteDatabase db, int id) {

        String[] columns ={"Class_classID","Student_studentID"};
        String[] whereValues = new String[1];
        String table = "Class_has_Student";
        String where = "Student_studentID =?";
        whereValues[0] = String.valueOf(id);
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;

        try {
            Cursor c = db.query(table, columns, where, whereValues, groupBy, having, orderBy, limit);
            int y = c.getColumnCount();
            if (c != null) {
                c.moveToFirst();
                for (int i = 0; i < c.getCount(); i++) {
                    int classID = c.getInt(c.getColumnIndex("Class_classID"));
                    int studentID = c.getInt(c.getColumnIndex("Student_studentID"));
                    theClassHasStudent = new ClassHasStudent(classID, studentID);
                    cs.add(theClassHasStudent);
                    c.moveToNext();
                    System.out.println(theClassHasStudent.toString());
                }

                c.close();

            } else {
                System.out.println("Cursor is null.");
                System.out.println(cs.toString());
                c.close();
                cs = null;
            }
        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        }
        return cs;
    }

    public Sessions getSessionList(SQLiteDatabase db, List<ClassHasStudent> cs) {
        try {
            String[] columns = {"sessionID","sessionNo","topic","startTime","endTime","room","date","Class_classID" };
            String[] whereValues = new String[cs.size()];
            String where = "Class_classID =?";
            String[] w = new String[cs.size()];

            for(int i = 0 ; i < cs.size() ; i++) {
                whereValues[i] = String.valueOf(cs.get(i).getclassID());
                if(i + 1 < cs.size()) {
                    w[i] = " or Class_classID =? ";
                    where += w[i];
                }
                else
                {
                    //noting to do
                }
            }
            String table = "Session";
            String groupBy = null;
            String having = null;
            String orderBy = null;
            String limit = null;
            Cursor c = db.query(table, columns, where, whereValues, groupBy, having, orderBy, limit);
            if (c != null) {
                c.moveToFirst();
                SimpleDateFormat theDate = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat theTime = new SimpleDateFormat("hh:mm");

                for (int i = 0; i < c.getCount(); i++) {
                    int sessionID = c.getInt(c.getColumnIndex("sessionID"));
                    int sessionNo = c.getInt(c.getColumnIndex("sessionNo"));
                    String topic = c.getString(c.getColumnIndex("topic"));
                    Date startTime = theTime.parse(c.getString(c.getColumnIndex("startTime")));
                    Date endTime = theTime.parse(c.getString(c.getColumnIndex("endTime")));
                    Date date = theDate.parse(c.getString(c.getColumnIndex("date")));
                    String room = c.getString(c.getColumnIndex("room"));
                    int classID = c.getInt(c.getColumnIndex("Class_classID"));

                    Session s = new Session(sessionID, sessionNo, topic, startTime, endTime, room, date, classID);
                    sessions.save(s);
                    c.moveToNext();
                }
            }
            c.close();
            return sessions;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error");
            return null;
        }
    }

    public Classes findClassByClasshasStudent(SQLiteDatabase db, List<ClassHasStudent> cs) {
        try {

            String[] columns = {"classID", "Lecture_lectureID", "Campus_CampusID", "Subject_subjectID"};
            String[] whereValues = new String[cs.size()];
            String table = "Class";
            String where = "classID =?";
            String[] w = new String[cs.size()];
            for (int i = 0; i < cs.size(); i++) {
                whereValues[i] = String.valueOf(cs.get(i).getclassID());
                if (i + 1 < cs.size()) {
                    w[i] = " or classID =? ";
                    where += w[i];
                }
                else
                {
                    //noting to do
                }
                System.out.println(whereValues.toString());
            }

            String groupBy = null;
            String having = null;
            String orderBy = null;
            String limit = null;

            Cursor c = db.query(table, columns, where, whereValues, groupBy, having, orderBy, limit);
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                int classID = c.getInt(c.getColumnIndex("classID"));
                int lectureID = c.getInt(c.getColumnIndex("Lecture_lectureID"));
                int subjectID = c.getInt(c.getColumnIndex("Subject_subjectID"));
                int CampusID = c.getInt(c.getColumnIndex("Campus_CampusID"));
                Class theClass = new Class(classID, lectureID, subjectID, CampusID);
                this.classes.save(theClass);
                c.moveToNext();
            }
            c.close();
            return this.classes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Subjects findSessionsBysubjectID(SQLiteDatabase db, Classes theClasses){

        try {
            String[] columns = {"subjectID", "subjectCode", "subjectName"};
            String[] whereValues = new String[theClasses.getSize()];
            String table = "Subject";
            String where = "subjectID =?";
            String[] w = new String[theClasses.getSize()];
            for (int i = 0; i < theClasses.getSize(); i++) {
                whereValues[i] = String.valueOf(theClasses.get(i).getSubjectID());
                if(i +1  < theClasses.getSize()) {
                    w[i] = " or subjectID =? ";
                    where += w[i];
                }
            }

            String groupBy = null;
            String having = null;
            String orderBy = null;
            String limit = null;

            Cursor c = db.query(table, columns, where, whereValues, groupBy, having, orderBy, limit);
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                int subjectID = c.getInt(c.getColumnIndex("subjectID"));
                String subjectCode = c.getString(c.getColumnIndex("subjectCode"));
                String subjectName = c.getString(c.getColumnIndex("subjectName"));
                Subject s = new Subject(subjectID, subjectCode, subjectName);
                subjects.save(s);
                c.moveToNext();
                System.out.println(subjects.toString());
            }
            c.close();
            return subjects;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Schedule> getSchedule(SQLiteDatabase db){
        try {
            LocalDate localDate = LocalDate.now().plusYears(1);
            String MY_QUERY = "select Subject.subjectID, Class.classID, Session.sessionID, Subject.subjectCode, Session.startTime, Session.endTime, Session.date, Session.room\n" +
                    "from Session\n" +
                    "inner join Class on Session.Class_classID = Class.classID\n " +
                    "inner join Subject on Class.Subject_subjectID = Subject.subjectID\n" +
                    "where Session.date Between '2010-10-10' and '2020-10-10'";

            System.out.println(MY_QUERY.toString());

            SimpleDateFormat theDate = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat theTime = new SimpleDateFormat("hh:mm");
            List<Schedule> schedules = new ArrayList<Schedule>();
            Cursor c = db.rawQuery(MY_QUERY,null);
            if(c != null) {
                c.moveToFirst();
                for (int i = 0; i < c.getCount(); i++) {
                    int subjectID = 1;
                    c.getInt(c.getColumnIndex("Subject.subjectID"));
                    int classID = c.getInt(c.getColumnIndex("Class.classID"));
                    int sessionID = c.getInt(c.getColumnIndex("Session.sessionID"));
                    String subjectCode = c.getString(c.getColumnIndex("Subject.subjectCode"));
                    Date startTime = theTime.parse(c.getString(c.getColumnIndex("Session.startTime")));
                    Date endTime = theTime.parse(c.getString(c.getColumnIndex("Session.endTime")));
                    Date date = theDate.parse(c.getString(c.getColumnIndex("Session.date")));
                    String room = c.getString(c.getColumnIndex("Session.room"));
                    schedules.add(new Schedule(subjectID, classID, sessionID, subjectCode, startTime, endTime, date, room));
                    c.moveToNext();
                    System.out.println(subjects.toString());
                }
                c.close();
                return schedules;
            }
            c.close();
            return null;
        }
        catch (Exception e){
            return null;
        }

    }
}

