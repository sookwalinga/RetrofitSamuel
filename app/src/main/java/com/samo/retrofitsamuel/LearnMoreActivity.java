package com.samo.retrofitsamuel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LearnMoreActivity extends AppCompatActivity {
//    Declaring a webview.
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_more);

//        Instantiating variables.
        webView = (WebView) findViewById(R.id.learn_more);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://square.github.io/retrofit/");

//       Enabling javascript settings on the app for webview.
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }

    }
}