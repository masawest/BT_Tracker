package com.example.bttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NormalActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        Button backHome2 = findViewById(R.id.backHomeButton2);
        backHome2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backHomeButton2:
                Intent toMain2 = new Intent(this, MainActivity.class);
                startActivity(toMain2);
                break;
        }
    }
}
