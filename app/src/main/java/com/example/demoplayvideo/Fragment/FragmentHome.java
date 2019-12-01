package com.example.demoplayvideo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoplayvideo.Adapter.AdapterAnimeList;
import com.example.demoplayvideo.Adapter.AdapterSliderView;
import com.example.demoplayvideo.Interface.IOnClickGetURL;
import com.example.demoplayvideo.Model.Anime;
import com.example.demoplayvideo.Model.GetHTMLData;
import com.example.demoplayvideo.R;
import com.example.demoplayvideo.StringURL;
import com.example.demoplayvideo.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    List<Anime> animes;
    IOnClickGetURL onClickItemAnime;
    FragmentHomeBinding binding;
    public static FragmentHome newInstance() {

        Bundle args = new Bundle();

        FragmentHome fragment = new FragmentHome();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);
        new GetHotVideo(StringURL.HOME_URL).execute();
        //RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false);
        GridLayoutManager layoutManager= new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.rcvHotVideo.setLayoutManager(layoutManager);
        visibleShimmer();
        return binding.getRoot();
    }
    private void loadSliderView(){
        //sliderView
        List<Anime> listSliderView= new ArrayList<>();
        listSliderView.add(new Anime("", StringURL.IMAGE_HOT_01, StringURL.ANIME_HOT_01));
        listSliderView.add(new Anime("", StringURL.IMAGE_HOT_02, StringURL.ANIME_HOT_02));
        listSliderView.add(new Anime("", StringURL.IMAGE_HOT_03, StringURL.ANIME_HOT_03));
        listSliderView.add(new Anime("", StringURL.IMAGE_HOT_04, StringURL.ANIME_HOT_04));
        listSliderView.add(new Anime("", StringURL.IMAGE_HOT_05, StringURL.ANIME_HOT_05));
        AdapterSliderView adapterSliderView= new AdapterSliderView(listSliderView,getContext());
        binding.imageSlider.setSliderAdapter(adapterSliderView);
        adapterSliderView.setOnClickItemAnime(new IOnClickGetURL() {
            @Override
            public void onClick(String url) {
                onClickItemAnime.onClick(url);
            }
        });
    }
    private class GetHotVideo extends GetHTMLData {
        protected GetHotVideo(String url_html) {
            super(url_html);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            animes= getAnimeList();
            AdapterAnimeList adapter= new AdapterAnimeList(animes, getContext());
            binding.rcvHotVideo.setAdapter(adapter);
            adapter.setOnClickItemAnime(new IOnClickGetURL() {
                @Override
                public void onClick(String url) {
                    onClickItemAnime.onClick(url);
                }
            });
            adapter.notifyDataSetChanged();
            if(animes!=null){
                inVisibleShimmer();
            }
            loadSliderView();
        }

    }
    private void inVisibleShimmer(){
        binding.shimmerSliderView.stopShimmer();
        binding.shimmerSliderView.setVisibility(View.INVISIBLE);
        binding.shimmerRCV.stopShimmer();
        binding.shimmerRCV.setVisibility(View.INVISIBLE);
    }

    private void visibleShimmer(){
        binding.shimmerSliderView.startShimmer();
        binding.shimmerSliderView.setVisibility(View.VISIBLE);
        binding.shimmerRCV.startShimmer();
        binding.shimmerRCV.setVisibility(View.VISIBLE);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof IOnClickGetURL){
            onClickItemAnime= (IOnClickGetURL) context;
        }
        else {
            throw new RuntimeException(context.toString()+ "must implement");
        }
    }
}
