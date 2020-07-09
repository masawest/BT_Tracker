package com.example.bttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogActivity extends AppCompatActivity implements View.OnClickListener {

    EditText userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        // get the ID of the EditText View
        userInput = (EditText)findViewById(R.id.LogInputData);

        Button logSave = findViewById(R.id.LogSaveButton);
        logSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.LogSaveButton:
                Intent toConfirm = new Intent(this, ConfirmActivity.class);
                // 下記は、append user input to the toConfirm Intent
                // the second argument means: get user input from the EditText View,
                // and convert the text to a string
                toConfirm.putExtra("@string/bt_data", userInput.getText().toString());
                startActivity(toConfirm);
                break;
        }
    }
}
