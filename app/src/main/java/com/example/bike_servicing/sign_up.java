package com.example.bike_servicing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class sign_up extends AppCompatActivity {

    private EditText emailsignup,signuppass,confirmpass,etfullname,etphoneno;
    private Button btnsignupp,btnemailverify;
    private ImageView ivverify;
    private ProgressDialog progressDialog;
    private FirebaseAuth mfirebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseUser fuser;
    private boolean proceed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailsignup = findViewById(R.id.etemailsignup);
        signuppass = findViewById(R.id.etsignuppass);
        confirmpass = findViewById(R.id.etconfirmpass);
        btnsignupp = findViewById(R.id.btnsignupp);
        etfullname = findViewById(R.id.etfullname);
        etphoneno = findViewById(R.id.etphoneno);
        btnemailverify = findViewById(R.id.btnemailverify);
        //ivverify = findViewById(R.id.ivverify);
        progressDialog = new ProgressDialog(this);


        btnsignupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


    }

    private void registerUser(){

        //getting email and password from edit texts


        //if the email and password are not empty
        //displaying a progress dialog

        String email = emailsignup.getText().toString().trim();
        String password  = signuppass.getText().toString().trim();
        String cnfpassword  = confirmpass.getText().toString().trim();
        String fullname = etfullname.getText().toString().trim();
        String phoneno = etphoneno.getText().toString().trim();

        mfirebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        fuser = mfirebaseAuth.getCurrentUser();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password) || TextUtils.isEmpty(cnfpassword)){
            Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        mfirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){

                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(),"EMail Verification Sent!! Pls verify to proceed",Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(),"Could not send the email, email may not exist!",Toast.LENGTH_SHORT).show();
                                }
                            });


                                Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_LONG).show();
                                String userID = mfirebaseAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);

                                Map<String,Object> user = new HashMap<>();
                                user.put("fullname",fullname);
                                user.put("emailid",email);
                                user.put("phoneno",phoneno);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.i("onSuccess","User profile is saved for userid :"+userID);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.i("OnFailure : ","Exception :" + e.toString());
                                    }
                                });


                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);




                            //display some message here

                        }else{
                            //display some message here
                            Toast.makeText(getApplicationContext(),"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }



}