package com.example.risa.studentportal;

import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
public class WebMain extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webpage_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(getIntent().getStringExtra("URL"));
    }
}
