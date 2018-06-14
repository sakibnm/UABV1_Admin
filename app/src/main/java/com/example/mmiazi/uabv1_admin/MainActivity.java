package com.example.mmiazi.uabv1_admin;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button adButton1;
    private Button adButton2;
    private Button adButton3;
    private Button clearButton;

    private Bitmap userPhoto;
    private Uri uriUserPhoto;
    private boolean loggedIn;

    private String CHANNEL_ID = "Channel";
    private String notificationCommand = "empty";

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceRec;


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
                firebaseDatabase.getReference().child("signalToAdmin").child("command").setValue("empty");

            }
        });

        databaseReferenceRec = firebaseDatabase.getReference().child("signalToAdmin").child("command");
        databaseReferenceRec.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                notificationCommand = dataSnapshot.getValue().toString();
                if(notificationCommand.equals("advertised1") || notificationCommand.equals("advertised2") || notificationCommand.equals("advertised3")) {
                    Log.d("test", notificationCommand+"");
                    createNotification();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void createNotification(){
        //        TODO: Notification receive.....
        createNotificationChannel();

        Intent intent = new Intent(getApplicationContext(), NotifActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setContentTitle("Ad Received!")
                .setContentText("Someone nearby published a product review!")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Someone nearby published a product review!"))
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, mBuilder.build());


    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
