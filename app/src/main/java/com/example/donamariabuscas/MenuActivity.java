package com.example.donamariabuscas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
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
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{



    private int[] mImagens = new int[]{
            R.drawable.bannerdonamaria, R.drawable.bannerslide2

    };

    private String[] mImagensTitle = new String[]{
        "", ""

    };

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("");

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        verifyAuthentication();

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        TextView nometext = (TextView) headerView.findViewById(R.id.nometext);
        nometext.setText(getIntent().getStringExtra("nome"));


        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        CarouselView carouselView = findViewById(R.id.carousel);
        carouselView.setPageCount(mImagens.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImagens[position]);


            }
        });

        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MenuActivity.this, mImagensTitle[position], Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void verifyAuthentication() {
        if (FirebaseAuth.getInstance().getUid() == null){
            Intent intent = new Intent(MenuActivity.this, MainActivity .class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);


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
                startActivity(new Intent(MenuActivity.this, MenuActivity.class));
                break;
         /*   case R.id.produtos:
                startActivity(new Intent(MenuActivity.this, ProdutosActivity.class));
                break;*/
            case R.id.cadastro:

                final AlertDialog.Builder alert = new AlertDialog.Builder(MenuActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.custom_categoria, null);

                Button btn_alimentosbasico = (Button)mView.findViewById(R.id.btn_alimentosbasico);
                Button btn_carne = (Button)mView.findViewById(R.id.btn_carne);
                Button btn_bebida = (Button)mView.findViewById(R.id.btn_bebida);
                Button btn_bebidaalcoolica = (Button)mView.findViewById(R.id.btn_bebida_alcoolica);
                Button btn_outros = (Button)mView.findViewById(R.id.btn_outros);
                Button btn_limpeza = (Button)mView.findViewById(R.id.btn_limpeza);

                alert.setView(mView);

                final  AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);

                btn_alimentosbasico.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(MenuActivity.this, CadastroAlimentosActivity.class));
                    }
                });

                btn_carne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(MenuActivity.this, CadastroCarneActivity.class));

                    }
                });

                btn_limpeza.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(MenuActivity.this, CadastroLimpezaActivity.class));

                    }
                });

                btn_bebida.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(MenuActivity.this, CadastroBebidaActivity.class));

                    }
                });

                btn_bebidaalcoolica.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(MenuActivity.this, CadastroActivity.class));

                    }
                });

                btn_outros.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(MenuActivity.this, CadastroOutrosActivity.class));

                    }
                });

                alertDialog.show();
                break;

            case R.id.chat:
                startActivity(new Intent(MenuActivity.this, ChatActivity.class));
                break;
                case R.id.sobre:
                    startActivity(new Intent(MenuActivity.this, SobreActivity.class));
                break;
                case R.id.sair:
                    FirebaseAuth.getInstance().signOut();
                    verifyAuthentication();
                    break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    public void categoria_outros(View view) {
        startActivity(new Intent(MenuActivity.this, CategoriaOutrosActivity.class));
    }

    public void categoria_bebiba(View view) {
        startActivity(new Intent(MenuActivity.this, CategoriaBebidaActivity.class));

    }


    public void categoria_alimento_basico(View view) {

        startActivity(new Intent(MenuActivity.this, CategoriaABActivity.class));

    }

    public void categoria_carne(View view) {
        startActivity(new Intent(MenuActivity.this, CategoriaCarneActivity.class));

    }

    public void categoria_alcool(View view) {
        startActivity(new Intent(MenuActivity.this, ProdutosActivity.class));

    }

    public void categoria_limpeza(View view) {
        startActivity(new Intent(MenuActivity.this, CategoriaLimpezaActivity.class));

    }
}
