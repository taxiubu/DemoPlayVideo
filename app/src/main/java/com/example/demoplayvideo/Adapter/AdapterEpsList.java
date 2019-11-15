package com.example.demoplayvideo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoplayvideo.Interface.IOnClickGetURL;
import com.example.demoplayvideo.Model.Episode;
import com.example.demoplayvideo.R;

import java.util.List;

public class AdapterEpsList extends RecyclerView.Adapter<AdapterEpsList.ViewHolder> {
    Context context;
    List<Episode> episodeList;
    IOnClickGetURL onClickGetURL;
    public AdapterEpsList(Context context, List<Episode> episodeList) {
        this.context = context;
        this.episodeList = episodeList;
    }

    public void setOnClickGetURL(IOnClickGetURL onClickGetURL) {
        this.onClickGetURL = onClickGetURL;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_episode, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Episode episode= episodeList.get(position);
        holder.tvTitleEps.setText(episode.getTitle());
        holder.tvTitleEps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGetURL.onClick(episode.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitleEps;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitleEps= itemView.findViewById(R.id.tvTitleEps);
        }
    }
}
