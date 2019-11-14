package com.example.demoplayvideo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.demoplayvideo.Fragment.FragmentDetailAnime;
import com.example.demoplayvideo.Fragment.FragmentHome;
import com.example.demoplayvideo.Interface.ICheckNull;
import com.example.demoplayvideo.Interface.IOnClickItemAnime;
import com.example.demoplayvideo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements IOnClickItemAnime, ICheckNull {
    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        getFragment(FragmentHome.newInstance());
        visibleShimmer();
    }

    public void getFragment(Fragment fragment) {
        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_fragment_container, fragment).addToBackStack(null)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "getFragment: " + e.getMessage());
        }
    }

    @Override
    public void onClick(String url) {
        getFragment(FragmentDetailAnime.newInstance(url));
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
    public void checkNull(int a) {
        if(a==1)
            inVisibleShimmer();
    }
}
