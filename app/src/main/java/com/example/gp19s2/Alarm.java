package com.example.gp19s2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Alarm extends BroadcastReceiver {
    public static ArrayList<String> eventAlarm=new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context,"There is one task due this time tomorrow, pls go to todo list to check",Toast.LENGTH_LONG).show();
        for (int i=0;i<eventAlarm.size();i++){
            Toast.makeText(context,eventAlarm.get(i),Toast.LENGTH_LONG).show();
        }
//        Toast.makeText(context,eventAlarm.get(0),Toast.LENGTH_LONG).show();

    }
}
