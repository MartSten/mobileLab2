package com.example.marts.mobilelab2v2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button testBtn;
    Button prefBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        testBtn = findViewById(R.id.testBtn);
        prefBtn = findViewById(R.id.prefBtn);

        RssReader rssReader = new RssReader(this, recyclerView);
        rssReader.execute();
    }
}
