package com.example.donamariabuscas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetalheAlimentosActivity extends AppCompatActivity {

    TextView produtoDescricao, produtoName, produtoPrice, produtoMercado, produtoEndereco;
    ImageView produtoImagem;
    String key="";
    String imageUrl="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_alimentos);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("");

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        produtoDescricao = (TextView) findViewById(R.id.txtDescription);
        produtoImagem = (ImageView) findViewById(R.id.ivImage2);
        produtoName = (TextView) findViewById(R.id.txtName);
        produtoPrice = (TextView) findViewById(R.id.txtPrice);
        produtoMercado = (TextView) findViewById(R.id.txtMercado);
        produtoEndereco = (TextView) findViewById(R.id.txtEndereco);


        Bundle mBundle = getIntent().getExtras();

        if (mBundle!=null){
            produtoDescricao.setText(mBundle.getString("Description"));
            produtoName.setText(mBundle.getString("Title"));
            produtoPrice.setText(mBundle.getString("Price"));
            produtoMercado.setText(mBundle.getString("Mercado"));
            produtoEndereco.setText(mBundle.getString("Endereco"));
            key = mBundle.getString("keyValue");
            imageUrl = mBundle.getString("Image");
            // produtoImagem.setImageResource(mBundle.getInt("Image"));

            Glide.with(this)
                    .load(mBundle.getString("Image"))
                    .into(produtoImagem);
        }

    }

    public void btnDeleteRecipe(View view) {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("RecipeAlimentos");
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);

        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                reference.child(key).removeValue();
                Toast.makeText(DetalheAlimentosActivity.this, "Produto Excluido", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DetalheAlimentosActivity.this, CategoriaABActivity.class));
                finish();


            }
        });


    }

    public void btnUpdateRecipe(View view) {
        startActivity(new Intent(getApplicationContext(),UpdateAlimentosActivity.class)
                .putExtra("produtoNameKey", produtoName.getText().toString())
                .putExtra("descriptionKey", produtoDescricao.getText().toString())
                .putExtra("priceKey", produtoPrice.getText().toString())
                .putExtra("mercadoKey", produtoMercado.getText().toString())
                .putExtra("enderecoKey", produtoEndereco.getText().toString())
                .putExtra("oldimageUrl", imageUrl)
                .putExtra("key", key)
        );

    }

    public void abrirMaps(View view) {

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/place/23%C2%B042'09.5%22S+46%C2%B041'21.5%22W/@-23.7026301,-46.6914967,1058m/data=!3m2!1e3!4b1!4m13!1m6!3m5!1s0x9be46d5222a8f7:0xd0a91a78f6212c68!2sEtec+Irm%C3%A3+Agostina!8m2!3d-23.702723!4d-46.689277!3m5!1s0x0:0x0!7e2!8m2!3d-23.7026346!4d-46.6893082"));
        startActivity(intent);
    }
}
