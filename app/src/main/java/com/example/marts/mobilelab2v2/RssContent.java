package com.example.marts.mobilelab2v2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Class that shows the content of the WebView
 */
public class RssContent extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_content);

        webView = findViewById(R.id.webView);
        final Bundle bundle = getIntent().getExtras();

        //ALL graphics, included adds, should load with the following settings
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);

        //Makes all links open in the WebView (before this was added some would open the default browser instead)
        webView.setWebViewClient(new WebViewClient());

        //Loads the url in the WebView
        webView.loadUrl(bundle.getString("link"));
    }
}
