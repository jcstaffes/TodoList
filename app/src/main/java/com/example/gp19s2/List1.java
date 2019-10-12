package com.example.gp19s2;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


public class List1 extends AppCompatActivity {
    ListView listView;
    private TextView mTextMessage;
    Database database;
    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    ArrayList<String> tempString = new ArrayList<>();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(List1.this, List.class);
                    startActivity(intent);
                    break;

                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
                    Intent intent1 = new Intent(List1.this,Calendar.class);
                    startActivity(intent1);
                    break;
            }
            return false;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list1);
//        database = new Database(this);
        listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> tempList = new ArrayList<>();
        Cursor list = Calendar.currentdaylist;
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (list.getCount()==0){
            Toast.makeText(this,"No data",Toast.LENGTH_LONG).show();
        }else{
            while(list.moveToNext()){
                StringBuilder temp = new StringBuilder();
                temp.append("ID: "+list.getString(0)+", ");
                temp.append("Title: "+list.getString(1)+", ");
                temp.append("Date: "+list.getString(2)+", ");
                temp.append("Time: "+list.getString(3)+", ");
                temp.append("Description: "+list.getString(4)+"\n\n");
                temp.append("Completed or not: "+list.getString(5)+"\n\n");
                tempList.add(temp.toString());
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tempList);
                listView.setAdapter(listAdapter);
                tempString.add(list.getString(0));
            }
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                final String temp = String.valueOf(l);
//                final Integer temp2 = Integer.parseInt(temp);
//                String changedID=tempString.get(temp2);
//                Intent intent = new Intent(List1.this,edit.class);
//                intent.putExtra("IDtoChange",changedID);
//                intent.putExtra("ListDetail",listView.getItemAtPosition(i).toString());
//
//                startActivity(intent);
                Intent intent = new Intent(List1.this,edit.class);
                intent.putExtra("ListDetail",listView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topnav,menu);
        return true;
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
