package com.example.donamariabuscas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mRegisterBtn, mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRegisterBtn = findViewById(R.id.register_btn);
        mLoginBtn = findViewById(R.id.login_btn);

    /*    mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterMercadoActivity.class));

            }
        });*/

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

    }

    public void btn_showDialog(View view) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.custom_dialog, null);

        Button btn_cliente = (Button)mView.findViewById(R.id.btn_cliente);
        Button btn_mercado = (Button)mView.findViewById(R.id.btn_supermercado);

        alert.setView(mView);

        final  AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
          alertDialog.show();

        btn_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        btn_mercado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, RegisterMercadoActivity.class));

            }
        });

    }
}
