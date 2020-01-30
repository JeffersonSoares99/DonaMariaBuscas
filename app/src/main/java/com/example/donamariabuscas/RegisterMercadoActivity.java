package com.example.donamariabuscas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterMercadoActivity extends AppCompatActivity {

    EditText nomeMercado, enderecoMercado, cnpjMercado, mEmail, mSenha, mSenhaConfirm, telefoneMercado;
    Button mRegisterBtn;
    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_mercado);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        nomeMercado = (EditText) findViewById(R.id.nomemercadoEt);
        enderecoMercado = (EditText) findViewById(R.id.enderecoEt);
        cnpjMercado = (EditText) findViewById(R.id.cnpjEt);
        telefoneMercado = (EditText) findViewById(R.id.telefoneEt);
        mEmail = (EditText) findViewById(R.id.emailEt);
        mSenha = (EditText) findViewById(R.id.passwordEt);
        mSenhaConfirm = (EditText) findViewById(R.id.passwordconfirmEt);
        mRegisterBtn = (Button) findViewById(R.id.registerBtn);


        SimpleMaskFormatter smf = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(telefoneMercado, smf);
        telefoneMercado.addTextChangedListener(mtw);

        SimpleMaskFormatter simpleMaskFormatter = new SimpleMaskFormatter("NN.NNN.NNN/NNNN-NN");
        MaskTextWatcher maskTextWatcher = new MaskTextWatcher(cnpjMercado, simpleMaskFormatter);
        cnpjMercado.addTextChangedListener(maskTextWatcher);


        auth = FirebaseAuth.getInstance();


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtnome = nomeMercado.getText().toString();
                String txtendereco = enderecoMercado.getText().toString();
                String txtcnpj = cnpjMercado.getText().toString();
                String txttelefone = telefoneMercado.getText().toString();
                String txtemail = mEmail.getText().toString();
                String txtsenha = mSenha.getText().toString();
                String txtsenhaconfirm = mSenhaConfirm.getText().toString();

                if (TextUtils.isEmpty(txtnome)) {
                    Toast.makeText(RegisterMercadoActivity.this, "Insira o nome do Supermercado", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(txtendereco)) {
                    Toast.makeText(RegisterMercadoActivity.this, "Insira o Endereco do Supermercado", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(txtcnpj)) {
                    Toast.makeText(RegisterMercadoActivity.this, "Insira o CNPJ Supermercado", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(txttelefone)) {
                    Toast.makeText(RegisterMercadoActivity.this, "Insira o Telefone do Supermercado", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(txtemail).matches()) {

                    mEmail.setError("Email invalido");
                    mEmail.setFocusable(true);
                    return;
                }


                if (TextUtils.isEmpty(txtsenha)) {
                    Toast.makeText(RegisterMercadoActivity.this, "Insira uma senha", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(txtsenhaconfirm)) {
                    Toast.makeText(RegisterMercadoActivity.this, "Confirme sua senha", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (txtsenha.length() < 6) {

                    Toast.makeText(RegisterMercadoActivity.this, "Escolha um senha com mais de 6 caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (txtsenha.equals(txtsenhaconfirm)) {

                    auth.createUserWithEmailAndPassword(txtemail, txtsenha)
                            .addOnCompleteListener(RegisterMercadoActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Usuario information = new Usuario(
                                                txtnome,
                                                txtendereco,
                                                txtcnpj,
                                                txttelefone,
                                                txtemail
                                        );


                                        FirebaseUser firebaseUser = auth.getCurrentUser();
                                        assert firebaseUser != null;
                                        String userid = firebaseUser.getUid();

                                        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                                        HashMap<String, String> hashMap = new HashMap<>();
                                        hashMap.put("id", userid);
                                        hashMap.put("username", txtnome);
                                        hashMap.put("userendereco", txtendereco);
                                        hashMap.put("usercnpj", txtcnpj);
                                        hashMap.put("usertelefone", txttelefone);
                                        hashMap.put("useremail", txtemail);
                                        hashMap.put("imageURL", "default");

                                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Intent intent = new Intent(RegisterMercadoActivity.this, MenuActivity.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    startActivity(intent);
                                                    finish();


                                                }
                                            }
                                        });


                                    } else {

                                        Toast.makeText(RegisterMercadoActivity.this, "Falha no Registro", Toast.LENGTH_SHORT).show();

                                    }


                                }
                            });

                }
            }
        });


    }

}