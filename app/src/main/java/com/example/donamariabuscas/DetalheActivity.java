package com.example.donamariabuscas;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetalheActivity extends AppCompatActivity {

    TextView produtoDescricao;
    ImageView produtoImagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        produtoDescricao = (TextView) findViewById(R.id.txtDescricao);
        produtoImagem = (ImageView) findViewById(R.id.ivImage2);

        Bundle mBundle = getIntent().getExtras();

        if (mBundle!=null){
            produtoDescricao.setText(mBundle.getString("Descricao"));
            produtoImagem.setImageResource(mBundle.getInt("Image"));

        }

    }
}
