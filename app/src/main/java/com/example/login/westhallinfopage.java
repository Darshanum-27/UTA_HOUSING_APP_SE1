package com.example.login;

import static com.example.login.arborselection.MSG1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Map;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class westhallinfopage extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String a,b;
    public static final String MSG = "com.CodeWarrior.multiSreeen.ORDER";
    private TextView retrieveTV;

    private void getdata(Query app,TextView retrieveTV) {

        // calling add value event listener method
        // for getting the values from database.
        app.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = "";
                System.out.println("count:"+Long.toString(snapshot.getChildrenCount()));
                for(DataSnapshot lastDataSnapshot : snapshot.getChildren()) {
                    data = data+lastDataSnapshot.getKey().toString()+"   ";
//                    data = "Apartment Name:"+lastDataSnapshot.child("ApartmentName").getValue(String.class);
//                    data = data +"\nApartment Number:"+lastDataSnapshot.child("ApartmentNumber").getValue(String.class);
//                    data = data +"\nApartment Number:"+lastDataSnapshot.child("Description").getValue(String.class);
//                    data = data +"\nApartment Number:"+lastDataSnapshot.child("Furnished").getValue(String.class);
//                    data = data +"\nApartment Number:"+lastDataSnapshot.child("Type").getValue(String.class)+"\n\n";
                }
                System.out.println(data);
                retrieveTV.setText(data);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(westhallinfopage.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent in1 = getIntent();
        Bundle extras = in1.getExtras();
        a = extras.getString("room");
        b = extras.getString("furnished");
        setContentView(R.layout.activity_westhallinfopage);
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("WestHall").child(b).child(a);
        Query app = reference.limitToLast(100);
        Log.d("app details", String.valueOf(app).toString());
        retrieveTV = findViewById(R.id.textView62);
        getdata(app,retrieveTV);
    }
    public void finalwesthall(View view){
        Intent i1 = new Intent(this,westhallfinalized.class);
        EditText et = (EditText) findViewById(R.id.inputfield);
        String str1 = et.getText().toString();
        Bundle extras = new Bundle();
        extras.putString("room1",a);
        extras.putString("furnished1",b);
        extras.putString("room Number1",str1);
        i1.putExtras(extras);
        //i1.putExtra(MSG,"You Have selected the Room Number:"+str);
        startActivity(i1);
    }
}