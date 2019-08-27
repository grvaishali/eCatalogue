package com.e.ecatalogue.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.e.ecatalogue.data.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class FirebaseManager {
    public static final String ITEM_CODE = "code";
    public static final String ITEM_DESCRIPTION = "description";
    public static final String ITEM_IMAGEURL = "imageurl";
    public static final String ITEM_NAME = "name";
    public static final String ITEM_PRICE = "price";
    public static final String ITEM_CATEGORY = "category";
    public static final String ITEM_BRAND = "brand";
    public static final String BRAND_NAME = "name";
    public static final String CATEGORY_NAME = "name";
    public static final String CATEGORY_BRAND = "brand";
    public static final String BRAND_IMAGEURL = "imageurl";
    public static final String MASTER_MAIN_BANNER_URL = "mainbannerurl";
    public static final String MASTER_LOGO_IMAGE_URL = "logoimageurl";
    public static final String MASTER_MAIN_PROMOTION_TEXT = "promotiontext";
    public static final String COLLECTION_ITEMS = "items";
    public static final String COLLECTION_BRANDS = "brands";
    public static final String COLLECTION_CATEGORIES = "categories";
    public static final String COLLECTION_MASTER = "master";
    public static final String CATEGORIES_ALL = "ALL";
    private static FirebaseFirestore db = null;
    private static FirebaseManager firebaseManager = null;

    private FirebaseManager() {
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
            getInstance().getDb().collection(COLLECTION_ITEMS).whereEqualTo(ITEM_CATEGORY, category).orderBy(ITEM_CODE).get().addOnCompleteListener(onCompleteListener);
        } else {
            getInstance().getDb().collection(COLLECTION_ITEMS).whereEqualTo(ITEM_CATEGORY, category).whereEqualTo(ITEM_BRAND, brand).orderBy(ITEM_CODE).get().addOnCompleteListener(onCompleteListener);
        }
    }

    public void getAllBrands(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        getInstance().getDb().collection(COLLECTION_BRANDS).orderBy(BRAND_NAME).get().addOnCompleteListener(onCompleteListener);
    }

    public void getAllCategories(OnCompleteListener<QuerySnapshot> onCompleteListener, String brandName) {
        Log.i("getAllCategories ", brandName );
        if (brandName.equals(CATEGORIES_ALL)) {
            getInstance().getDb().collection(COLLECTION_CATEGORIES).orderBy(CATEGORY_NAME).get().addOnCompleteListener(onCompleteListener);
        } else {
            getInstance().getDb().collection(COLLECTION_CATEGORIES).whereEqualTo(CATEGORY_BRAND, brandName).orderBy(CATEGORY_NAME).get().addOnCompleteListener(onCompleteListener);
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
