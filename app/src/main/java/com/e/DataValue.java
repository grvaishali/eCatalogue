package com.e;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.e.com.e.user.User;
import com.e.ecatalogue.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class DataValue {
    static Context context;

    public static void createUser(String uid, String name, String password, String phone) {
        User user = new User();
        user.setU_id(uid);
        user.setName(name);
        user.setPassword(password);
        user.setPhoneNumber(phone);
        user.setSecurity_level("1");
        FirebaseDatabase.getInstance().getReference().child(context.getString(R.string.dbnode_users)).
                child(uid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                FirebaseAuth.getInstance().signOut();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();

            }
        });
    }

    public static void update(View view) {
        com.google.firebase.database.DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        //Change Name

        reference.child(context.getString(R.string.dbnode_users))
                .child("1").child(context.getString(R.string.field_name))
                .setValue("Jiyana");


    }


}
