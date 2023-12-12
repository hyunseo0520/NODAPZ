package com.example.trip_project.hotel;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.trip_project.R;

public class WebActivity extends AppCompatActivity {
    private String TAG = WebActivity.class.getSimpleName();

    private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = (WebView) findViewById(R.id.webview);

        webView.setWebViewClient(new WebViewClient());  // 새 창 띄우기 않기
        webView.setWebChromeClient(new WebChromeClient());

        webView.getSettings().setLoadWithOverviewMode(true);  // WebView 화면크기에 맞추도록 설정 - setUseWideViewPort 와 같이 써야함
        webView.getSettings().setUseWideViewPort(true);  // wide viewport 설정 - setLoadWithOverviewMode 와 같이 써야함

        webView.getSettings().setSupportZoom(false);  // 줌 설정 여부
        webView.getSettings().setBuiltInZoomControls(false);  // 줌 확대/축소 버튼 여부

        webView.getSettings().setDomStorageEnabled(true);  // 로컬 스토리지 (localStorage) 사용여부

        webView.loadUrl("https://www.google.co.kr/travel/hotels/%ED%94%8C%EB%A1%9C%EB%A0%8C%EC%8A%A4/entity/CgsI3Z-EmoqriN_3ARAB?utm_campaign=sharing&utm_medium=link&utm_source=htls&ved=0CAAQ5JsGahcKEwi4vbbwuoqDAxUAAAAAHQAAAAAQBA&ts=CAEaIAoCGgASGhIUCgcI5w8QDBgOEgcI5w8QDBgPGAEyAhAAKgkKBToDS1JXGgA");
    }

}