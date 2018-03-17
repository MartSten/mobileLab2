package com.example.marts.mobilelab2v2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button testBtn;
    Button prefBtn;
    int code = 123;
    String url;
    int rate;
    int limit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        testBtn = findViewById(R.id.testBtn);
        prefBtn = findViewById(R.id.prefBtn);

        prefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), preferences.class);
                startActivityForResult(i, code);
            }


        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (code == requestCode) {
            if (resultCode == RESULT_OK) {
                url = data.getStringExtra("rssUrl");
                limit = data.getIntExtra("limit", 10);
                rate = data.getIntExtra("rate", 10);
                //Saves the preferences
                savePreferences(url, limit, rate);
                //Loads the xml content in to the recyclerView
                RssReader rssReader = new RssReader(this, recyclerView);
                rssReader.execute();
            }
        }
    }

    /**
     * Saves the url, the limit, and the rate to the app preferences
     * @param url - the xml url
     * @param limit - the number of items shown in the recyclerView
     * @param rate - the rate of how often the recycler is updated (not used)
     */
    private void savePreferences(String url, int limit, int rate) {
        SharedPreferences pref = getSharedPreferences("lab2Prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if(url.isEmpty()){
            url = "https://news.google.com/news/rss/?ned=us&gl=US&hl=en";   //FOR TESTING ONLY
        }
        editor.putInt("limit", limit);
        editor.putInt("rate", rate);
        editor.putString("rssUrl", url);
        editor.apply();
    }
}

/* Links to different rss feeds. Have been used for testing.
https://www.computerworlduk.com/mobile/rss
http://www.nrk.no/toppsaker.rss
https://news.google.com/news/rss/?ned=us&gl=US&hl=en
https://www.vg.no/rss/feed/?categories=1068&keywords=&limit=10&format=rss
 */