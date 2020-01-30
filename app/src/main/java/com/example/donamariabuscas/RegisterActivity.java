package com.example.donamariabuscas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText email, senha, nome, senhaconfirmar;
    Button mRegisterBtn;
    TextView mHaveAccountTv;
    Button mBtnSelectedPhoto;
    ImageView mImgPhoto;

    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;

    ProgressDialog progressDialog;


     FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        nome = findViewById(R.id.nome);
        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        senhaconfirmar = findViewById(R.id.senhaconfirmar);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mHaveAccountTv = findViewById(R.id.have_accountTv);

        auth = FirebaseAuth.getInstance();

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_nome = nome.getText().toString();
                String txt_email = email.getText().toString();
                String txt_senha = senha.getText().toString();
                String txt_confirmarsenha = senhaconfirmar.getText().toString();

                if (TextUtils.isEmpty(txt_nome) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_senha) || TextUtils.isEmpty(txt_confirmarsenha)) {
                    Toast.makeText(RegisterActivity.this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();

                }

                else if (txt_senha.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Escolha uma senha com mais de 6 caracteres", Toast.LENGTH_SHORT).show();
                }

                else  {
                    register(txt_nome, txt_email, txt_senha);
                }

            }
        });
    }



    private void register(String nome, String email, String senha){

        auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    assert firebaseUser != null;
                    String userid = firebaseUser.getUid();

                    reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("id", userid);
                    hashMap.put("username", nome);
                hashMap.put("imageURL", "default");

                reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(RegisterActivity.this,MenuActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();



                        }
                    }
                });

                } else {
                    Toast.makeText(RegisterActivity.this, "Falha no Registro, verifique os campos.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


}