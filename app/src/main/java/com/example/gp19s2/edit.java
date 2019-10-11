package com.example.gp19s2;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
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
    //private EditText idEdit;
    private static RadioGroup completed;
    private static RadioButton buttonCompleted;
    DatePickerDialog.OnDateSetListener setListener;
    private String get_title;
    private String get_date;
    private String get_time;
    private String get_des;
    //private String get_id;
    private String completed_or_not;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        //idEdit=findViewById(R.id.ID2);
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
                System.out.println(get_title);
                get_date = dateEdit.getText().toString().trim();
                get_time = timeEdit.getText().toString().trim();
                get_des =desEdit.getText().toString().trim();
                //get_id=idEdit.getText().toString().trim();
                int selected = completed.getCheckedRadioButtonId();
                buttonCompleted=findViewById(selected);
                completed_or_not=buttonCompleted.getText().toString();
                Database thisDB = new Database(this);
                String idToedit = getIntent().getStringExtra("IDtoChange");
                Cursor c = thisDB.search(idToedit);
                //Cursor c = thisDB.search(get_id);
                System.out.println(c);
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
                    Intent intent=new Intent(edit.this,List.class);
                    intent.putExtra("Update",1);
                    Toast.makeText(edit.this, "Data updated", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    Toast.makeText(edit.this, "fail to update data", Toast.LENGTH_SHORT).show();
                }

                /*if (TextUtils.isEmpty(get_des)){
                    get_des="no description";
                }
                if (TextUtils.isEmpty(get_title) || TextUtils.isEmpty(get_date)||TextUtils.isEmpty(get_time)||TextUtils.isEmpty(get_id)) {
                    Toast.makeText(edit.this, "Please enter something", Toast.LENGTH_SHORT).show();
                }
                else {
                    Database db=new Database(this);
                    boolean add=db.insert(get_title,get_date,get_time,get_des,completed_or_not);
                    if (add){
                        Intent intent=new Intent(edit.this,MainActivity.class);
                        intent.putExtra("Insert",1);
                        Toast.makeText(edit.this, "data inserted", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(edit.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }*/

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
