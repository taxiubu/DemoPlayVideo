package com.example.demoplayvideo.Model;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;

public class GetHTMLData extends AsyncTask<Void, Void, Void> {
    private String result="";
    private String url_html;
    protected GetHTMLData(String url_html){
        this.url_html= url_html;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        Document document=null;
        try {
            document= Jsoup.connect(url_html).get();
            result= document.html();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected List<Anime> getAnimeList(){
        List<Anime> animeList= new ArrayList<>();
        //result= result.replaceAll(System.getProperty("line.separator"),"");
        Document document= Jsoup.parse(result);
        if(document!=null){
            Elements moveList= document.selectFirst("div.flex-wrap-movielist").select("a");
            for (Element element:moveList){
                String url= element.attr("href");
                String title= element.select("p.title").text();
                String image= element.select("div.pl-carousel-img").attr("data-original");
                animeList.add(new Anime(title, image+".jpg", url));
            }
        }
        return animeList;
    }
    protected DetailAnime getDetailAnime(){
        Document document= Jsoup.parse(result);
        DetailAnime detailAnime=null;
        if(document!=null){
            Element content = document.selectFirst("section.content");
            Element col_sm_4= content.selectFirst("div.col-sm-4");
            Element linkImage= col_sm_4.select("img.movie-thumb").first();
            Element col_sm_8= content.selectFirst("div.col-sm-8");
            String vnTitle= col_sm_8.select("h1").text();
            String engTitle= col_sm_8.selectFirst("p.movie-info").text();
            String mainImage= linkImage.attr("data-original");
            detailAnime= new DetailAnime(vnTitle, engTitle, mainImage+".jpg", "", "", "");
        }

        return detailAnime;
    }
}
