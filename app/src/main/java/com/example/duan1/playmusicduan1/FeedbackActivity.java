package com.example.duan1.playmusicduan1;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {
    String url;
    WebView webViewFeedback;
    ProgressDialog progressDialogFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        webViewFeedback = (WebView) findViewById(R.id.webviewFeedback);
        url = "https://docs.google.com/forms/d/1Yh85fzDqke8eoFxyEIkWJcmiJheRjUY7gLJSokt7-FE";
        webViewFeedback.loadUrl(url);
        WebSettings webSettingsFeedback = webViewFeedback.getSettings();
        webSettingsFeedback.setJavaScriptEnabled(true);
        webSettingsFeedback.setBuiltInZoomControls(true);
        webSettingsFeedback.setAppCacheEnabled(true);
        webSettingsFeedback.setDisplayZoomControls(true);
        progressDialogFeedback = new ProgressDialog(FeedbackActivity.this);
        webViewFeedback.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                try {progressDialogFeedback.setTitle("Please wait !!!");
                    progressDialogFeedback.setMessage("Process loading ...");
                    progressDialogFeedback.setIndeterminate(true);
                    progressDialogFeedback.setCancelable(true);
                    progressDialogFeedback.setCanceledOnTouchOutside(true);
                    progressDialogFeedback.show();}catch (Exception e){}
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                try {progressDialogFeedback.dismiss();}catch (Exception e){}

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option_reload,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.opt_reload:
                webViewFeedback.reload();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
