package com.firstapp.myslot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Homepage extends AppCompatActivity {
    public static final String TAG = "tag";
    TextView verifyMsg;
    Button resendCode;
    FirebaseAuth fAuth;
    String userId;
    FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
            MaterialButton QRpage=(MaterialButton) findViewById(R.id.QRpage);
        MaterialButton History=(MaterialButton) findViewById(R.id.History);
        MaterialButton Profile=(MaterialButton) findViewById(R.id.Profile);
        MaterialButton Logout=(MaterialButton) findViewById(R.id.Logout);
        resendCode = findViewById(R.id.resendCode);
        verifyMsg = findViewById(R.id.verifyMsg);
        userId = fAuth.getCurrentUser().getUid();
        FirebaseUser user = fAuth.getCurrentUser();

        if(!user.isEmailVerified()){
            verifyMsg.setVisibility(View.VISIBLE);
            resendCode.setVisibility(View.VISIBLE);

            resendCode.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(final View v) {

                                                  user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                      @Override
                                                      public void onSuccess(Void aVoid) {
                                                          Toast.makeText(v.getContext(), "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                                                      }
                                                  }).addOnFailureListener(new OnFailureListener() {
                                                      @Override
                                                      public void onFailure(@NonNull Exception e) {
                                                          Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                                                      }
                                                  });
                                              }
                                          });
        }

            QRpage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getApplicationContext(),qrpage.class);
                    startActivity(intent);
                    finish();
                }
    });

                    History.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getApplicationContext(),Parking_Data.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                            Profile.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent=new Intent(getApplicationContext(),profile.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                                    Logout.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            FirebaseAuth.getInstance().signOut();
                                            Intent intent=new Intent(getApplicationContext(), Login.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
}
}