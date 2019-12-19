package com.example.secretlockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SecretLock secretLock = new SecretLock();
        HashMap<String, Boolean> myMap = new HashMap<>();
        myMap.put("wifi_status", true);
        myMap.put("bluetooth_status", true);
        myMap.put("airplanemode_status", false);

        secretLock.setPreferenceValues(getApplicationContext(), myMap);

        Intent i = new Intent(this, SecretScreen.class);
        if (secretLock.getLockValue(getApplicationContext())) {
            startActivity(i);
        }
    }
}
