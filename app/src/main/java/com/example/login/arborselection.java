package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class arborselection extends AppCompatActivity{
    Spinner sp,sp3;
    String value,value1;
    String[] arr1 = {"NOT SELECTED","ONE BEDROOM, ONE BATHROOM","TWO BEDROOMS, TWO BATHROOMS"};
    String[] arr2 = {"NOT SELECTED","FURNISHED APARTMENT","UN-FUNISHED APARTMENT"};
    public static final String MSG = "com.CodeWarrior.multiSreeen.ORDER";
    public static final String MSG1 = "com.CodeWarrior.multiSreeen.ORDER";
    @SuppressLint({"ResourceType", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arborselection);
        Intent in1 = getIntent();
        String message = null;
        String src = null;
        ImageView imageView = findViewById(R.id.image1);
        if (in1.getStringExtra(westhallsimple.MSG)!= ""){
            message = in1.getStringExtra(westhallsimple.MSG);
            imageView.setImageResource(R.raw.w1);
        }
        if (in1.getStringExtra(arbor.MSG)!= "") {
            message = in1.getStringExtra(arbor.MSG);
            imageView.setImageResource(R.raw.a2);
        }
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView tv = findViewById(R.id.abc);
        tv.setText("YOU SELECTED :"+message);

        sp = findViewById(R.id.dropdown);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(arborselection.this, android.R.layout.simple_spinner_item,arr1);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adp);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(arborselection.this,arr1[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        sp3 = findViewById(R.id.dropdown3);
        ArrayAdapter<String> adp3 = new ArrayAdapter<String>(arborselection.this, android.R.layout.simple_spinner_item,arr2);
        adp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adp3);

        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value1 = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(arborselection.this,arr2[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }
    public void nextpage(View view){
        Intent i1 = new Intent(this,westhallinfopage.class);
        Bundle extras = new Bundle();
        extras.putString("room",value);
        extras.putString("furnished",value1);
        i1.putExtras(extras);
        startActivity(i1);
    }
}