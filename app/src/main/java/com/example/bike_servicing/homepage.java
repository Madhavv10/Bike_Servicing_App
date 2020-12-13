package com.example.bike_servicing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private DrawerLayout drawerLayout;
    private final int adding_bike =2;
    private Toolbar toolbar;
    private FirebaseAuth fauth;
    private FirebaseFirestore firestore;
    private String userid;
    private TextView username_tv,user_emailid_tv;
    mybike_tab mybike_tab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        username_tv = (TextView) findViewById(R.id.user_name_tv);
        user_emailid_tv = (TextView) findViewById(R.id.user_emailid_tv);


        NavigationView navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

        BottomNavigationView bottomNavView = findViewById(R.id.bottom_nav_bar);
        bottomNavView.setOnNavigationItemSelectedListener(navigationItemSelectedListenere);
        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container,new homefragment());

        //Getting user info from Firestore
        /*
        fauth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        userid = fauth.getCurrentUser().getUid();

        DocumentReference documentReference = firestore.collection("users").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                username_tv.setText(value.getString("fullname"));
                user_emailid_tv.setText(value.getString("emailid"));
            }
        });

         */

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.nav_app_bar_open_drawer_description,R.string.navigation_draw_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container,new homefragment()).commit();
            navigationView.setCheckedItem(R.id.home);
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Log.i("Height and Width :",height +" "+width);

        // dot menu
        toolbar.inflateMenu(R.menu.add_bike);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.add_bike_menu)
                {
                    Intent intent = new Intent(homepage.this,add_bike.class);
                    startActivityForResult(intent,adding_bike);
                }
                else if(item.getItemId()== R.id.update_address_menu)
                {
                    // do something
                }
                else{
                    // do something
                }

                return false;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 ){
            if(resultCode == RESULT_OK){
                Application_class.vehicles.add(new vehicle(data.getStringExtra("modelname"),data.getStringExtra("regno")
                        ,data.getStringExtra("type")));
                mybike_tab.notifychange();
            }else{
                Toast.makeText(homepage.this,"Fields were empty",Toast.LENGTH_SHORT).show();
            }
        }

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListenere = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.bottom_nav_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container,new homefragment()).commit();
                    break;

                case R.id.bottom_nav_orders:
                    getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container,new ordersfrag()).commit();
            }

            return true;
        }
    };


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container,new profile_fragment()).commit();
                break;
            case R.id.nav_payment:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container,new payment_fragment()).commit();
                break;
            case R.id.nav_logout:


                /*AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // user is now signed out
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                        });
        }*/

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                FirebaseAuth firebaseAuth;
                                firebaseAuth = FirebaseAuth.getInstance();
                                firebaseAuth.signOut();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.dismiss();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(homepage.this);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();








        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}