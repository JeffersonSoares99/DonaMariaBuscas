package com.example.donamariabuscas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProdutosActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    List<Produtos> myProdutoList;
    Produtos mProdutos;


    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("PRODUTOS");

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        setContentView(R.layout.activity_produtos);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(ProdutosActivity.this,1);
        mRecyclerView.setLayoutManager(gridLayoutManager);


        myProdutoList = new ArrayList<>();

        mProdutos = new Produtos("Peito de frango","1 kg de peito de frango com osso","R$9,90",R.drawable.frango);
        myProdutoList.add(mProdutos);

        mProdutos = new Produtos("Papel Higienico","Leve 16 pague apenas 15","R$15,90", R.drawable.papelhigi);
        myProdutoList.add(mProdutos);
        mProdutos = new Produtos("Coca cola","Coca cola 2L ","R$5,19",R.drawable.coca);
        myProdutoList.add(mProdutos);


        MyAdapter myAdapter = new MyAdapter(ProdutosActivity.this,myProdutoList);
        mRecyclerView.setAdapter(myAdapter);

    }
}
