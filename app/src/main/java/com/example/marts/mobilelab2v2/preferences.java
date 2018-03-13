package com.example.marts.mobilelab2v2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class preferences extends AppCompatActivity {

    Spinner limit;
    Spinner rate;
    Button saveBtn;
    TextView rssUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        limit = findViewById(R.id.limitSpinner);
        rate = findViewById(R.id.rateSpinner);
        saveBtn = findViewById(R.id.saveBtn);
        rssUrl = findViewById(R.id.rssUrl);

        String[] limitMins = new String[]{"10","60", "1440"};
        String[] rates = new String[]{"10", "20", "50", "100"};

        ArrayAdapter<String> limitAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, limitMins);
        limit.setAdapter(limitAdapter);

        ArrayAdapter<String> rateAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, rates);
        rate.setAdapter(rateAdapter);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                String text = rssUrl.getText().toString();
                int limitRate = Integer.parseInt(rate.getSelectedItem().toString());
                int limitLimit = Integer.parseInt(limit.getSelectedItem().toString());
                editor.putString("rssUrl", text);
                editor.putInt("rate", limitRate);
                editor.putInt("limit", limitLimit);
                editor.apply();
            }
        });
    }
}
