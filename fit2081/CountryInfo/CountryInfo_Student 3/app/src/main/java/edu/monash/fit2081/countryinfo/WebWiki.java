package edu.monash.fit2081.countryinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebWiki extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_wiki);
        webView=findViewById(R.id.web_id);
        webView.setWebViewClient(new WebViewClient());//管理webview
        webView.loadUrl("https://en.wikipedia.org/wiki/" + getIntent().getStringExtra("NAME"));

    }

}
