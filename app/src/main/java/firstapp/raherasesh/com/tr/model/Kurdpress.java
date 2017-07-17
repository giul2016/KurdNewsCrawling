package firstapp.raherasesh.com.tr.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import firstapp.raherasesh.com.tr.MainActivity;

/**
 * Created by Sh-Java on 12/23/2016.
 */
public class Kurdpress implements Runnable {
    Document doc;
    List<News> newsList;


    public Kurdpress(List<News> newsList) {
        System.out.println("creating thread kurdpress");
        this.newsList=newsList;
    }

    private void connectToSite(){


        try {
            doc = Jsoup.connect("http://kurdpress.com/fa/").timeout(15000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void run() {
        System.out.println("run thread kurdoress");
connectToSite();
        extractNews();

    }


    private List<News> extractNews(){

        // div with class=masthead
    //    Elements divs = doc.select("div.itemsHolder");
        Elements uls = doc.select("ul.dNon");
       // System.out.println("divs="+divs.size());
        System.out.println("uls="+uls.size());
      //  Elements spans = divs.select("span.flL curP mR5 mL5 alC oHid dBlk");
        //System.out.println("span="+ spans.size());

for (Element element:uls){
 //   Element ul = element.select("ul.dNon").first();

  String imageUrl=  element.select("li.img").first().text();
  String title=  element.select("li.titr").first().text();
  String summary=  element.select("li.sotitr").first().text();
    String newsUrl=  element.select("li.url").first().text();

    System.out.println("in kurdpress thread");
    System.out.println('\n'+" in kurdpress title:"+title);
    System.out.println('\n'+" in kurdpress image url:"+imageUrl);
    System.out.println('\n'+"in kurdpress summary:"+summary);
    System.out.println('\n'+" in kurdpress news url:"+newsUrl);

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
