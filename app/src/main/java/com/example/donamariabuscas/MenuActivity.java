package com.example.donamariabuscas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("DONA MARIA BUSCAS");

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.home);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();
                break;
            case R.id.produtos:
                startActivity(new Intent(MenuActivity.this, ProdutosActivity.class));
                break;
            case R.id.cadastro:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new CadastroFragment()).commit();
                break;
            case R.id.chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ChatFragment()).commit();
                break;
                case R.id.sair:
                    startActivity(new Intent(MenuActivity.this, LoginActivity.class));
                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}
