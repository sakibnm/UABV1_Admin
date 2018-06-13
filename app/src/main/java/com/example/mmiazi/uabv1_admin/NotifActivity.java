package com.example.mmiazi.uabv1_admin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NotifActivity extends AppCompatActivity {

    private AdStruct ad;
    private String name;
    private float rating;
    private String userPhoto;
    private String productPhoto;
    private String comment;
    private String productName;
    private int fontColor;
    private RatingBar ratingBar;
    private TextView tv_Title;
    private TextView tv_Name;
    private TextView tv_Comment;
    private ImageView iv_User;
    private String command;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);

        ratingBar = findViewById(R.id.ratingBar_Notif);
        tv_Title = findViewById(R.id.tv_Notif_Title);
        tv_Name = findViewById(R.id.tv_Notif_Name);
        tv_Comment = findViewById(R.id.tv_Notif_Comment);
        iv_User = findViewById(R.id.iv_Notif_photo);
        final ConstraintLayout back_Notif = findViewById(R.id.back_notif);

        command = "empty";
        gender= "other";

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        ArrayList<AdStruct> sentAdsFromAdmin = new ArrayList<AdStruct>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                command = dataSnapshot.child("signalToAdmin").child("command").getValue().toString();
                gender = dataSnapshot.child("currentUser").child("gender").getValue().toString();
                AdStruct ad = null;

                switch(gender){
                    case "male":
                        ad = getMaleAd(command, dataSnapshot);
                        break;
                    case "female":
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    private AdStruct getMaleAd(String command, DataSnapshot dataSnapshot) {
        AdStruct iAd = null;

        switch (command){
            case "advertised1":
                iAd.setProductName(dataSnapshot.child("cads").child("male").child("cad1").child("productName").getValue().toString());
                iAd.setProductPhoto(dataSnapshot.child("cads").child("male").child("cad1").child("productPhoto").getValue().toString());
                iAd.setName(dataSnapshot.child("currentUser").child("createAd1").child("uname").getValue().toString());
                iAd.setComment(dataSnapshot.child("currentUser").child("createAd1").child("ucomment").getValue().toString());
                iAd.setRating(Float.parseFloat(dataSnapshot.child("currentUser").child("createAd1").child("urating").getValue().toString()));
                iAd.setUserPhoto(dataSnapshot.child("currentUser").child("createAd1").child("uphoto").getValue().toString());
                return iAd;
            case "advertised2":
                iAd.setProductName(dataSnapshot.child("cads").child("male").child("cad2").child("productName").getValue().toString());
                iAd.setProductPhoto(dataSnapshot.child("cads").child("male").child("cad2").child("productPhoto").getValue().toString());
                iAd.setName(dataSnapshot.child("currentUser").child("createAd2").child("uname").getValue().toString());
                iAd.setComment(dataSnapshot.child("currentUser").child("createAd2").child("ucomment").getValue().toString());
                iAd.setRating(Float.parseFloat(dataSnapshot.child("currentUser").child("createAd2").child("urating").getValue().toString()));
                iAd.setUserPhoto(dataSnapshot.child("currentUser").child("createAd2").child("uphoto").getValue().toString());
                return iAd;
            case "advertised3":
                iAd.setProductName(dataSnapshot.child("cads").child("male").child("cad3").child("productName").getValue().toString());
                iAd.setProductPhoto(dataSnapshot.child("cads").child("male").child("cad3").child("productPhoto").getValue().toString());
                iAd.setName(dataSnapshot.child("currentUser").child("createAd3").child("uname").getValue().toString());
                iAd.setComment(dataSnapshot.child("currentUser").child("createAd3").child("ucomment").getValue().toString());
                iAd.setRating(Float.parseFloat(dataSnapshot.child("currentUser").child("createAd3").child("urating").getValue().toString()));
                iAd.setUserPhoto(dataSnapshot.child("currentUser").child("createAd3").child("uphoto").getValue().toString());
                return iAd;
        }

        return iAd;
    }

    class GetImageFromURL extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ConstraintLayout constraintLayout = findViewById(R.id.back_notif);
            Drawable dr = new BitmapDrawable(bitmap);
            dr.setBounds(0, 0, 480, 840);
            constraintLayout.setBackground(dr);
            Log.d("test", "background set");

        }
    }
}


