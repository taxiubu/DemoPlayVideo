package com.example.demoplayvideo.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demoplayvideo.Adapter.AdapterEpsList;
import com.example.demoplayvideo.Model.DetailAnime;
import com.example.demoplayvideo.Model.Episode;
import com.example.demoplayvideo.Model.GetHTMLData;
import com.example.demoplayvideo.R;
import com.example.demoplayvideo.databinding.FragmentDetailAnimeBinding;

import java.util.List;


public class FragmentDetailAnime extends Fragment {
    List<Episode> episodeList;
    DetailAnime detailAnime;
    FragmentDetailAnimeBinding binding;
    public static FragmentDetailAnime newInstance(String url) {

        Bundle args = new Bundle();
        args.putSerializable("url", url);
        FragmentDetailAnime fragment = new FragmentDetailAnime();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_detail_anime, container, false);
        String url_html= getArguments().getString("url");
        new getDetail(url_html).execute();
        new getEpisode(url_html).execute();
        return binding.getRoot();
    }
    private class getDetail extends GetHTMLData{

        protected getDetail(String url_html) {
            super(url_html);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            detailAnime= getDetailAnime();
            if(detailAnime!=null)
                inVisibleShimmer();

            Glide.with(getContext()).load(detailAnime.getMainImage())
                    .error(R.drawable.image_cover)
                    .into(binding.imageMainAnime);
            Glide.with(getContext()).load(detailAnime.getMainImage())
                    .error(R.drawable.image_cover)
                    .into(binding.imageCover);
            binding.tvTitleVNAnime.setText(detailAnime.getVnTitleDetail());
            binding.tvTitleENGAnime.setText(detailAnime.getEngTitleDetail());
            binding.tvDatePublish.setText(detailAnime.getDatePublish()+" |");
            binding.tvView.setText(detailAnime.getView());
            binding.iconPublish.setBackgroundResource(R.drawable.calendar);
            binding.iconEye.setBackgroundResource(R.drawable.eye);
            if(detailAnime.getMovieSchedule().equals("")==false)
                binding.tvMovieSchedule.setText(detailAnime.getMovieSchedule());
            if(detailAnime.getDescription().equals("")==false)
                binding.tvDescription.setText(detailAnime.getDescription());

        }
    }
    private class getEpisode extends GetHTMLData{

        protected getEpisode(String url_html) {
            super(url_html);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            episodeList= getEpsList();
            //rcvEps
            RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
            binding.rcvEpisode.setLayoutManager(layoutManager);
            if(episodeList!=null){
                AdapterEpsList adapterEpsList= new AdapterEpsList(getContext(), episodeList);
                binding.rcvEpisode.setAdapter(adapterEpsList);
            }
        }
    }
    private void inVisibleShimmer(){
        binding.shimmerImageCover.stopShimmer();
        binding.shimmerImageCover.setVisibility(View.INVISIBLE);
        binding.shimmerImageMain.stopShimmer();
        binding.shimmerImageMain.setVisibility(View.INVISIBLE);
        binding.shimmerTitleVNAnime.stopShimmer();
        binding.shimmerTitleVNAnime.setVisibility(View.INVISIBLE);
        binding.shimmerTitleEngAnime.stopShimmer();
        binding.shimmerTitleEngAnime.setVisibility(View.INVISIBLE);
        binding.shimmerDatePublish.stopShimmer();
        binding.shimmerDatePublish.setVisibility(View.INVISIBLE);
    }
}
