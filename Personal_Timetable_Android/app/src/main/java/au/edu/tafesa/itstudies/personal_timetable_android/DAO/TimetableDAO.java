package au.edu.tafesa.itstudies.personal_timetable_android.DAO;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import au.edu.tafesa.itstudies.personal_timetable_android.ConnectDatabase.MysqlCon;
import au.edu.tafesa.itstudies.personal_timetable_android.activities.IndexActivity;
import au.edu.tafesa.itstudies.personal_timetable_android.activities.LoginActivity;
import au.edu.tafesa.itstudies.personal_timetable_android.models.*;



public class TimetableDAO {
    public List<Student>studentList;
    public List<Campus>campusList;
    public List<Assignment>assignmentList;
    public List<Lecturer>lecturerList;
    public List<Course>courseList;
    public List<Session>sessionList;
    public List<Test>testList;

    //JDBC deriver name and URL
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/mydb?"
            + "useUnicode=true&"
            + "characterEncoding=utf-8&"
            + "useSSL=false&"
            + "serverTimezone=GMT%2B8";

    public static final String USER = "generalUser";
    public static final String PASSWORD = "5work";

    //user name and password

    public List<Student> getStudentDAO() {
        Connection cn = null;

        try{
            Class.forName(JDBC_DRIVER);
            cn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            String sql = "SELECT * FROM Student";
            Statement stat = cn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("studentID");
                String name = rs.getString("studentName");
                String password = rs.getString("loginPassword");


                studentList.add(new Student(id,name,password));

            }
            closeAll(cn,stat,rs);
        }
        catch (ClassNotFoundException e){
            System.out.println("Error run driver.");
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            System.out.println("Error. adding data.");
            e.printStackTrace();
        }
        return studentList;
    }

    public void getCampusDAO() {
        Connection cn = null;

        try{
            Class.forName(JDBC_DRIVER);
            cn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            String sql = "SELECT * FROM Campus";
            Statement stat = cn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("campusID");
                String name = rs.getString("campusName");
                String address = rs.getString("address");


                campusList.add(new Campus(id,name,address));

            }
            closeAll(cn,stat,rs);
        }
        catch (ClassNotFoundException e){
            System.out.println("Error run driver.");
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            System.out.println("Error. adding data.");
            e.printStackTrace();
        }
    }

    public void getLecturerDAO() {
        Connection cn = null;

        try{
            Class.forName(JDBC_DRIVER);
            cn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            String sql = "SELECT * FROM Lecturer";
            Statement stat = cn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("lectureID");
                String name = rs.getString("lectureName");

                lecturerList.add(new Lecturer(id,name));

            }
            closeAll(cn,stat,rs);
        }
        catch (ClassNotFoundException e){
            System.out.println("Error run driver.");
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            System.out.println("Error. adding data.");
            e.printStackTrace();
        }
    }

    public void getAssigmentDAO() {
        Connection cn = null;

        try{
            Class.forName(JDBC_DRIVER);
            cn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            String sql = "SELECT * FROM Assigment";
            Statement stat = cn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("assignmentID");
                String name = rs.getString("assignmentName");
                String dutDate = rs.getString("dueDate");
                String courseCode = rs.getString("Course_courseCode");


                assignmentList.add(new Assignment(id,name,dutDate,this.findCourse(courseCode)));

            }
            closeAll(cn,stat,rs);
        }
        catch (ClassNotFoundException e){
            System.out.println("Error run driver.");
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            System.out.println("Error. adding data.");
            e.printStackTrace();
        }
    }

    public void getTestDAO() {
        Connection cn = null;

        try{
            Class.forName(JDBC_DRIVER);
            cn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            String sql = "SELECT * FROM Test";
            Statement stat = cn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("testID");
                String name = rs.getString("Testname");
                String dutDate = rs.getString("dueDate");
                String courseCode = rs.getString("Course_courseCode");

                testList.add(new Test(id,name,dutDate,this.findCourse(courseCode)));

            }
            closeAll(cn,stat,rs);
        }
        catch (ClassNotFoundException e){
            System.out.println("Error run driver.");
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            System.out.println("Error. adding data.");
            e.printStackTrace();
        }
    }


    public void getSessionDAO() {
        Connection cn = null;

        try{
            Class.forName(JDBC_DRIVER);
            cn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            String sql = "SELECT * FROM Session";
            Statement stat = cn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("testID");
                String name = rs.getString("Testname");
                String dutDate = rs.getString("dueDate");
                String courseCode = rs.getString("Course_courseCode");

                testList.add(new Test(id,name,dutDate,this.findCourse(courseCode)));

            }
            closeAll(cn,stat,rs);
        }
        catch (ClassNotFoundException e){
            System.out.println("Error run driver.");
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            System.out.println("Error. adding data.");
            e.printStackTrace();
        }
    }

    public void getCourseDAO() {
        Connection cn = null;

        try{
            Class.forName(JDBC_DRIVER);
            cn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            String sql = "SELECT * FROM Course";
            Statement stat = cn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                String code = rs.getString("courseCode");
                String name = rs.getString("courseName");
                String startDate = rs.getString("startDate");
                String endDate = rs.getString("endDate");
                String startTime = rs.getString("startTime");
                String endTime = rs.getString("endTime");
                String room = rs.getString("room");
                int lecture = rs.getInt("Lecture_lectureID");

                courseList.add(new Course(code,name,startDate,endDate,startTime,endTime,room,this.findLecturer(lecture)));

            }
            closeAll(cn,stat,rs);
        }
        catch (ClassNotFoundException e){
            System.out.println("Error run driver.");
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            System.out.println("Error. adding data.");
            e.printStackTrace();
        }
    }




    // looking for the course fk.
    public Course findCourse(String code) {
        Course course = null;
        for (int i = 0; i < this.courseList.size(); i++)
        {
            if(this.courseList.get(i).getCourseName() == code)
            {
                course = courseList.get(i);
                break;
            }
            else
            {
                course = null;
            }
        }
        return course;
    }

    public Student findStudent(int id) {
        Student student = null;
        for (int i = 0; i < this.studentList.size(); i++)
        {
            if(this.studentList.get(i).getStudentID() == id)
            {
                student = studentList.get(i);
                break;
            }
            else
            {

            }
        }
        return student;
    }

    public Campus findCampus(int id) {
        Campus campus = null;
        for (int i = 0; i < this.campusList.size(); i++)
        {
            if(this.campusList.get(i).getCampusID() == id)
            {
                campus = campusList.get(i);
                break;
            }
            else
            {

            }
        }
        return campus;
    }

    public Lecturer findLecturer(int id){
        Lecturer lecturer = null;
        for (int i = 0; i < this.campusList.size(); i++)
        {
            if(this.lecturerList.get(i).getLecturerID() == id)
            {
                lecturer = lecturerList.get(i);
                break;
            }
            else
            {

            }
        }
        return lecturer;
    }


    public static void closeAll(Connection conn, Statement stmt,ResultSet rs)throws SQLException{
        if(stmt!=null)
        {
            stmt.cancel();
        }
        if (conn!=null)
        {
            conn.close();
        }
        if(rs != null){
            rs.close();
        }
    }


}
