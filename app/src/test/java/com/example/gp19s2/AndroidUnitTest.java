package com.example.gp19s2;

import android.database.Cursor;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AndroidUnitTest {
    Database db=new Database(null);
    Calendar c = new Calendar();

    @Test
    public void testCalendar() {
        String month = "Jan";
        assertEquals("01", c.changeMonth(month));
        String month2 = "Mar";
        assertEquals("03", c.changeMonth(month2));
        String month3 = "Jul";
        assertEquals("07", c.changeMonth(month3));
    }

    @Test
    public void testDatabaseInsert(){
        db.insert("abcd","01/10/2019","09:00:00","test the data","Not Completed");
        Cursor list=db.getList();
        assertEquals(list.getCount(),1);
    }
}