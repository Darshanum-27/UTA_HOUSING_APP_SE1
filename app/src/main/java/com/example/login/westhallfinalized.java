package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.system.StructMsghdr;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class westhallfinalized extends AppCompatActivity {
    public static final String MSG = "com.CodeWarrior.multiSreeen.ORDER";
    public String a = "",data = "";
    public String[] link = new String[1];
    String a1,b;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_westhallfinalized);
        Intent in1 = getIntent();
        Bundle extras = in1.getExtras();
        a1 = extras.getString("room1");
        b = extras.getString("furnished1");
        a = extras.getString("room Number1");
        TextView e1 = findViewById(R.id.textview1);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("WestHall").child(b).child(a1).child(a);
        Query app = reference.limitToLast(100);
        ImageView rlink = findViewById(R.id.imageView40);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView img = findViewById(R.id.imageView40);
        app.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot lastDataSnapshot : snapshot.getChildren()) {
                    data = data +"Apartment Name :"+snapshot.child("ApartmentName").getValue(String.class)+"\n\n";
                    data = data +"Apartment Number : "+snapshot.child("ApartmentNumber").getValue(String.class)+"\n\n";
                    data = data +"Apartment Description : "+snapshot.child("Description").getValue(String.class)+"\n\n";
                    data = data +"Furnished/Unfurnished : "+snapshot.child("Furnished").getValue(String.class)+"\n\n";
                    data = data +"Apartment Type :"+snapshot.child("Type").getValue(String.class)+"\n\n";
                    link[0] = snapshot.child("uri").getValue(String.class);
                    break;
                }

                // loading that data into rImage
                // variable which is ImageView
                Picasso.get().load(link[0]).into(rlink);
                System.out.println("data2:"+data);
                e1.setText(" You Have Selected Room:"+a+"\n\n"+data);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(westhallfinalized.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void confirmwesthallfinal(View view){
        Intent i1 = new Intent(this,confirmwesthall.class);
        Bundle extras = new Bundle();
        extras.putString("data",data);
        extras.putString("uri",link[0]);
        i1.putExtras(extras);
        startActivity(i1);
    }
}