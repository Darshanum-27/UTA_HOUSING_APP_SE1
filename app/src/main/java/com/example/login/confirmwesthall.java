package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class confirmwesthall extends AppCompatActivity {
    public String a = "",data,uri;
    public static final String MSG = "com.CodeWarrior.multiSreeen.ORDER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmwesthall);
        Intent in1 = getIntent();
        Bundle extras = in1.getExtras();
        data = extras.getString("data");
        uri = extras.getString("uri");
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView e1 = findViewById(R.id.finaloutput);
        ImageView img = findViewById(R.id.imageView20);
        Picasso.get().load(uri).into(img);
        e1.setText("Congratulations!!!!!\n\n\nYou Have Successfully applied for west hall \n\n The Details are as follows\n\n"+data);
    }
    public void returnhomepage(View view){
        Intent i1 = new Intent(this,studentpagedetials.class);
        i1.putExtra(MSG,a);
        startActivity(i1);
    }
    public void signinpage(View view){
        Intent i1 = new Intent(this,MainActivity.class);
        i1.putExtra(MSG,a);
        startActivity(i1);
    }
}