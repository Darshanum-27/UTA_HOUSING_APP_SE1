package com.example.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.*;

public class AddApartment extends AppCompatActivity {
    public StorageReference storageref;
    String url1;
    public static final int IMAGE_REQUEST = 2;
    public Uri file;
    Apartments ap;
    String[] arr1 = {"NOT SELECTED","ONE BEDROOM, ONE BATHROOM","TWO BEDROOMS, TWO BATHROOMS"};
    String[] arr2 = {"NOT SELECTED","FURNISHED APARTMENT","UN-FUNISHED APARTMENT"};
    String[] arr3 = {"NOT SELECTED","WestHall","Arbor Oaks","Meadow Run","TimberBrook"};
    String value1,value2,value3,str2,str1;
    Spinner spinner,spinner1,spinner2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_apartment);

        spinner1 = findViewById(R.id.dropdown2);
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(AddApartment.this, android.R.layout.simple_spinner_item,arr2);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adp1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value1 = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(AddApartment.this,arr2[i], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        spinner = findViewById(R.id.dropdown4);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(AddApartment.this, android.R.layout.simple_spinner_item,arr3);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value2 = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(AddApartment.this,arr3[i], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinner2 = findViewById(R.id.dropdown5);
        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(AddApartment.this, android.R.layout.simple_spinner_item,arr1);
        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adp2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value3 = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(AddApartment.this,arr1[i], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    public void send(View view){
        EditText et = (EditText) findViewById(R.id.input1);
        str1 = et.getText().toString();
        EditText et2 = (EditText) findViewById(R.id.input6);
        str2 = et2.getText().toString();
        Toast.makeText(AddApartment.this,"Record Uploaded to Database", Toast.LENGTH_SHORT).show();
        DatabaseReference mDatabase;
// ...
        mDatabase = FirebaseDatabase.getInstance().getReference();
        System.out.println("URL "+url1);
        Apartments ap = new Apartments(value2,str1,value1,value3,str2,url1);
        //mDatabase.child(value2).child(str1).setValue(ap);
        mDatabase.child(value2).child(value1).child(value3).child(str1).setValue(ap);
        Intent i1 = new Intent(this,adminWelcomePage.class);
        i1.putExtra("","hello world");
        startActivity(i1);
    }
    public String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    @Override
    public void onActivityResult(int requestCode, int resultcode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultcode, data);
        if (requestCode == IMAGE_REQUEST && resultcode == RESULT_OK){
            file = data.getData();
            upload();
        }
    }
    public void openImage(){
            Intent intent = new Intent();
            intent.setType("image/");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,IMAGE_REQUEST);
    }

    public void uploadImg(View view){
        openImage();
    }

    public void upload(){
            StorageReference firebaseref = FirebaseStorage.getInstance().getReference().child("WestHall").child(String.valueOf(System.currentTimeMillis())+"."+getFileExtension(file));
            firebaseref.putFile(file).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    firebaseref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            url1 = uri.toString();
                            Log.d("Download URL",url1);
                            Toast.makeText(AddApartment.this, "Image uploaded Successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
    }
}