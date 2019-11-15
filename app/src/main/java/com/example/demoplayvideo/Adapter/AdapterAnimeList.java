package com.example.demoplayvideo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demoplayvideo.Interface.IOnClickGetURL;
import com.example.demoplayvideo.Model.Anime;
import com.example.demoplayvideo.R;

import java.util.List;

public class AdapterAnimeList extends RecyclerView.Adapter<AdapterAnimeList.ViewHolder> {
    List<Anime> animeList;
    Context context;
    IOnClickGetURL onClickItemAnime;

    public void setOnClickItemAnime(IOnClickGetURL onClickItemAnime) {
        this.onClickItemAnime = onClickItemAnime;
    }

    public AdapterAnimeList(List<Anime> animeList, Context context) {
        this.animeList = animeList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_anime, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Anime anime= animeList.get(position);
        final Animation a= AnimationUtils.loadAnimation(context, R.anim.anim_textview);
        a.reset();
        holder.tvTitleItem.setText(anime.getTitle());
        //holder.tvTitleItem.startAnimation(a);
        Glide.with(context)
                .load(anime.getImage())
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imItem);
        holder.imItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemAnime.onClick(anime.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imItem;
        TextView tvTitleItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imItem= itemView.findViewById(R.id.imItem);
            tvTitleItem= itemView.findViewById(R.id.tvTitleItem);
        }
    }
}
