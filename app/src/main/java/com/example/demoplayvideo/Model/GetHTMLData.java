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
            String vnTitle, engTitle, mainImage, datePublish, view, movieSchedule="", description="";
            Element content = document.selectFirst("section.content");
            Element col_sm_4= content.selectFirst("div.col-sm-4");
            Element linkImage= col_sm_4.select("img.movie-thumb").first();
            Element col_sm_8= content.selectFirst("div.col-sm-8");
            vnTitle= col_sm_8.select("h1").text();
            engTitle= col_sm_8.selectFirst("p.movie-info").text();
            mainImage= linkImage.attr("data-original");
            datePublish= col_sm_8.selectFirst("div.clearfix").selectFirst("span.bio").text();
            view= col_sm_8.selectFirst("div.clearfix").selectFirst("span.bio").lastElementSibling().text();
            Element html_movieschedule=col_sm_8.selectFirst("div.air_date");
            if(html_movieschedule!=null)
                movieSchedule= html_movieschedule.selectFirst("p").text();
            Element col_md_9= content.selectFirst("div.col-md-9");
            Element html_description= col_md_9.selectFirst("div.shortener");
            if(html_description!=null)
                description= html_description.text();
            detailAnime= new DetailAnime(vnTitle, engTitle, mainImage+".jpg", datePublish, view, movieSchedule, description);
        }

        return detailAnime;
    }
    protected List<Episode> getEpsList(){
        Document document= Jsoup.parse(result);
        List<Episode> episodeList= new ArrayList<>();
        if(document!=null){
            Element movie_rate= document.selectFirst("div.movie-rate");
            if(movie_rate!=null){
                Elements movie_eps= movie_rate.select("div.movie-eps").select("a");
                for (Element element:movie_eps){
                    String title= element.attr("title");
                    String href= element.attr("href");
                    episodeList.add(new Episode(title, href));
                }
            }
        }
        return episodeList;
    }
}
