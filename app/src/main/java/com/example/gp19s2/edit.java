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
import java.util.Calendar;
import java.util.Date;



public class edit extends AppCompatActivity{
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
    public int yearAlarm2;
    public int monthAlarm2;
    public int dayAlarm2;
    public int hourAlarm2;
    public int minuteAlarm2;





    /*@author the whole team
    Alarm manager refer to https://www.jianshu.com/p/3c6a71b55c72
    Similiar to edit_or_add
    To edit the existing items*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        titleEdit=(EditText)findViewById(R.id.TITLE2);
        dateEdit=(EditText)findViewById(R.id.DATE2);
        timeEdit=(EditText)findViewById(R.id.TIME2);
        desEdit= (EditText)findViewById(R.id.DESC2);
        completed=(RadioGroup)findViewById(R.id.COMP2);
        Calendar calendar=Calendar.getInstance();
        final int year =calendar.get(Calendar.YEAR);
        final int month =calendar.get(Calendar.MONTH);
        final int day =calendar.get(Calendar.DAY_OF_MONTH);
        final int hour=calendar.get(Calendar.HOUR_OF_DAY);
        final int minute=calendar.get(Calendar.MINUTE);
        final Date date =calendar.getTime();
        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(edit.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month=month+1;
                        String date=day+"/"+month+"/"+year;
                        dateEdit.setText(date);
                        yearAlarm2=year;
                        monthAlarm2=month;
                        dayAlarm2=day;
                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMinDate(date.getTime()-(date.getTime()%(24*60*60*1000)));
                datePickerDialog.show();
            }
        });
        timeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(edit.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        String time=hour+":"+minute;
                        timeEdit.setText(time);
                        hourAlarm2=hour;
                        minuteAlarm2=minute;
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
                Database thisDB = new Database(this);
                //To receive the ID of the item which is clicked from the List activity and search the row whose ID is this ID
                String idToedit = getIntent().getStringExtra("IDtoChange");
                Cursor c = thisDB.search(idToedit);
                System.out.println(c);
                //If user change some columns, this information will be changed in the database, otherwise the information will keep the same
                String res_title="";
                String res_date="";
                String res_time="";
                String res_des="";
                while(c.moveToNext()){
                if (TextUtils.isEmpty(get_title)){
                    res_title=c.getString(1);
                }else{
                    res_title=get_title;
                }
                if (TextUtils.isEmpty(get_date)){
                    res_date=c.getString(2);
                }else{
                    res_date=get_date;
                }
                if (TextUtils.isEmpty(get_time)){
                    res_time=c.getString(3);
                }else{
                    res_time=get_time;
                }
                if (TextUtils.isEmpty(get_des)){
                    res_des=c.getString(4);
                }else{
                    res_des=get_des;
                }}
                boolean isUpdate = thisDB.updateData(idToedit,res_title,res_date,res_time,res_des,completed_or_not);
                if (isUpdate){
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.set(Calendar.YEAR,yearAlarm2);
                    calendar2.set(Calendar.MONTH,monthAlarm2-1);
                    calendar2.set(Calendar.DAY_OF_MONTH,dayAlarm2-1);
                    calendar2.set(Calendar.HOUR_OF_DAY,hourAlarm2);
                    calendar2.set(Calendar.MINUTE,minuteAlarm2);
                    calendar2.set(Calendar.MILLISECOND,0);
                    Alarm.event=get_title;
                    Intent intent2 = new Intent(edit.this,Alarm.class);
                    PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(),0,intent2,0);
                    AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                    am.set(AlarmManager.RTC_WAKEUP,calendar2.getTimeInMillis(),pi);
                    Intent intent=new Intent(edit.this,List.class);
                    intent.putExtra("Update",1);
                    Toast.makeText(edit.this, "Data updated", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    Toast.makeText(edit.this, "fail to update data", Toast.LENGTH_SHORT).show();
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
