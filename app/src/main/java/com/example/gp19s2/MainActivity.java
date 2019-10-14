package com.example.gp19s2;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.usage.UsageEvents;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.app.AlarmManager.ELAPSED_REALTIME_WAKEUP;

//Go to different activity by click different item
public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    Database database;
    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());


//    private void startAlarm(String t){
//        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
//        am.s
//    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(MainActivity.this,List.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    Intent i = new Intent(MainActivity.this , Calendar.class);
                    startActivity(i);
                    return true;
            }
            return false;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        final ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(false);
        actionbar.setTitle(null);




//        AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);;
//        Cursor alldaylist=database.getList();
//        ArrayList<String> tempListday = new ArrayList<>();
//        ArrayList<String> tempListtime = new ArrayList<>();
//        while (alldaylist.moveToNext()){
//            tempListday.add(alldaylist.getString(2));
//            tempListtime.add(alldaylist.getString(3));
//        }
//        Intent alarm = new Intent(MainActivity.this, Calendar.class);
//        PendingIntent sender = PendingIntent.getBroadcast(
//                MainActivity.this, 0, alarm, 0);
//        for (int i=0;i<tempListday.size();i++){
//            String day=tempListday.get(i).substring(0,2);
//            String month=tempListday.get(i).substring(3,5);
//            String year=tempListday.get(i).substring(6);
//            //String tsStr = "2011-05-09 11:49:45";
//            String time=tempListtime.get(i);
//            String str=year+"-"+month+"-"+day+" "+time;
//            Timestamp ts = Timestamp.valueOf(str);
//            alarmManager.set(ELAPSED_REALTIME_WAKEUP,ts.getTime(),sender);
//        }

//        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
//        compactCalendar.setUseThreeLetterAbbreviation(true);

        //Set an event
        //Todo: read in an event title from database
//        Event ev1 = new Event(Color.RED, 1569910170L, "National Day");
//        compactCalendar.addEvent(ev1);
//
//        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
//            @Override
//            public void onDayClick(Date dateClicked) {
//                Context context = getApplicationContext();
//
//                if(dateClicked.toString().compareTo("Tue Oct 01 06:09:30 AST 2019") == 0){
//                    Toast.makeText(context, "National Day", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(context, "No Events for that day", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onMonthScroll(Date firstDayOfNewMonth) {
//                actionbar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
//
//            }
//        });
        database=new Database(this);

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





}
