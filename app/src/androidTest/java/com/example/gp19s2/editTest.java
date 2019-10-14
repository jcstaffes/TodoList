package com.example.gp19s2;

import android.app.Activity;

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
    @Rule
    public ActivityTestRule<edit> activityRule
            = new ActivityTestRule<>(edit.class);
    @Before
    public void setUp() throws Exception {
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
    public void user_type_des() {
        Espresso.onView(withId(R.id.DESC2)).perform(typeText(mDES));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.DESC2)).check(matches(withText(mDES)));
    }

}