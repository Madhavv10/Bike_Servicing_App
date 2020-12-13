package com.example.bike_servicing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {


    private EditText etemail,etpassword;
    private TextView tvnotverified;
    private Button btnsignup,btnlogin,btnnotverified,btn_skip;
    private SignInButton btngsignin;
    private FirebaseAuth mfirebaseauth;
    private GoogleSignInClient googleSignInClient;
    private int RC_signin = 1;
    private static final String TAG = "status";
    private FirebaseUser fuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);
        btnsignup = findViewById(R.id.btnsignup);
        btngsignin = findViewById(R.id.btngsignin);
        btnlogin = findViewById(R.id.btnlogin);
        mfirebaseauth = FirebaseAuth.getInstance();
        fuser = mfirebaseauth.getCurrentUser();
        tvnotverified = findViewById(R.id.tvnotverified);
        btnnotverified = findViewById(R.id.btnsendverification);
        tvnotverified.setVisibility(View.INVISIBLE);
        btnnotverified.setVisibility(View.INVISIBLE);
        btn_skip = findViewById(R.id.btn_skip);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),sign_up.class);
                startActivity(intent);
            }
        });

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,homepage.class);
                startActivity(intent);
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this,gso);

        btngsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailid = etemail.getText().toString().trim();
                String password = etpassword.getText().toString().trim();
                mfirebaseauth = FirebaseAuth.getInstance();
                fuser = mfirebaseauth.getCurrentUser();

                mfirebaseauth.signInWithEmailAndPassword(emailid,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful() && fuser.isEmailVerified()){
                            Toast.makeText(getApplicationContext(),"Succesfully loged in!",Toast.LENGTH_SHORT).show();
                            Log.i("Task :","Succesfully loged in");
                            Intent intent = new Intent(MainActivity.this,homepage.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(),"The emailid is not verified or registered",Toast.LENGTH_SHORT).show();
                            Log.i("Task :","Login error");
                            tvnotverified.setVisibility(View.VISIBLE);
                            btnnotverified.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });

        btnnotverified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fuser.sendEmailVerification();
                Toast.makeText(getApplicationContext(),"Email sent on your emailid pls verify!!",Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_signin);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_signin) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mfirebaseauth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mfirebaseauth.getCurrentUser();
                            updateUi(user);
                            Intent intent = new Intent(MainActivity.this,homepage.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUi(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUi(FirebaseUser user) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account != null){
            Log.i("person name :","Welcome "+ account.getDisplayName());
            Toast.makeText(getApplicationContext(),"Welcome : "+account.getDisplayName(),Toast.LENGTH_SHORT).show();
        }
    }
}