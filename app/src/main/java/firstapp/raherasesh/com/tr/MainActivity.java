package firstapp.raherasesh.com.tr;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import firstapp.raherasesh.com.tr.model.Kurdpress;
import firstapp.raherasesh.com.tr.model.Kurdtoday;
import firstapp.raherasesh.com.tr.model.News;
import firstapp.raherasesh.com.tr.model.NewsAdapter;

public class MainActivity extends Activity {
public volatile List<News> newsList;
    boolean finishedKurdpress,finishedKurdToday;

    Thread trKurdpress;
    Thread trKurdToday;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         recyclerView= (RecyclerView) findViewById(R.id.recyclerNews);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        // StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(mLayoutManager);
         EventBus bus = EventBus.getDefault();
        bus.register(this);
        newsList=new ArrayList<>();

Kurdpress kurdpress=new Kurdpress(newsList);
        Kurdtoday kurdtoday=new Kurdtoday(newsList);

         trKurdpress=new Thread(kurdpress);
         trKurdToday=new Thread(kurdtoday);
        trKurdpress.start();
        trKurdToday.start();



        try {
            trKurdpress.join();
            trKurdToday.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

fillRecycler();
    }



    public void fillRecycler(){

            NewsAdapter newsAdapter=new NewsAdapter(newsList,this);
            recyclerView.setAdapter(newsAdapter);
newsAdapter.notifyDataSetChanged();


        }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doAction(News news)  {

        String newsUrl =news.getNewsUrl();


        Log.e("url is=,",newsUrl);
        Intent i=new Intent(this,CompleteNewsActivity.class);
        i.putExtra("newsURL",newsUrl);
        startActivity(i);
    }

    }

