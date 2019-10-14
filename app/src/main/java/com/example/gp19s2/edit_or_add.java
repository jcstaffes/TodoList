package com.example.gp19s2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//@author the whole team
//Alarm manager refer to https://www.jianshu.com/p/3c6a71b55c72


public class edit_or_add extends AppCompatActivity  {
    private TextView mTextMessage;
    private EditText titleEdit;
    private EditText dateEdit;
    private EditText timeEdit;
    private EditText desEdit;
    private static RadioGroup completed;
    private static RadioButton buttonCompleted;
    DatePickerDialog.OnDateSetListener setListener;
    private String get_title;
    private String get_date;
    private String get_time;
    private String get_des;
    private String completed_or_not;
    public int yearAlarm;
    public int monthAlarm;
    public int dayAlarm;
    public int hourAlarm;
    public int minuteAlarm;






//To get information from those EditTexts and RadioGroup in the interface
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_or_add);

        titleEdit=(EditText)findViewById(R.id.TITLE);
        dateEdit=(EditText)findViewById(R.id.DATE);
        timeEdit=(EditText)findViewById(R.id.TIME);
        desEdit= (EditText)findViewById(R.id.DESC);
        completed=(RadioGroup)findViewById(R.id.COMP);
        Calendar calendar=Calendar.getInstance();
        final int year =calendar.get(Calendar.YEAR);
        yearAlarm=year;
        final int month =calendar.get(Calendar.MONTH);
        monthAlarm=month;
        final int day =calendar.get(Calendar.DAY_OF_MONTH);
        dayAlarm=day;
        final int hour=calendar.get(Calendar.HOUR_OF_DAY);
        hourAlarm=hour;
        final int minute=calendar.get(Calendar.MINUTE);
        minuteAlarm=minute;
        final Date date =calendar.getTime();
        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(edit_or_add.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month=month+1;
                        String date=day+"/"+month+"/"+year;
                        dateEdit.setText(date);
                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMinDate(date.getTime()-(date.getTime()%(24*60*60*1000)));
                datePickerDialog.show();
            }
        });
        timeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(edit_or_add.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        String time=hour+":"+minute;
                        timeEdit.setText(time);
                    }
                },hour,minute,true);
                timePickerDialog.show();
            }

        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topnav2,menu);
        return true;
    }

   /* To insert information into the database
    User can also cancel adding*/
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.confirm:
                get_title = titleEdit.getText().toString().trim();
                get_date = dateEdit.getText().toString().trim();
                get_time = timeEdit.getText().toString().trim();
                get_des =desEdit.getText().toString().trim();
                int selected = completed.getCheckedRadioButtonId();
                buttonCompleted=findViewById(selected);
                completed_or_not=buttonCompleted.getText().toString();
                if (TextUtils.isEmpty(get_des)){
                    get_des="no description";
                }
                if (TextUtils.isEmpty(get_title) || TextUtils.isEmpty(get_date)||TextUtils.isEmpty(get_time)) {
                    Toast.makeText(edit_or_add.this, "Please enter something", Toast.LENGTH_SHORT).show();
                }
                else {
                    Database db=new Database(this);
                    boolean add=db.insert(get_title,get_date,get_time,get_des,completed_or_not);
                    if (add){
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR,yearAlarm);
                        calendar.set(Calendar.MONTH,monthAlarm);
                        calendar.set(Calendar.DAY_OF_MONTH,dayAlarm-1);
                        calendar.set(Calendar.HOUR_OF_DAY,hourAlarm);
                        calendar.set(Calendar.MINUTE,minuteAlarm);
                        calendar.set(Calendar.MILLISECOND,0);
                        Alarm.event=get_title;
                        //intent2 is to set Alarm for this item so that alarm will show one day before the due time
                        Intent intent2 = new Intent(edit_or_add.this,Alarm.class);
                        //To send the item title to Alarm class
                        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(),0,intent2,0);
                        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                        //To set Alarm show time
                        am.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pi);
                        Intent intent=new Intent(edit_or_add.this,MainActivity.class);
                        intent.putExtra("Insert",1);
                        Toast.makeText(edit_or_add.this, "data inserted", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(edit_or_add.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }


                break;
            case R.id.cancel:
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_launcher_background)
                        .setTitle("Confirm")
                        .setMessage("Exit without saving")
                        .setNegativeButton("No",null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();

                            }
                        })
                        .show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

