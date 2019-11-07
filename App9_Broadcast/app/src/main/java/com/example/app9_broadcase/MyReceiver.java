package com.example.app9_broadcase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.err.println("------------------");
        Toast.makeText(context, "---------------", Toast.LENGTH_SHORT).show();
    }
}
