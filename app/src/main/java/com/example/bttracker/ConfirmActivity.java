package com.example.bttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmActivity extends AppCompatActivity implements View.OnClickListener {

    TextView accessData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        // find the ID of the TextView on the layout
        accessData = (TextView)findViewById(R.id.showBTData);

        // Extract the extras from Intent
        Bundle transferredData = getIntent().getExtras();
        // Extract data with the key "Your BT data"
        String s = transferredData.getString("@string/bt_data");
        // create the confirmation message
        String txt = "Your input \nbody temperature is \n\n" + s + "\u00B0C";
        // show the confirmation message in the TextView
        accessData.setText(txt);


        Button backHome = findViewById(R.id.backHomeButton);
        backHome.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backHomeButton:
                Intent toMain = new Intent(this, MainActivity.class);
                startActivity(toMain);
                break;
        }
    }
}
