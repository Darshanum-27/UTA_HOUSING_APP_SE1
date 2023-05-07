package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class arbor extends AppCompatActivity {
    public static final String MSG = "com.CodeWarrior.multiSreeen.ORDER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arbor);
    }
    public void arborselect(View view){
        Intent i1 = new Intent(this,arborselection.class);
        i1.putExtra(MSG,"Arbor Oaks");
        startActivity(i1);
    }
}