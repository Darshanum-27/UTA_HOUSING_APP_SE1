package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import android.view.View;

public class studentpagedetials extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentpagedetials);
        VideoView video = findViewById(R.id.video);
        video.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.video);
        video.start();
        MediaController mc = new MediaController(this);
        video.setMediaController(mc);
        mc.setAnchorView(video);
    }
    public void westhallsimple(View view){
        Intent i1 = new Intent(this,westhallsimple.class);
        i1.putExtra("","hello world");
        startActivity(i1);
    }
    public void arbor(View view){
        Intent i1 = new Intent(this,arbor.class);
        i1.putExtra("","hello world");
        startActivity(i1);
    }

}