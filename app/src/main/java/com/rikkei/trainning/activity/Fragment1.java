package com.rikkei.trainning.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Fragment1 extends Fragment {
    private String fragment_name = "Fragment 1";
    private Button btnStartFragment2;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(fragment_name,"Start onCreate "+fragment_name);
        mediaPlayer = MediaPlayer.create(getContext(),R.raw.music1);
        mediaPlayer.start();
        if(savedInstanceState!=null){
            mediaPlayer.seekTo(savedInstanceState.getInt("position",0));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(fragment_name,"Start onCreateView "+fragment_name);
        return inflater.inflate(R.layout.fragment1,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(fragment_name,"Start onViewCreated "+fragment_name);
        btnStartFragment2 = view.findViewById(R.id.btnStartFragment2);
        btnStartFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getParentFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.remove(manager.findFragmentByTag("fragment1"));
                transaction.add(R.id.frame,new Fragment2(),"fragment2");
                transaction.addToBackStack("fragment2");
                transaction.commit();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(fragment_name,"Start onStart "+fragment_name);
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(fragment_name,"Start onResume "+fragment_name);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(fragment_name,"Start onPause "+fragment_name);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(fragment_name,"Start onStop "+fragment_name);
        mediaPlayer.pause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", mediaPlayer.getCurrentPosition());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(fragment_name,"Start onDestroyView "+fragment_name);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(fragment_name,"Start onDestroy "+fragment_name);
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
