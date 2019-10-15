package com.example.gp19s2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class editTest {
    private String mTitle="Test";
    private String mDES = "Have a good markz!";
    private edit medit=null;
    @Rule
    public IntentsTestRule<edit> activityRule
            = new IntentsTestRule<>(edit.class);


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
    @Test
    public void testDelete(){
        String t="";
        Context context=medit.getApplicationContext();
        Database thisDB = new Database(context);
        thisDB.insert("Test","10/11/2019","3:03","have a test!",null);
        thisDB.insert("Test11","10/12/2019","3:03","have a test!",null);
        thisDB.deleteData("01");
        Cursor c=thisDB.getList();
        while (c.moveToNext()){
            t=c.getString(1);
        }
        assertEquals(t,"Test11");
    }

//    @Test
//    public void testUpdate(){
//        String t="";
//        Context context=medit.getApplicationContext();
//        Database thisDB = new Database(context);
//        thisDB.insert("Test","10/11/2019","3:03","have a test!",null);
//        thisDB.updateData("1","Edit","10/11/2019","3:03","have a test!",null);
//        Cursor c=thisDB.getList();
//        while (c.moveToNext()){
//            t=c.getString(1);
//        }
//        assertEquals(t,"Edit");
//    }
    @Test
    public void testGetCurrentDayList(){
        String t="";
        Context context=medit.getApplicationContext();
        Database thisDB = new Database(context);
        thisDB.insert("Test","10/11/2019","3:03","have a test!",null);
        thisDB.insert("Edit","10/11/2019","3:03","have a test!",null);
        thisDB.insert("Add","10/12/2019","3:03","have a test!",null);
        Cursor c=thisDB.getListCurrentDay("10/12/2019");
        while (c.moveToNext()){
            t=c.getString(1);
        }
        assertEquals(t,"Add");
    }
//    @Test
//    public void testSearch(){
//        String t="";
//        Context context=medit.getApplicationContext();
//        Database thisDB = new Database(context);
//        thisDB.insert("Test","10/11/2019","3:03","have a test!",null);
//        thisDB.insert("Edit","10/11/2019","3:03","have a test!",null);
//        thisDB.insert("Add","10/12/2019","3:03","have a test!",null);
//        Cursor c=thisDB.search("3");
//        while (c.moveToNext()){
//            t=c.getString(1);
//        }
//        assertEquals(t,"Add");
//    }
    @Test
    public void testIntent() {

        Espresso.onView(withId(R.id.TITLE2)).perform(typeText(mTitle));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.confirm)).perform(click());
        intended(hasComponent(List.class.getName()));


    }

}