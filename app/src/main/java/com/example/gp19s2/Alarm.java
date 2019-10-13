package com.example.gp19s2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"There is one task due this time tomorrow, pls go to todo list to check",Toast.LENGTH_LONG).show();

    }
}
