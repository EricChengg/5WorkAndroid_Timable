package au.edu.tafesa.itstudies.personal_timetable_android;

import org.junit.Test;

import java.time.LocalDateTime;

import au.edu.tafesa.itstudies.personal_timetable_android.activities.IndexActivity;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private IndexActivity indexActivity = new IndexActivity();

    //Today is Mon 23/12/2018
    @Test
    public void Test_toStringOfDate(){
        String i = this.indexActivity.toStringOfDate(LocalDateTime.now());
        assertEquals("Mon",i);
    }

    @Test
    public void Test_toStringOfMonth(){
        String i = this.indexActivity.toStringOfMonth(LocalDateTime.now());
        assertEquals("Dec",i);
    }
}