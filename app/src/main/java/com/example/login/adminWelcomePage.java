package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class adminWelcomePage extends AppCompatActivity {
    public static final String MSG = "com.CodeWarrior.multiSreeen.ORDER";
    public String a = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome_page);
        VideoView video = findViewById(R.id.video);
        video.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.video);
        video.start();
        MediaController mc = new MediaController(this);
        video.setMediaController(mc);
        mc.setAnchorView(video);
    }
    public void addApartment(View view){
        Intent i1 = new Intent(this,AddApartment.class);
        i1.putExtra("","hello world");
        startActivity(i1);
    }
    public void removeApartment(View view){
        Intent i1 = new Intent(this,RemoveApartment.class);
        i1.putExtra("","hello world");
        startActivity(i1);
    }

    public void modifyApartment(View view){
        Intent i1 = new Intent(this,ModifyApartment.class);
        i1.putExtra("","hello world");
        startActivity(i1);
    }

    public void gotoMainpag(View view){
        Intent i1 = new Intent(this,MainActivity.class);
        i1.putExtra("","hello world");
        startActivity(i1);
    }

}