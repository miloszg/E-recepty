package com.parse.starter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.parse.ParseUser;

public class UserMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Main Menu");
        setContentView(R.layout.activity_user_menu);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_meds:
                Toast.makeText(this, "Leki", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_calendar:
                Toast.makeText(this, "Kalendarz", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_notes:
                Intent intent=new Intent(this,ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                Toast.makeText(this, "Ustawienia", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                ParseUser.logOut();
                Toast.makeText(this, "Trwa wylogowanie...", Toast.LENGTH_SHORT).show();
                Intent intentLogout=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intentLogout);
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
