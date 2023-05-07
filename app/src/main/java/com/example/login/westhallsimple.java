package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class westhallsimple extends AppCompatActivity {
    public static final String MSG = "com.CodeWarrior.multiSreeen.ORDER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_westhallsimple);
        VideoView video = findViewById(R.id.video1);
        video.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.video1);
        video.start();
        MediaController mc = new MediaController(this);
        video.setMediaController(mc);
        mc.setAnchorView(video);
    }

    public void westhallselect(View view){
        Intent i1 = new Intent(this,arborselection.class);
        i1.putExtra(MSG,"Westhall");
        startActivity(i1);
    }
}