package com.example.login;

import android.net.Uri;

public class Apartments {
    //public Uri uri;
    public String ApartmentName,ApartmentNumber,Furnished,Type,Description,uri;
    public Apartments() {
        this.ApartmentName = null;
        this.ApartmentNumber = null;
        this.Furnished = null;
        this.Type = null;
        this.Description = null;
        this.uri = null;
    }
    public Apartments(String ApartmentName, String Number, String Furnished, String Type, String Description,String uri) {
        this.ApartmentName = ApartmentName;
        this.ApartmentNumber = Number;
        this.Furnished = Furnished;
        this.Type = Type;
        this.Description = Description;
        this.uri = uri;
    }
}
