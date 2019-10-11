package com.example.gp19s2;

import androidx.appcompat.app.AppCompatActivity;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Calendar extends AppCompatActivity {
    private TextView mTextMessage;
    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    Database database;
    private TextView currentdaytext;
    public static Cursor currentdaylist;
    Cursor alldaylist;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(Calendar.this,List.class);
                    startActivity(intent);
                    return true;

                case R.id.navigation_notifications:
                    Intent intent1 = new Intent(Calendar.this,Calendar.class);
                    startActivity(intent1);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        final ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(false);
        actionbar.setTitle(null);

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        database = new Database(this);

        //Set an event
        //Todo: read in an event title from database
//        Event ev1 = new Event(Color.RED, 1569910170L, "National Day");
//        compactCalendar.addEvent(ev1);

        alldaylist=database.getList();
        ArrayList<String> tempList = new ArrayList<>();
        while (alldaylist.moveToNext()){
            tempList.add(alldaylist.getString(2));
        }
//        10/10/2019
        for (int i=0;i<tempList.size();i++){
            String day=tempList.get(i).substring(0,2);
            String month=tempList.get(i).substring(3,5);
            String year=tempList.get(i).substring(6);
            //String tsStr = "2011-05-09 11:49:45";
            String str=year+"-"+month+"-"+day+" 09:00:00";
            Timestamp ts = Timestamp.valueOf(str);
            Event event=new Event(Color.RED,ts.getTime(),"");
            compactCalendar.addEvent(event);
        }

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                String currentmonth=dateClicked.toString().substring(4,7);
                String currentday=dateClicked.toString().substring(8,10);
                String currentyear=dateClicked.toString().substring(24);
                String date=currentday+"/"+changeMonth(currentmonth)+"/"+currentyear;
                currentdaylist = database.getListCurrentDay(date);
                if (currentdaylist.getCount()!=0){
                    Intent intent = new Intent(Calendar.this,List1.class);
                    startActivity(intent);
                }
//                if(dateClicked.toString().compareTo("Tue Oct 01 06:09:30 AST 2019") == 0){
//                    Toast.makeText(context, "National Day", Toast.LENGTH_SHORT).show();
//                }
                else{
                    Toast.makeText(context, "No Events for that day", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionbar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topnav,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent intent = new Intent(this,edit_or_add.class);
                startActivity(intent);
                break;
            case R.id.item2:
                Toast.makeText(this,"item2",Toast.LENGTH_LONG).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public String changeMonth(String month){
        String m=null;
        switch (month){
            case "Jan":
                m="01";
                break;
            case "Feb":
                m="02";
                break;
            case "Mar":
                m="03";
                break;
            case "Apr":
                m="04";
                break;
            case "May":
                m="05";
                break;
            case "Jun":
                m="06";
                break;
            case "Jul":
                m="07";
                break;
            case "Aug":
                m="08";
                break;
            case "Sep":
                m="09";
                break;
            case "Oct":
                m="10";
                break;
            case "Nov":
                m="11";
                break;
            case "Dec":
                m="12";
                break;
        }
        return m;
    }
}
