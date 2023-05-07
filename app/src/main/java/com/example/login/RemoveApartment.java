package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RemoveApartment extends AppCompatActivity {
    String[] arr3 = {"NOT SELECTED","WestHall","Arbor Oaks","Meadow Run","TimberBrook"};
    String[] arr2 = {"NOT SELECTED","FURNISHED APARTMENT","UN-FUNISHED APARTMENT"};
    String[] arr1 = {"NOT SELECTED","ONE BEDROOM, ONE BATHROOM","TWO BEDROOMS, TWO BATHROOMS"};
    Spinner spinner,spinner2,spinner3;
    String value1,value2,value3,str1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_apartment);

        spinner = findViewById(R.id.dropdown6);
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(RemoveApartment.this, android.R.layout.simple_spinner_item,arr3);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value1 = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(RemoveApartment.this,arr3[i], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        spinner2 = findViewById(R.id.dropdown10);
        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(RemoveApartment.this, android.R.layout.simple_spinner_item,arr2);
        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adp2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value2 = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(RemoveApartment.this,arr2[i], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinner3 = findViewById(R.id.dropdown11);
        ArrayAdapter<String> adp3 = new ArrayAdapter<String>(RemoveApartment.this, android.R.layout.simple_spinner_item,arr1);
        adp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adp3);

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value3 = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(RemoveApartment.this,arr1[i], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }
    public void remove(View view){
        EditText et = (EditText) findViewById(R.id.input6);
        str1 = et.getText().toString();
        DatabaseReference mDatabase;
// ...
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(value1).child(value2).child(value3).child(str1).removeValue();
        Toast.makeText(RemoveApartment.this,"Record Deleted in Database", Toast.LENGTH_SHORT).show();
        Intent i1 = new Intent(this,adminWelcomePage.class);
        i1.putExtra("","hello world");
        startActivity(i1);
    }
}