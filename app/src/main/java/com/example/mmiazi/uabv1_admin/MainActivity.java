package com.example.mmiazi.uabv1_admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button adButton1;
    private Button adButton2;
    private Button adButton3;
    private Button clearButton;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Admin Panel");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        adButton1 = findViewById(R.id.buttonAd1);
        adButton2 = findViewById(R.id.buttonAd2);
        adButton3 = findViewById(R.id.buttonAd3);
        clearButton = findViewById(R.id.buttonClear);

        adButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("signalFromAdmin").child("command").setValue("notifyAd1");
            }
        });

        adButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("signalFromAdmin").child("command").setValue("notifyAd2");
            }
        });

        adButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("signalFromAdmin").child("command").setValue("notifyAd3");
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseCreateAd createAd1 = new FirebaseCreateAd("false", "false","false","false");
                FirebaseCreateAd createAd2 = new FirebaseCreateAd("false", "false","false","false");
                FirebaseCreateAd createAd3 = new FirebaseCreateAd("false", "false","false","false");

                CurrentUser user = new CurrentUser(createAd1, createAd2, createAd3, "empty", "empty", "empty", "empty", "empty");
                databaseReference.child("currentUser").setValue(user);
                databaseReference.child("signalFromAdmin").child("command").setValue("empty");
            }
        });
    }

}
