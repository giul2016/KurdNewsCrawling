package firstapp.raherasesh.com.tr.model;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sh-Java on 12/23/2016.
 */
public class Kurdtoday implements Runnable {
    Document doc;
    List<News> newsList;

    public Document getDoc() {
        return doc;
    }

    public Kurdtoday(List<News> newsList) {
        this.newsList = newsList;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    private void connectToSite(){
        System.out.println("creating thread kurdtoday");


        try {
            doc = Jsoup.connect("http://www.kurdtoday.ir/").timeout(15000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void run() {
        System.out.println("run thread kurdtoday");

        connectToSite();
        extractNews();

    }


    private List<News> extractNews(){


        Elements divs = doc.select("div.body-news");


        Elements lis=   divs.select("li");
        System.out.println("li="+ lis.size());



        for (Element element:lis){
            //   Element ul = element.select("ul.dNon").first();

            String imageUrl=  element.select("div.news-pic").first().select("a").select("img").first().absUrl("src");
            Element elementNews=element.select("div.news-text").first();

            Element h2=  elementNews.select("h2").first();
            String title=h2.text();
            String newsUrl=  h2.select("a").attr("abs:href");


            String summary=  elementNews.select("p").text();


            Log.e("kurdtoday image=",imageUrl);
            System.out.println('\n'+" in kurdtoday title:"+title);
            System.out.println('\n'+" in kurdtoday image url:"+imageUrl);
            System.out.println('\n'+"in kurdtoday summary:"+summary);
            System.out.println('\n'+" in kurdtoday news url:"+newsUrl);

            News news=new News();
            news.setImageUrl(imageUrl);
            news.setSummary(summary);
            news.setTitle(title);
            news.setNewsUrl(newsUrl);

            newsList.add(news);



        }


        return newsList;


    }
}
