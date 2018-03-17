package com.example.marts.mobilelab2v2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        limit = findViewById(R.id.limitSpinner);
        rate = findViewById(R.id.rateSpinner);
        saveBtn = findViewById(R.id.saveBtn);
        rssUrl = findViewById(R.id.rssUrl);

        String[] limitNumber = new String[]{"10", "20", "50", "100"};
        String[] rates = new String[]{"10","60", "1440"};

        ArrayAdapter<String> limitAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, limitNumber);
        limit.setAdapter(limitAdapter);

        final ArrayAdapter<String> rateAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, rates);
        rate.setAdapter(rateAdapter);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = rssUrl.getText().toString();
                int limitRate = Integer.parseInt(rate.getSelectedItem().toString());
                int limitLimit = Integer.parseInt(limit.getSelectedItem().toString());
                save(text, limitLimit, limitRate);
            }
        });
    }

    /**
     * finishes the activity and sends data back to main
     * @param url - the rss url
     * @param limit - the limit
     * @param rate - the rate
     */
    private void save(String url, int limit, int rate){
        Intent i = new Intent();
        i.putExtra("rssUrl", url);
        i.putExtra("limit", limit);
        i.putExtra("rate", rate);
        setResult(Activity.RESULT_OK, i);
        finish();
    }
}
