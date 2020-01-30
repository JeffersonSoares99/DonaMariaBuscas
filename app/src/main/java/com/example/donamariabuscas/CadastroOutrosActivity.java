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
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class CadastroOutrosActivity extends AppCompatActivity {


    ImageView recipeImage;
    Uri uri;
    EditText txt_name, txt_description, txt_price, txt_mercado, txt_endereco;
    String imageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_outros);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        recipeImage = (ImageView) findViewById(R.id.iv_produtoImage);
        txt_name = (EditText) findViewById(R.id.txt_recipe_name);
        txt_description = (EditText) findViewById(R.id.text_description);
        txt_price = (EditText) findViewById(R.id.text_price);
        txt_mercado = (EditText) findViewById(R.id.text_mercado);
        txt_endereco = (EditText) findViewById(R.id.text_endereco);




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


        } else Toast.makeText(this, "SELECIONE UMA IMAGEM", Toast.LENGTH_SHORT).show();

    }

    public void uploadImage() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("RecipeImageOutros").child(uri.getLastPathSegment());

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cadastrando...");
        progressDialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();

                while (!uriTask.isComplete()) ;
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                uploadRecipe();
                progressDialog.dismiss();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });

    }

    public void btnUploadRecipe(View view) {
        uploadImage();
    }

    public void uploadRecipe() {
/*
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cadastrando...");
        progressDialog.show();

*/
        CategoriaOutros categoriaOutros = new CategoriaOutros(

                txt_name.getText().toString(),
                txt_description.getText().toString(),
                txt_price.getText().toString(),
                txt_mercado.getText().toString(),
                txt_endereco.getText().toString(),
                imageUrl
        );

        String myCurrentDateTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());


        FirebaseDatabase.getInstance().getReference("RecipeOutros").child(myCurrentDateTime).setValue(categoriaOutros).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(CadastroOutrosActivity.this, "Cadastro realizado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CadastroOutrosActivity.this, CategoriaOutrosActivity.class);
                    startActivity(intent);

                    finish();
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CadastroOutrosActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}