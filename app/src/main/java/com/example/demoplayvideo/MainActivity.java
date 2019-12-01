package com.example.demoplayvideo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.demoplayvideo.Fragment.FragmentDetailAnime;
import com.example.demoplayvideo.Fragment.FragmentHome;
import com.example.demoplayvideo.Interface.IOnClickGetURL;
import com.example.demoplayvideo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements IOnClickGetURL{
    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        getFragment(FragmentHome.newInstance());

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
}
