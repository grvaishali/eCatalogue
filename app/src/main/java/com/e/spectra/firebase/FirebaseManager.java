package com.e.spectra.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.e.spectra.ui.data.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.inject.Inject;

import static com.e.spectra.constants.FirebaseManageConstants.BRAND_NAME;
import static com.e.spectra.constants.FirebaseManageConstants.CATEGORIES_ALL;
import static com.e.spectra.constants.FirebaseManageConstants.CATEGORY_BRAND;
import static com.e.spectra.constants.FirebaseManageConstants.CATEGORY_NAME;
import static com.e.spectra.constants.FirebaseManageConstants.COLLECTION_BRANDS;
import static com.e.spectra.constants.FirebaseManageConstants.COLLECTION_CATEGORIES;
import static com.e.spectra.constants.FirebaseManageConstants.COLLECTION_ITEMS;
import static com.e.spectra.constants.FirebaseManageConstants.COLLECTION_MASTER;
import static com.e.spectra.constants.FirebaseManageConstants.ITEM_BRAND;
import static com.e.spectra.constants.FirebaseManageConstants.ITEM_CATEGORY;
import static com.e.spectra.constants.FirebaseManageConstants.ITEM_CODE;

public class FirebaseManager {
    private static FirebaseFirestore db = null;
    private static FirebaseManager firebaseManager = null;
    Query first, next;
    OnCompleteListener<QuerySnapshot> onCompleteListener;

    @Inject
     FirebaseManager() {
    }

    public static FirebaseManager getInstance() {
        if (null == firebaseManager) {
            firebaseManager = new FirebaseManager();
            db = FirebaseFirestore.getInstance();
        }
        return firebaseManager;
    }

    public void getAllItems(OnCompleteListener<QuerySnapshot> onCompleteListener, String category, String brand) {
        Log.i("getAllItems ", category + ", " + brand);
        if (brand.equals(CATEGORIES_ALL)) {
            first = getInstance().getDb().collection(COLLECTION_ITEMS).whereEqualTo(ITEM_CATEGORY, category).orderBy(ITEM_CODE).limit(20);
            first.get().addOnCompleteListener(onCompleteListener);


        } else {
            getInstance().getDb().collection(COLLECTION_ITEMS).whereEqualTo(ITEM_CATEGORY, category).whereEqualTo(ITEM_BRAND, brand).orderBy(ITEM_CODE).get().addOnCompleteListener(onCompleteListener);
        }
    }

    public void getAllBrands(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        first = getInstance().getDb().collection(COLLECTION_BRANDS).orderBy(BRAND_NAME).limit(20);
        this.onCompleteListener = onCompleteListener;
        first.get().addOnCompleteListener(onCompleteListener);

    }

    public void getNext(DocumentSnapshot snapshot, int size) {
        next = getInstance().getDb().collection(COLLECTION_BRANDS).orderBy(BRAND_NAME).startAfter(snapshot).limit(size);
        next.get().addOnCompleteListener(onCompleteListener);
    }

    public void getAllCategories(OnCompleteListener<QuerySnapshot> onCompleteListener, String brandName) {
        Log.i("getAllCategories ", brandName);
        if (brandName.equals(CATEGORIES_ALL)) {
            getInstance().getDb().collection(COLLECTION_CATEGORIES).orderBy(CATEGORY_NAME).get().addOnCompleteListener(onCompleteListener);
        } else {
            getInstance().getDb().collection(COLLECTION_CATEGORIES).whereEqualTo(CATEGORY_BRAND, brandName).get().addOnCompleteListener(onCompleteListener);
        }
    }

    public void getBannerImages(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        getInstance().getDb().collection(COLLECTION_MASTER).get().addOnCompleteListener(onCompleteListener);
    }

    public void getOffer(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        getInstance().getDb().collection(COLLECTION_MASTER).get().addOnCompleteListener(onCompleteListener);
    }

    public static void create(String name, String password) {
        UserData userData = new UserData();
        userData.setName(name);
        userData.setPassword(password);


        getInstance().getDb().collection("users").document().set(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i("Status", "Successful");
                } else {
                    Log.i("Status", "Failed");
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public FirebaseFirestore getDb() {
        return db;
    }

    public static String readValue(QueryDocumentSnapshot snapshot, String attribute) {
        String readValue = "";
        try {
            readValue = snapshot.getString(attribute);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readValue;
    }

}
