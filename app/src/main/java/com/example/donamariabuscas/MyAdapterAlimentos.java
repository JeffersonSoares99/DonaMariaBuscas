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

public class MyAdapterAlimentos extends RecyclerView.Adapter<AlimentosViewHolder> {


    private Context mContext;
    private List<CategoriaAlimentosBasicos> myAlimentosList;


    public MyAdapterAlimentos(Context mContext, List<CategoriaAlimentosBasicos> myAlimentosList) {
        this.mContext = mContext;
        this.myAlimentosList = myAlimentosList;
    }


    @Override
    public AlimentosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row_item, viewGroup, false);

        return new AlimentosViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlimentosViewHolder alimentosViewHolder, int i) {
        Glide.with(mContext)
                .load(myAlimentosList.get(i).getItemImage())
                .into(alimentosViewHolder.imageView);

        //   produtoViewHolder.imageView.setImageResource(myProdutoList.get(i).getItemImage());
        alimentosViewHolder.mTitle.setText(myAlimentosList.get(i).getItemName());
        alimentosViewHolder.mDescription.setText(myAlimentosList.get(i).getItemDescription());
        alimentosViewHolder.mPrice.setText(myAlimentosList.get(i).getItemPrice());
        alimentosViewHolder.mMercado.setText(myAlimentosList.get(i).getItemMercado());
        alimentosViewHolder.mEndereco.setText(myAlimentosList.get(i).getItemEndereco());


        alimentosViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetalheAlimentosActivity.class);
                intent.putExtra("Image", myAlimentosList.get(alimentosViewHolder.getAdapterPosition()).getItemImage());
                intent.putExtra("Description", myAlimentosList.get(alimentosViewHolder.getAdapterPosition()).getItemDescription());
                intent.putExtra("Title", myAlimentosList.get(alimentosViewHolder.getAdapterPosition()).getItemName());
                intent.putExtra("Price", myAlimentosList.get(alimentosViewHolder.getAdapterPosition()).getItemPrice());
                intent.putExtra("Mercado", myAlimentosList.get(alimentosViewHolder.getAdapterPosition()).getItemMercado());
                intent.putExtra("Endereco", myAlimentosList.get(alimentosViewHolder.getAdapterPosition()).getItemEndereco());
                intent.putExtra("keyValue", myAlimentosList.get(alimentosViewHolder.getAdapterPosition()).getKey());

                mContext.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() { return myAlimentosList.size(); }

    public void filteredList(ArrayList<CategoriaAlimentosBasicos> filterList) {
        myAlimentosList = filterList;
        notifyDataSetChanged();
    }
}

class AlimentosViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle,mDescription, mPrice, mMercado, mEndereco;
    CardView mCardView;

    public AlimentosViewHolder(@NonNull View itemView) {
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