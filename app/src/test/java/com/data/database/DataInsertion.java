package com.data.database;


import android.content.Context;


import com.google.firebase.database.FirebaseDatabase;

public class DataInsertion {
    static Context context;

    private com.google.firebase.database.DatabaseReference mDatabase;

    public DataInsertion(com.google.firebase.database.DatabaseReference mDatabase) {
        this.mDatabase = mDatabase;
    }

    public void getReference() {

// ...
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public DataInsertion() {
        //  this.mDatabase = mDatabase;
    }



}
