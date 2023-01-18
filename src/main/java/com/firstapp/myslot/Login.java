package com.firstapp.myslot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    EditText TextInputemail,TextInputpassword,email;
    EditText fullname,phone;
    TextView Forgot_password;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=(EditText) findViewById(R.id.email);
        TextInputemail=(EditText) findViewById(R.id.username);
        TextInputpassword=(EditText) findViewById(R.id.password);
        MaterialButton loginBtn=(MaterialButton) findViewById(R.id.loginbtn);
        Forgot_password=(TextView)findViewById(R.id.Forgot_password) ;
        fAuth=FirebaseAuth.getInstance();


        Forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username,password,email1;

                email1= String.valueOf(TextInputemail.getText());
                password= String.valueOf(TextInputpassword.getText());

                fAuth.signInWithEmailAndPassword(email1,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Login.this, "Logged in", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Homepage.class));
                        }
                        else
                        {
                            Toast.makeText(Login.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }


        });
    }
}