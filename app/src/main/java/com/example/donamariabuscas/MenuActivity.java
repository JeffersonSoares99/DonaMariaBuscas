package com.example.donamariabuscas;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class MenuActivity extends AppCompatActivity {


    GridView gridView;

    String[] namePlanets = {"Alimentos Basico", "Verdura", "Bebida Alcoolica", "Fruta", "Peixe", "Leite"};
    int[] imageProdutos = {R.drawable.alimentosbasico, R.drawable.verdura, R.drawable.bebidaalcolica,
            R.drawable.fruta, R.drawable.peixe, R.drawable.leite,
    };


    private DrawerLayout nDrawerLayout;
    private ActionBarDrawerToggle aToggle;



    private int[] mImagens = new int[]{
            R.drawable.bombom, R.drawable.fraldas, R.drawable.arroz, R.drawable.feijao

    };
    private String[] mImagensTitle = new String[]{


    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        nDrawerLayout = (DrawerLayout)findViewById(R.id.menu);
        aToggle = new ActionBarDrawerToggle(this, nDrawerLayout, R.string.open, R.string.close);
        nDrawerLayout.addDrawerListener(aToggle);
        aToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



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

        gridView = (GridView) findViewById(R.id.gridView);

        CustomAdapter customAdaper = new CustomAdapter();
        gridView.setAdapter(customAdaper);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), GridItemActivity.class);
                intent.putExtra("name", namePlanets[position]);
                intent.putExtra("image", imageProdutos[position]);
                startActivity(intent);
            }
        });
    }

    public class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return imageProdutos.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view1 = getLayoutInflater().inflate(R.layout.row_data, null);

            TextView name = view1.findViewById(R.id.txtPlanetas);
            ImageView image = view1.findViewById(R.id.images);

            name.setText(namePlanets[position]);
            image.setImageResource(imageProdutos[position]);

            return view1;
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (aToggle.onOptionsItemSelected(item)) {
            return true;

        }

        return super.onOptionsItemSelected(item);

    }
}
