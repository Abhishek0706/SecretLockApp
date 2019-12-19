package com.example.secretlockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecretScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_screen);

        Button open_settings = findViewById(R.id.button_open_settings);

        open_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecretLock myLock = new SecretLock();
                myLock.openSettings(SecretScreen.this);
            }
        });
    }
}
