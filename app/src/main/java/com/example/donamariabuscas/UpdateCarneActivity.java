package com.example.donamariabuscas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UpdateCarneActivity extends AppCompatActivity {


    ImageView recipeImage;
    Uri uri;
    EditText txt_name, txt_description, txt_price, txt_mercado, txt_endereco;
    String imageUrl;
    String key, oldImageUrl;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    String recipename, recipeDescription, recipePrice, recipeMercado, recipeEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_carne);


        recipeImage = (ImageView) findViewById(R.id.iv_produtoImage);
        txt_name = (EditText) findViewById(R.id.txt_recipe_name);
        txt_description = (EditText) findViewById(R.id.text_description);
        txt_price = (EditText) findViewById(R.id.text_price);
        txt_mercado = (EditText) findViewById(R.id.text_mercado);
        txt_endereco = (EditText) findViewById(R.id.text_endereco);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            Glide.with(UpdateCarneActivity.this)
                    .load(bundle.getString("oldimageUrl"))
                    .into(recipeImage);
            txt_name.setText(bundle.getString("produtoNameKey"));
            txt_description.setText(bundle.getString("descriptionKey"));
            txt_price.setText(bundle.getString("priceKey"));
            txt_mercado.setText(bundle.getString("mercadoKey"));
            txt_endereco.setText(bundle.getString("enderecoKey"));
            key = bundle.getString("key");
            oldImageUrl = bundle.getString("oldimageUrl");
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("RecipeCarne").child(key);



    }





    public void btnSelectImage(View view) {
        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            uri = data.getData();
            recipeImage.setImageURI(uri);


        }

        else Toast.makeText(this, "SELECIONE UMA IMAGEM", Toast.LENGTH_SHORT).show();

    }



    public void btnUpdateRecipe(View view) {
        recipename = txt_name.getText().toString().trim();
        recipeDescription = txt_description.getText().toString().trim();
        recipePrice = txt_price.getText().toString();
        recipeMercado = txt_mercado.getText().toString();
        recipeEndereco = txt_endereco.getText().toString();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cadastrando...");
        progressDialog.show();

        storageReference =  FirebaseStorage.getInstance()
                .getReference().child("RecipeCarne").child(uri.getLastPathSegment());

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                uploadRecipe();
                Intent intent = new Intent(UpdateCarneActivity.this, CategoriaCarneActivity.class);
                startActivity(intent);
                finish();
                progressDialog.dismiss();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });

    }


    public void uploadRecipe() {
/*
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cadastrando...");
        progressDialog.show();

*/
        CategoriaCarne categoriaCarne = new CategoriaCarne(

                recipename,
                recipeDescription,
                recipePrice,
                recipeMercado,
                recipeEndereco,
                imageUrl

        );


        databaseReference.setValue(categoriaCarne).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                StorageReference storageReferenceNew = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageUrl);
                storageReferenceNew.delete();
                Toast.makeText(UpdateCarneActivity.this, "Produto Editado", Toast.LENGTH_SHORT).show();
                finish();
            }
        });





    }


}
