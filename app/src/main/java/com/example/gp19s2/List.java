package com.example.gp19s2;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


public class List extends AppCompatActivity {
    private TextView mTextMessage;
    Database database;
    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(List.this,List.class);
                    startActivity(intent);
                    break;

                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    break;
            }
            return false;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        database = new Database(this);
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> tempList = new ArrayList<>();
        Cursor list = database.getList();
        if (list.getCount()==0){
            Toast.makeText(this,"No data",Toast.LENGTH_LONG).show();
        }else{
            while(list.moveToNext()){
                StringBuilder temp = new StringBuilder();
                temp.append("Title: "+list.getString(0));
                temp.append("Date: "+list.getString(1));
                temp.append("Time: "+list.getString(2));
                temp.append("Description: "+list.getString(3)+"\n\n");
                tempList.add(temp.toString());
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tempList);
                listView.setAdapter(listAdapter);
            }
        }

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
