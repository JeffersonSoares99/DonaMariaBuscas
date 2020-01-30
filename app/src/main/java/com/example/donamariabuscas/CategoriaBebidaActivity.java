package com.example.donamariabuscas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoriaBebidaActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<CategoriaBebida> myBebidaList;
    CategoriaBebida mBebida;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;
    MyAdapterBebida myAdapter;
    EditText txt_Search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_bebida);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("");

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(CategoriaBebidaActivity.this,1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        txt_Search = (EditText)findViewById(R.id.txt_searchtext);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando produtos...");

        myBebidaList = new ArrayList<>();



        myAdapter = new MyAdapterBebida(CategoriaBebidaActivity.this, myBebidaList);
        mRecyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("RecipeBebida");

        progressDialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myBebidaList.clear();

                for (DataSnapshot itemSnapshot: dataSnapshot.getChildren()){

                    CategoriaBebida categoriaBebida = itemSnapshot.getValue(CategoriaBebida.class);
                    categoriaBebida.setKey(itemSnapshot.getKey());
                    myBebidaList.add(categoriaBebida);

                }
                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });

        txt_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());

            }
        });






    }

    private void filter(String text) {

        ArrayList<CategoriaBebida> filterList = new ArrayList<>();

        for (CategoriaBebida item:myBebidaList){
            if (item.getItemName().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);

            }


        }

        myAdapter.filteredList(filterList);



    }


    /*
    public void btn_uploadActivity(View view) {

        startActivity(new Intent(this, CadastroActivity.class));
    }*/
}
