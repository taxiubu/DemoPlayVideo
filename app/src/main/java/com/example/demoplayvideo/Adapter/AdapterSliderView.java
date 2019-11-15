package com.example.demoplayvideo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.demoplayvideo.Interface.IOnClickGetURL;
import com.example.demoplayvideo.Model.Anime;
import com.example.demoplayvideo.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class AdapterSliderView extends SliderViewAdapter<AdapterSliderView.SliderViewHolder> {
    List<Anime> animeList;
    Context context;
    IOnClickGetURL onClickItemAnime;

    public AdapterSliderView(List<Anime> animeList, Context context) {
        this.animeList = animeList;
        this.context = context;
    }

    public void setOnClickItemAnime(IOnClickGetURL onClickItemAnime) {
        this.onClickItemAnime = onClickItemAnime;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_slider_view, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        final Anime anime= animeList.get(position);
        Glide.with(context)
                .load(anime.getImage())
                .error(R.drawable.ic_launcher_foreground)
                .into(viewHolder.ivImageCover);
        viewHolder.ivImageCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemAnime.onClick(anime.getUrl());
            }
        });
    }

    @Override
    public int getCount() {
        return animeList.size();
    }

    public class SliderViewHolder extends SliderViewAdapter.ViewHolder {
        ImageView ivImageCover;
        public SliderViewHolder(View itemView) {
            super(itemView);
            ivImageCover= itemView.findViewById(R.id.ivImageCover);
        }
    }
}
