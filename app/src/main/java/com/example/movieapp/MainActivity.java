package com.example.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.movieapp.FragmentsBottom.FavoriteFragment;
import com.example.movieapp.FragmentsBottom.ProfileFragment;
import com.example.movieapp.FragmentsBottom.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView;
        SearchFragment searchFragment = new SearchFragment();
        FavoriteFragment favoriteFragment = new FavoriteFragment();
        ProfileFragment profilFragment = new ProfileFragment();
        FragmentMovie fragmentMovie = new FragmentMovie();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentMovie).commit();

        
        bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentMovie).commit();
                        break;

                    case R.id.nav_search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, searchFragment).commit();
                        break;
                    case R.id.nav_favorite:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, favoriteFragment).commit();
                        break;

                    case R.id.nav_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profilFragment).commit();
                        break;

                }
                return false;
            }
        });

    }
}