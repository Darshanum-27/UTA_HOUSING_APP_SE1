package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class resetpassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
    }

    public void gobacktomaingpage(View view){
        System.out.println("Updated");
        EditText text1 = (EditText) findViewById(R.id.textinput1);
        String s1 = text1.getText().toString();
        EditText text2 = (EditText)findViewById(R.id.textinput2);
        String s2 = text2.getText().toString();
        EditText text3 = (EditText)findViewById(R.id.textinput3);
        String s3 = text3.getText().toString();
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Students").child(s1).child("username").setValue(s1);
        mDatabase.child("Students").child(s1).child("edit_cnfrm_password").setValue(s3);
        mDatabase.child("Students").child(s1).child("password").setValue(s3);
        Toast.makeText(resetpassword.this,"Successfully Updated", Toast.LENGTH_SHORT).show();
        Intent i1 = new Intent(this,MainActivity.class);
        i1.putExtra("","hello world");
        startActivity(i1);
    }
}