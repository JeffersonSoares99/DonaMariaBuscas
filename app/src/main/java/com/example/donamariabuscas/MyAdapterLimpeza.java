package com.example.donamariabuscas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterLimpeza extends RecyclerView.Adapter<LimpezaViewHolder> {


    private Context mContext;
    private List<CategoriaLimpeza> myLimpezaList;


    public MyAdapterLimpeza(Context mContext, List<CategoriaLimpeza> myLimpezaList) {
        this.mContext = mContext;
        this.myLimpezaList = myLimpezaList;
    }


    @Override
    public LimpezaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row_item, viewGroup, false);

        return new LimpezaViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull LimpezaViewHolder limpezaViewHolder, int i) {
        Glide.with(mContext)
                .load(myLimpezaList.get(i).getItemImage())
                .into(limpezaViewHolder.imageView);

        //   produtoViewHolder.imageView.setImageResource(myProdutoList.get(i).getItemImage());
        limpezaViewHolder.mTitle.setText(myLimpezaList.get(i).getItemName());
        limpezaViewHolder.mDescription.setText(myLimpezaList.get(i).getItemDescription());
        limpezaViewHolder.mPrice.setText(myLimpezaList.get(i).getItemPrice());
        limpezaViewHolder.mMercado.setText(myLimpezaList.get(i).getItemMercado());
        limpezaViewHolder.mEndereco.setText(myLimpezaList.get(i).getItemEndereco());


        limpezaViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetalheLimpezaActivity.class);
                intent.putExtra("Image", myLimpezaList.get(limpezaViewHolder.getAdapterPosition()).getItemImage());
                intent.putExtra("Description", myLimpezaList.get(limpezaViewHolder.getAdapterPosition()).getItemDescription());
                intent.putExtra("Title", myLimpezaList.get(limpezaViewHolder.getAdapterPosition()).getItemName());
                intent.putExtra("Price", myLimpezaList.get(limpezaViewHolder.getAdapterPosition()).getItemPrice());
                intent.putExtra("Mercado", myLimpezaList.get(limpezaViewHolder.getAdapterPosition()).getItemMercado());
                intent.putExtra("Endereco", myLimpezaList.get(limpezaViewHolder.getAdapterPosition()).getItemEndereco());
                intent.putExtra("keyValue", myLimpezaList.get(limpezaViewHolder.getAdapterPosition()).getKey());

                mContext.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() { return myLimpezaList.size(); }

    public void filteredList(ArrayList<CategoriaLimpeza> filterList) {
        myLimpezaList = filterList;
        notifyDataSetChanged();
    }
}

class LimpezaViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle,mDescription, mPrice, mMercado, mEndereco;
    CardView mCardView;

    public LimpezaViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mDescription = itemView.findViewById(R.id.tvDescription);
        mPrice = itemView.findViewById(R.id.tvPrice);
        mMercado = itemView.findViewById(R.id.tvMercado);
        mEndereco = itemView.findViewById(R.id.tvEndereco);



        mCardView = itemView.findViewById(R.id.myCardView);

    }
}