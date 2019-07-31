package com.data.database;


import android.content.Context;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.e.com.e.user.User;

import com.e.ecatalogue.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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
