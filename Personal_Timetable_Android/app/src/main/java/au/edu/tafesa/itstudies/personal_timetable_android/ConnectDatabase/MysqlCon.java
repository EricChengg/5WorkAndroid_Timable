package au.edu.tafesa.itstudies.personal_timetable_android.ConnectDatabase;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import au.edu.tafesa.itstudies.personal_timetable_android.models.Student;

public class MysqlCon {

    //JDBC deriver name and URL

    public List<Student> studentList;
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://192.168.64.2:8443/testingTimetable?"
            + "useUnicode=true&"
            + "characterEncoding=utf-8&"
            + "useSSL=false&"
            + "serverTimezone=GMT%2B8";

//    public static final String DB_URL = "jdbc:mysql://10.0.2.2:3306/mydb?"
//            + "useUnicode=true&"
//            + "characterEncoding=utf-8&"
//            + "useSSL=false&"
//            + "serverTimezone=GMT%2B8";

    public static final String USER = "test";
    public static final String PASSWORD = "12345";

    //user name and password

    public void getStudent() {
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



