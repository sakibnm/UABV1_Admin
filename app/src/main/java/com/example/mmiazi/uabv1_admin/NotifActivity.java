package com.example.mmiazi.uabv1_admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

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
    private ImageView iv_Product;
    private String command;
    private String gender;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceRec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        ratingBar = findViewById(R.id.ratingBar_Notif);
        tv_Title = findViewById(R.id.tv_Notif_Title);
        tv_Name = findViewById(R.id.tv_Notif_Name);
        tv_Comment = findViewById(R.id.tv_Notif_Comment);
        iv_User = findViewById(R.id.iv_Notif_photo);
        iv_Product =findViewById(R.id.productImage);
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
                    case "Male":
                        ad = getMaleAd(command, dataSnapshot);
                        if(!ad.getRating().equals("false")){
                            ratingBar.setRating(Float.parseFloat(ad.getRating()));
                        }else ratingBar.setVisibility(View.INVISIBLE);
                        tv_Title.setText(ad.getProductName());
                        if(!ad.getName().equals("false"))tv_Name.setText(ad.getName());
                        else{
                            tv_Name.setText("");
                        }
                        if(!ad.getComment().equals("false")){
                            tv_Comment.setText(ad.getComment());
                        }else{
                            tv_Comment.setText("");
                        }

                        if(!ad.getUserPhoto().equals("false")){
                            try {
                                iv_User.setImageBitmap(decodeFromFireBase64(ad.getUserPhoto()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Picasso.get().load(ad.getProductPhoto()).into(iv_Product);
                        break;
                    case "Female":
                        ad = getFemaleAd(command, dataSnapshot);
                        if(!ad.getRating().equals("false")){
                            ratingBar.setRating(Float.parseFloat(ad.getRating()));
                        }else ratingBar.setVisibility(View.INVISIBLE);
                        tv_Title.setText(ad.getProductName());
                        if(!ad.getName().equals("false"))tv_Name.setText(ad.getName());
                        else{
                            tv_Name.setText("");
                        }
                        if(!ad.getComment().equals("false")){
                            tv_Comment.setText(ad.getComment());
                        }else{
                            tv_Comment.setText("");
                        }

                        if(!ad.getUserPhoto().equals("false")){
                            try {
                                iv_User.setImageBitmap(decodeFromFireBase64(ad.getUserPhoto()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Picasso.get().load(ad.getProductPhoto()).into(iv_Product);
                        break;
                    default:
                        ad = getOtherAd(command, dataSnapshot);
                        if(!ad.getRating().equals("false")){
                            ratingBar.setRating(Float.parseFloat(ad.getRating()));
                        }else ratingBar.setVisibility(View.INVISIBLE);
                        tv_Title.setText(ad.getProductName());
                        if(!ad.getName().equals("false"))tv_Name.setText(ad.getName());
                        else{
                            tv_Name.setText("");
                        }
                        if(!ad.getComment().equals("false")){
                            tv_Comment.setText(ad.getComment());
                        }else{
                            tv_Comment.setText("");
                        }

                        if(!ad.getUserPhoto().equals("false")){
                            try {
                                iv_User.setImageBitmap(decodeFromFireBase64(ad.getUserPhoto()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Picasso.get().load(ad.getProductPhoto()).into(iv_Product);
                        break;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    private AdStruct getOtherAd(String command, DataSnapshot dataSnapshot) {
        AdStruct iAd = null;

        switch (command){
            case "advertised1":
                iAd.setProductName(dataSnapshot.child("cads").child("other").child("cad1").child("productName").getValue().toString());
                iAd.setProductPhoto(dataSnapshot.child("cads").child("other").child("cad1").child("productPhoto").getValue().toString());
                iAd.setName(dataSnapshot.child("currentUser").child("createAd1").child("uname").getValue().toString());
                iAd.setComment(dataSnapshot.child("currentUser").child("createAd1").child("ucomment").getValue().toString());
                iAd.setRating(dataSnapshot.child("currentUser").child("createAd1").child("urating").getValue().toString());
                iAd.setUserPhoto(dataSnapshot.child("currentUser").child("createAd1").child("uphoto").getValue().toString());
                return iAd;
            case "advertised2":
                iAd.setProductName(dataSnapshot.child("cads").child("other").child("cad2").child("productName").getValue().toString());
                iAd.setProductPhoto(dataSnapshot.child("cads").child("other").child("cad2").child("productPhoto").getValue().toString());
                iAd.setName(dataSnapshot.child("currentUser").child("createAd2").child("uname").getValue().toString());
                iAd.setComment(dataSnapshot.child("currentUser").child("createAd2").child("ucomment").getValue().toString());
                iAd.setRating(dataSnapshot.child("currentUser").child("createAd2").child("urating").getValue().toString());
                iAd.setUserPhoto(dataSnapshot.child("currentUser").child("createAd2").child("uphoto").getValue().toString());
                return iAd;
            case "advertised3":
                iAd.setProductName(dataSnapshot.child("cads").child("other").child("cad3").child("productName").getValue().toString());
                iAd.setProductPhoto(dataSnapshot.child("cads").child("other").child("cad3").child("productPhoto").getValue().toString());
                iAd.setName(dataSnapshot.child("currentUser").child("createAd3").child("uname").getValue().toString());
                iAd.setComment(dataSnapshot.child("currentUser").child("createAd3").child("ucomment").getValue().toString());
                iAd.setRating(dataSnapshot.child("currentUser").child("createAd3").child("urating").getValue().toString());
                iAd.setUserPhoto(dataSnapshot.child("currentUser").child("createAd3").child("uphoto").getValue().toString());
                return iAd;
        }

        return iAd;
    }

    private AdStruct getFemaleAd(String command, DataSnapshot dataSnapshot) {
        AdStruct iAd = new AdStruct();

        switch (command){
            case "advertised1":
                iAd.setProductName(dataSnapshot.child("cads").child("female").child("cad1").child("productName").getValue().toString());
                iAd.setProductPhoto(dataSnapshot.child("cads").child("female").child("cad1").child("productPhoto").getValue().toString());
                iAd.setName(dataSnapshot.child("currentUser").child("createAd1").child("uname").getValue().toString());
                iAd.setComment(dataSnapshot.child("currentUser").child("createAd1").child("ucomment").getValue().toString());
                iAd.setRating(dataSnapshot.child("currentUser").child("createAd1").child("urating").getValue().toString());
                iAd.setUserPhoto(dataSnapshot.child("currentUser").child("createAd1").child("uphoto").getValue().toString());
                return iAd;
            case "advertised2":
                iAd.setProductName(dataSnapshot.child("cads").child("female").child("cad2").child("productName").getValue().toString());
                iAd.setProductPhoto(dataSnapshot.child("cads").child("female").child("cad2").child("productPhoto").getValue().toString());
                iAd.setName(dataSnapshot.child("currentUser").child("createAd2").child("uname").getValue().toString());
                iAd.setComment(dataSnapshot.child("currentUser").child("createAd2").child("ucomment").getValue().toString());
                iAd.setRating(dataSnapshot.child("currentUser").child("createAd2").child("urating").getValue().toString());
                iAd.setUserPhoto(dataSnapshot.child("currentUser").child("createAd2").child("uphoto").getValue().toString());
                return iAd;
            case "advertised3":
                iAd.setProductName(dataSnapshot.child("cads").child("female").child("cad3").child("productName").getValue().toString());
                iAd.setProductPhoto(dataSnapshot.child("cads").child("female").child("cad3").child("productPhoto").getValue().toString());
                iAd.setName(dataSnapshot.child("currentUser").child("createAd3").child("uname").getValue().toString());
                iAd.setComment(dataSnapshot.child("currentUser").child("createAd3").child("ucomment").getValue().toString());
                iAd.setRating(dataSnapshot.child("currentUser").child("createAd3").child("urating").getValue().toString());
                iAd.setUserPhoto(dataSnapshot.child("currentUser").child("createAd3").child("uphoto").getValue().toString());
                return iAd;
        }

        return iAd;
    }

    private AdStruct getMaleAd(String command, DataSnapshot dataSnapshot) {
        AdStruct iAd = null;

        switch (command){
            case "advertised1":
                iAd.setProductName(dataSnapshot.child("cads").child("male").child("cad1").child("productName").getValue().toString());
                iAd.setProductPhoto(dataSnapshot.child("cads").child("male").child("cad1").child("productPhoto").getValue().toString());
                iAd.setName(dataSnapshot.child("currentUser").child("createAd1").child("uname").getValue().toString());
                iAd.setComment(dataSnapshot.child("currentUser").child("createAd1").child("ucomment").getValue().toString());
                iAd.setRating(dataSnapshot.child("currentUser").child("createAd1").child("urating").getValue().toString());
                iAd.setUserPhoto(dataSnapshot.child("currentUser").child("createAd1").child("uphoto").getValue().toString());
                return iAd;
            case "advertised2":
                iAd.setProductName(dataSnapshot.child("cads").child("male").child("cad2").child("productName").getValue().toString());
                iAd.setProductPhoto(dataSnapshot.child("cads").child("male").child("cad2").child("productPhoto").getValue().toString());
                iAd.setName(dataSnapshot.child("currentUser").child("createAd2").child("uname").getValue().toString());
                iAd.setComment(dataSnapshot.child("currentUser").child("createAd2").child("ucomment").getValue().toString());
                iAd.setRating(dataSnapshot.child("currentUser").child("createAd2").child("urating").getValue().toString());
                iAd.setUserPhoto(dataSnapshot.child("currentUser").child("createAd2").child("uphoto").getValue().toString());
                return iAd;
            case "advertised3":
                iAd.setProductName(dataSnapshot.child("cads").child("male").child("cad3").child("productName").getValue().toString());
                iAd.setProductPhoto(dataSnapshot.child("cads").child("male").child("cad3").child("productPhoto").getValue().toString());
                iAd.setName(dataSnapshot.child("currentUser").child("createAd3").child("uname").getValue().toString());
                iAd.setComment(dataSnapshot.child("currentUser").child("createAd3").child("ucomment").getValue().toString());
                iAd.setRating(dataSnapshot.child("currentUser").child("createAd3").child("urating").getValue().toString());
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
    private Bitmap decodeFromFireBase64(String photoUrl) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(photoUrl, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        finish();
        databaseReference.child("signalFromAdmin").child("command").setValue("empty");
        firebaseDatabase.getReference().child("signalToAdmin").child("command").setValue("empty");
        startActivity(intent);

    }
}


