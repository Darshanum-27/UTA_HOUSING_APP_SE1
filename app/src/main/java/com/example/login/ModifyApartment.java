package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ModifyApartment extends AppCompatActivity {
    String[] arr1 = {"NOT SELECTED", "ONE BEDROOM, ONE BATHROOM", "TWO BEDROOMS, TWO BATHROOMS"};
    String[] arr2 = {"NOT SELECTED", "FURNISHED APARTMENT", "UN-FUNISHED APARTMENT"};
    String[] arr3 = {"NOT SELECTED", "WestHall", "Arbor Oaks", "Meadow Run", "TimberBrook"};
    String value1, value2, value3, str2, str1;
    Spinner spinner, spinner1, spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_apartment);

        spinner1 = findViewById(R.id.dropdown6);
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(ModifyApartment.this, android.R.layout.simple_spinner_item, arr3);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adp1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value1 = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(ModifyApartment.this, arr3[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        spinner = findViewById(R.id.dropdown7);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(ModifyApartment.this, android.R.layout.simple_spinner_item, arr2);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value2 = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(ModifyApartment.this, arr2[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinner2 = findViewById(R.id.dropdown8);
        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(ModifyApartment.this, android.R.layout.simple_spinner_item, arr1);
        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adp2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value3 = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(ModifyApartment.this, arr1[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void modify(View view) {
        EditText et = (EditText) findViewById(R.id.input4);
        str1 = et.getText().toString();
        EditText et2 = (EditText) findViewById(R.id.input6);
        str2 = et2.getText().toString();
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("WestHall").child(value2).child(value3).child(str1);
        Query app = reference.limitToLast(100);
        String[] link = new String[1];
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView img = findViewById(R.id.imageView40);
        app.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot lastDataSnapshot : snapshot.getChildren()) {
                    link[0] = snapshot.child("uri").getValue(String.class);
                    break;
                }
                //mDatabase.child(value2).child(value1).child(value3).child(str1).removeValue();
                Apartments ap = new Apartments(value1, str1, value2, value3, str2, link[0]);
                //mDatabase.child(value1).child(str1).setValue(ap);
                mDatabase.child(value1).child(value2).child(value3).child(str1).setValue(ap);
                Toast.makeText(ModifyApartment.this, "Record Updated to Database", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Intent i1 = new Intent(this,adminWelcomePage.class);
        i1.putExtra("", "hello world");
        startActivity(i1);
    }
}