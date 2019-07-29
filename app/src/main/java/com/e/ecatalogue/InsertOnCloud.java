package com.e.ecatalogue;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.e.com.e.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class InsertOnCloud {
    static Context context;
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static DocumentReference ref = db.collection("users").document();

    public static void create(String uid, String name, String password, String phone) {
        User user = new User();
        user.setU_id(uid);
        user.setName(name);
        user.setPassword(password);
        user.setPhoneNumber(phone);
        user.setSecurity_level("1");

        ref.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i("Status", "Successful");
//                    Intent intent = new Intent();
//                    intent.putExtra("catalogue","Successful");
//                    setResult();
                } else {
                    Log.i("Status", "Failed");
                }

            }
        });

        ref.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}
