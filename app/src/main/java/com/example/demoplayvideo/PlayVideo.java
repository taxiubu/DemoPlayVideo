package com.example.demoplayvideo;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demoplayvideo.Model.GetHTMLData;
import com.example.demoplayvideo.databinding.ActivityMainBinding;
import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;

import java.util.Arrays;
import java.util.List;

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
        videoView=findViewById(R.id.videoView);
        // chưa lấy đc linkVideo. Dùng tạm :)
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.gokuvsjiren));
        videoView.start();
        MediaController controller= new MediaController(this);
        videoView.setMediaController(controller);
        test();
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
                Uri video= Uri.parse(linkVideo);
                videoView.setVideoURI(video);
                videoView.start();
            //}
        }
    }
    public void test(){
        final List<String> datas = Arrays.asList("1", "2", "3", "4", "5");
        SimpleMarqueeView<String> marqueeView = (SimpleMarqueeView) findViewById(R.id.marqueeView);
        SimpleMF<String> marqueeFactory = new SimpleMF(this);
        marqueeFactory.setData(datas);
        marqueeView.setMarqueeFactory(marqueeFactory);
        marqueeView.startFlipping();
    }
}
