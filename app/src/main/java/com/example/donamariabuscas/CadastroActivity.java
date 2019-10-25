package com.example.donamariabuscas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

public class CadastroActivity extends AppCompatActivity {

    ImageView receberImage;
    Uri uri;
    EditText txt_nome_produto, txt_descricao_produto, txt_preco_produto;
    String imageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        receberImage = (ImageView) findViewById(R.id.iv_produtoImage);
        txt_nome_produto = (EditText) findViewById(R.id.txt_nome_produto);
        txt_descricao_produto = (EditText) findViewById(R.id.txt_descricao_produto);
        txt_preco_produto = (EditText) findViewById(R.id.txt_preco_produto);


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
            receberImage.setImageURI(uri);


        } else Toast.makeText(this, "SELECIONE UMA IMAGEM", Toast.LENGTH_SHORT).show();

    }

    public void uploadImage() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("ReceberImage").child(uri.getLastPathSegment());

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
                uploadImage();
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


/*      Produtos produtos = new Produtos(

               txt_nome_produto.getText().toString(),
                txt_descricao_produto.getText().toString(),
                txt_preco_produto.getText().toString(),
                imageUrl
        );

        String myCurrentDateTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());


        FirebaseDatabase.getInstance().getReference("Recipe").child(myCurrentDateTime).setValue(produtos).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(CadastroActivity.this, "Cadastro realizado", Toast.LENGTH_SHORT).show();

                    finish();
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CadastroActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });*/

    }
}
