package com.zbadev.bottomnavigation;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

//import android.widget.Toolbar;
import android.widget.ShareActionProvider;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.zbadev.bottomnavigation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //ActivityMainBinding binding;
    public static int id=0x7f030004;
    public static int ids=0x7f030004;
    //Para menu slider
    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());*/
        setContentView(R.layout.activity_main);


        //Para menu slider
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.drawer_layout);
        //drawerLayout =(DrawerLayout) getLayoutInflater().inflate(R.layout.activity_main,null);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Asegurarse de que la Toolbar se infló correctamente
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        navigationView.setNavigationItemSelectedListener(this);
        //

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }*/

        // del otro
        //replaceFragment(new HomeFragment());
        bottomNavigationView.setBackground(null);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                id = item.getItemId();
                if(id == R.id.home){
                    replaceFragment(new HomeFragment());
                    return true;
                } else if (id == R.id.shorts) {
                    replaceFragment(new ShortsFragment());
                    return true;
                } else if (id == R.id.subscriptions) {
                    replaceFragment(new SuscriptionFragment());
                    return true;
                }else if (id == R.id.library){
                    replaceFragment(new LibraryFragment());
                    return true;
                }
                return true;
            }
        });

        fragmentManager = getSupportFragmentManager();
        replaceFragment(new HomeFragment());

       /* bottomNavigationView.setOnItemSelectedListener(item -> {
            id = item.getItemId();
            if(id == R.id.home){
                replaceFragment(new HomeFragment());
            } else if (id == R.id.shorts) {
                replaceFragment(new ShortsFragment());
            } else if (id == R.id.subscriptions) {
                replaceFragment(new SuscriptionFragment());
            }else if (id == R.id.library){
                replaceFragment(new LibraryFragment());
            }
            return true;
        });*/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });
    }


    private void replaceFragment(Fragment fragment){
        //FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    //para bottom sheet loyout
    private void showBottomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheet_layout);

        LinearLayout videoLayout = dialog.findViewById(R.id.layoutVideo);
        LinearLayout shortsLayout = dialog.findViewById(R.id.layoutShorts);
        LinearLayout liveLayout = dialog.findViewById(R.id.layoutLive);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this,"Upload a Video is clicked",Toast.LENGTH_SHORT).show();

            }
        });

        shortsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this,"Create a short is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        liveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this,"Go live is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        try {
            ids = item.getItemId();
            if(ids == R.id.nav_home){
                replaceFragment(new HomeFragment());
                return true;
                //getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
            } else if (ids == R.id.nav_settings) {
                replaceFragment(new ShortsFragment());
                return true;
                //getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ShortsFragment()).commit();
            } else if (ids == R.id.nav_share) {
                replaceFragment(new SuscriptionFragment());
                return true;
                //getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new SuscriptionFragment()).commit();
            }else if (ids == R.id.nav_about){
                replaceFragment(new LibraryFragment());
                return true;
                //getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new LibraryFragment()).commit();
            } else if (ids == R.id.nav_logout) {
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
                return true;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;

        /*switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ShortsFragment()).commit();
                break;
            case R.id.nav_share:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new SuscriptionFragment()).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new LibraryFragment()).commit();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
                break;
        }*/
            //drawerLayout.closeDrawer(GravityCompat.START);
        }catch (Exception e){
            Toast.makeText(this,"Data not inserted" + e.getMessage(),Toast.LENGTH_LONG).show();
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}