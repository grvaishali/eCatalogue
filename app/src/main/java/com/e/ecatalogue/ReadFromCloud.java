package com.e.ecatalogue;

import android.util.Log;

import androidx.annotation.NonNull;

import com.e.com.e.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ReadFromCloud {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
   // static CollectionReference ref = db.collection("items");
    DocumentReference ref = db.collection("items").document();
    ListDataModel list = new ListDataModel();
    private static ArrayList<ListDataModel> data = new ArrayList<ListDataModel>();

    public ArrayList<ListDataModel> load() {

//        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot snapshot) {
//
//                ListDataModel list = snapshot.toObject(ListDataModel.class);
//                DocumentSnapshot snapshot1 = snapshot;
//                list.setId_(Integer.parseInt(snapshot.getString("id")));
//                list.setDescription(snapshot.getString("description"));
//                list.setPrice(Integer.parseInt(snapshot.getString("price")));
//                list.setImage(Integer.parseInt(snapshot.getString("imageurl")));
//                list.setCode((snapshot.getString("code")));
//
//
//                data.add(new ListDataModel(list.getDescription(),
//                        list.getCode(), list.getPrice(), list.getImage(), list.getId_()));
//
//
//            }
//        });
        return data;
    }

    public ArrayList<ListDataModel> read() {


        db.collection("items").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {


            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    int i = 0;
                    String projects[] = new String[task.getResult().size()];
                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                        ListDataModel list = new ListDataModel();
                        list.setId_(snapshot.getString("id"));
                        list.setDescription(snapshot.getString("description"));
                        list.setPrice(snapshot.getString("price")   );
                        list.setImage(snapshot.getString("imageurl"));
                        list.setCode((snapshot.getString("code")));


                        data.add(new ListDataModel(list.getDescription(),
                                list.getCode(), list.getPrice(), list.getImage(), list.getId_()));
                    }
                }

            }
        });
        return data;
    }
}
