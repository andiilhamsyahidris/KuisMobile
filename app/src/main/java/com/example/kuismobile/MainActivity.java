package com.example.kuismobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toMaps = findViewById(R.id.toMaps);
        Button toCall = findViewById(R.id.toCall);
        Button logout = findViewById(R.id.logout);
        toMaps.setOnClickListener(this);
        toCall.setOnClickListener(this);
        logout.setOnClickListener(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.package.ACTION_LOGOUT");
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("onReceive", "Mauki keluar?");
                finish();
            }
        }, intentFilter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toCall:
                Uri phoneNumber = Uri.parse("081244136611");
                Intent moveCall = new Intent(Intent.ACTION_DIAL, phoneNumber);
                startActivity(moveCall);
                return;
            case R.id.toMaps:
                Intent moveMaps = new Intent(Intent.ACTION_VIEW);
                moveMaps.setPackage("com.google.android.apps.maps");
                startActivity(moveMaps);
                break;
            case R.id.logout:
                logout();
                break;
        }
    }
    public void logout() {
        Intent out = new Intent();
        out.setAction("com.package.ACTION_LOGOUT");
        sendBroadcast(out);
    }
}
