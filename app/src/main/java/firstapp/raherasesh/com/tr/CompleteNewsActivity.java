package firstapp.raherasesh.com.tr;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import firstapp.raherasesh.com.tr.model.Kurdpress;
import firstapp.raherasesh.com.tr.model.Kurdtoday;
import firstapp.raherasesh.com.tr.model.News;
import firstapp.raherasesh.com.tr.model.NewsAdapter;

public class CompleteNewsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completenews);

       String newsUrl= getIntent().getStringExtra("newsURL");
        WebView webView= (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(newsUrl);
    }

    }

