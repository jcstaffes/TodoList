package com.example.gp19s2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class edit_or_add extends AppCompatActivity  {
    private TextView mTextMessage;
    private EditText titleEdit;
    private EditText dateEdit;
    private EditText timeEdit;
    private EditText desEdit;
    DatePickerDialog.OnDateSetListener setListener;
    private String get_title;
    private String get_date;
    private String get_time;
    private String get_des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_or_add);

        titleEdit=(EditText)findViewById(R.id.TITLE);
        dateEdit=(EditText)findViewById(R.id.DATE);
        timeEdit=(EditText)findViewById(R.id.TIME);
        desEdit= (EditText)findViewById(R.id.DESC);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.confirm:
                get_title = titleEdit.getText().toString().trim();
                get_date = dateEdit.getText().toString().trim();
                get_time = timeEdit.getText().toString().trim();
                get_des =desEdit.getText().toString().trim();
                if (TextUtils.isEmpty(get_title) || TextUtils.isEmpty(get_date)||TextUtils.isEmpty(get_time)) {
                    Toast.makeText(edit_or_add.this, "Pleasr enter something", Toast.LENGTH_SHORT).show();
                }
                else {
                    Database db=new Database(this);
                    boolean add=db.insert(get_title,get_date,get_time,get_des);
                    if (add){
                        Intent intent=new Intent(edit_or_add.this,MainActivity.class);
                        intent.putExtra("Insert",1);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(edit_or_add.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }

//
//                Intent intent=new Intent(edit_or_add.this,MainActivity.class);
//                startActivity(intent);
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

