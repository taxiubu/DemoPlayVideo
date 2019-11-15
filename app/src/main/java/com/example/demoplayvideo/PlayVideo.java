package com.example.demoplayvideo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.demoplayvideo.Model.GetHTMLData;
import com.example.demoplayvideo.databinding.ActivityMainBinding;

public class PlayVideo extends AppCompatActivity {
    ActivityMainBinding binding;
    String url_html;
    String linkVideo;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        //binding= DataBindingUtil.setContentView(this, R.layout.activity_play_video);
        url_html= getIntent().getStringExtra("url");
        videoView= findViewById(R.id.videoView);

        // chưa lấy đc linkVideo. Dùng tạm :)
        Uri video= Uri.parse("https://r4---sn-i3b7knlk.googlevideo.com/videoplayback?expire=1573854342&ei=ZgDPXberF4aHowO4pJHADw&ip=167.172.64.244&id=7cf23bc6f23dd387&itag=18&source=picasa&begin=0&requiressl=yes&sc=yes&ttl=transient&susc=ph&app=fife&mime=video/mp4&dur=1450.109&lmt=1573755296584141&sparams=expire,ei,ip,id,itag,source,requiressl,ttl,susc,app,mime,dur,lmt&sig=ALgxI2wwRgIhAIB1AuY1YIeJIWfBz_eh6cjch0wjrVS14Vc4Ifshwx9-AiEAyo2ZlCJc4nvY95md0pBO1lZWqJRkJOOLOKH_hc2NlVs=&cms_redirect=yes&mip=1.55.94.184&mm=30&mn=sn-i3b7knlk&ms=nxu&mt=1573849658&mv=m&mvi=3&pl=24&lsparams=mip,mm,mn,ms,mv,mvi,pl,sc&lsig=AHylml4wRQIgFUAhqL0tgEA4AWtX_VhSYiWWEbt32aXwsdxZrGZorDcCIQDfpBSSYC5cZNyYZge1SM_fs3p6k5BM802en2VXapZusA==");
        videoView.setVideoURI(video);
        videoView.start();

       // new GetLink(url_html).execute();
    }
    class GetLink extends GetHTMLData{

        protected GetLink(String url_html) {
            super(url_html);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            linkVideo= getLinkVideo();
            //if(linkVideo!=null){
                Uri video= Uri.parse("https://r4---sn-i3b7knlk.googlevideo.com/videoplayback?expire=1573854342&ei=ZgDPXberF4aHowO4pJHADw&ip=167.172.64.244&id=7cf23bc6f23dd387&itag=18&source=picasa&begin=0&requiressl=yes&sc=yes&ttl=transient&susc=ph&app=fife&mime=video/mp4&dur=1450.109&lmt=1573755296584141&sparams=expire,ei,ip,id,itag,source,requiressl,ttl,susc,app,mime,dur,lmt&sig=ALgxI2wwRgIhAIB1AuY1YIeJIWfBz_eh6cjch0wjrVS14Vc4Ifshwx9-AiEAyo2ZlCJc4nvY95md0pBO1lZWqJRkJOOLOKH_hc2NlVs=&cms_redirect=yes&mip=1.55.94.184&mm=30&mn=sn-i3b7knlk&ms=nxu&mt=1573849658&mv=m&mvi=3&pl=24&lsparams=mip,mm,mn,ms,mv,mvi,pl,sc&lsig=AHylml4wRQIgFUAhqL0tgEA4AWtX_VhSYiWWEbt32aXwsdxZrGZorDcCIQDfpBSSYC5cZNyYZge1SM_fs3p6k5BM802en2VXapZusA==.mp4");
                videoView.setVideoURI(video);
                videoView.start();
            //}
        }
    }
}
