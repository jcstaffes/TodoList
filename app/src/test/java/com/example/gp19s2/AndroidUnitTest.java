package com.example.gp19s2;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AndroidUnitTest {

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
}