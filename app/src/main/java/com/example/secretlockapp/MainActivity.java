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


        Intent i = new Intent(this, SecretScreen.class);
        if (new SecretLock().getLockValue(getApplicationContext())) {
            startActivity(i);
        }

    }
}
