package com.example.gp19s2;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class editTest {
    private String mTitle="Test";
    private String mDES = "Have a good markz!";
    private edit medit=null;
    @Rule
    public ActivityTestRule<edit> activityRule
            = new ActivityTestRule<>(edit.class);

    @Before
    public void setUp() throws Exception {
medit=activityRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void user_type_title() {
        Espresso.onView(withId(R.id.TITLE2)).perform(typeText(mTitle));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.TITLE2)).check(matches(withText(mTitle)));
    }
    @Test
    public void user_type_des() {
        Espresso.onView(withId(R.id.DESC2)).perform(typeText(mDES));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.DESC2)).check(matches(withText(mDES)));
    }
   @Test
    public void testLauch() {
        View view=medit.findViewById(R.id.DATE2);
        View view2=medit.findViewById(R.id.TIME2);
        assertNotNull(view);
        assertNotNull(view2);
    }
    @Test
    public void testInsert() {
        String title_get="";
        String date_get="";
        String time_get="";
        String Des_get="";

        Context context=medit.getApplicationContext();
        Database thisDB = new Database(context);
        thisDB.insert("Test","10/11/2019","3:03","have a test!",null);
        Cursor c=thisDB.getList();
        while (c.moveToNext()){
             title_get= c.getString(1);
             date_get=c.getString(2);
             time_get=c.getString(3);
             Des_get=c.getString(4);

        }
        assertEquals("Test",title_get);
        assertEquals("10/11/2019",date_get);
        assertEquals("3:03",time_get);
        assertEquals("have a test!",Des_get);


    }

}