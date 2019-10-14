package com.example.gp19s2;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/*@author Jiayi Bian
To set alarm action
The static String @event is called in the @edit_or_add class and @edit class
To show the title of the alarm event
*/
public class Alarm extends BroadcastReceiver {
    public static String event;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,event+" due tomorrow, pls go to todo list to check",Toast.LENGTH_LONG).show();
    }
}
