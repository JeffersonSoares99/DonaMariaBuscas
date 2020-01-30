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

public class MyAdapterBebida extends RecyclerView.Adapter<BebidaViewHolder> {


    private Context mContext;
    private List<CategoriaBebida> myBebidaList;

    public MyAdapterBebida(Context mContext, List<CategoriaBebida> myBebidaList) {
        this.mContext = mContext;
        this.myBebidaList = myBebidaList;
    }

    @NonNull
    @Override
    public BebidaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row_item, viewGroup, false);

        return new BebidaViewHolder(mView);

    }

    @Override
    public void onBindViewHolder(@NonNull BebidaViewHolder bebidaViewHolder, int i) {


        Glide.with(mContext)
                .load(myBebidaList.get(i).getItemImage())
                .into(bebidaViewHolder.imageView);

        //   produtoViewHolder.imageView.setImageResource(myProdutoList.get(i).getItemImage());
        bebidaViewHolder.mTitle.setText(myBebidaList.get(i).getItemName());
        bebidaViewHolder.mDescription.setText(myBebidaList.get(i).getItemDescription());
        bebidaViewHolder.mPrice.setText(myBebidaList.get(i).getItemPrice());
        bebidaViewHolder.mMercado.setText(myBebidaList.get(i).getItemMercado());
        bebidaViewHolder.mEndereco.setText(myBebidaList.get(i).getItemEndereco());


        bebidaViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetalheBebidaActivity.class);
                intent.putExtra("Image", myBebidaList.get(bebidaViewHolder.getAdapterPosition()).getItemImage());
                intent.putExtra("Description", myBebidaList.get(bebidaViewHolder.getAdapterPosition()).getItemDescription());
                intent.putExtra("Title", myBebidaList.get(bebidaViewHolder.getAdapterPosition()).getItemName());
                intent.putExtra("Price", myBebidaList.get(bebidaViewHolder.getAdapterPosition()).getItemPrice());
                intent.putExtra("Mercado", myBebidaList.get(bebidaViewHolder.getAdapterPosition()).getItemMercado());
                intent.putExtra("Endereco", myBebidaList.get(bebidaViewHolder.getAdapterPosition()).getItemEndereco());
                intent.putExtra("keyValue", myBebidaList.get(bebidaViewHolder.getAdapterPosition()).getKey());

                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() { return myBebidaList.size(); }

    public void filteredList(ArrayList<CategoriaBebida> filterList) {

        myBebidaList = filterList;
        notifyDataSetChanged();
    }
}

class BebidaViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle,mDescription, mPrice, mMercado, mEndereco;
    CardView mCardView;

    public BebidaViewHolder(@NonNull View itemView) {
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
