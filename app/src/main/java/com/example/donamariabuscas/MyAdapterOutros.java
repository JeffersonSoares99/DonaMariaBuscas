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

public class MyAdapterOutros extends RecyclerView.Adapter<OutrosViewHolder> {


    private Context mContext;
    private List<CategoriaOutros> myOutrosList;

    public MyAdapterOutros(Context mContext, List<CategoriaOutros> myOutrosList) {
        this.mContext = mContext;
        this.myOutrosList = myOutrosList;
    }


    @Override
    public OutrosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row_item, viewGroup, false);

        return new OutrosViewHolder(mView);

    }

    @Override
    public void onBindViewHolder(@NonNull OutrosViewHolder outrosViewHolder, int i) {


        Glide.with(mContext)
                .load(myOutrosList.get(i).getItemImage())
                .into(outrosViewHolder.imageView);

        //   produtoViewHolder.imageView.setImageResource(myProdutoList.get(i).getItemImage());
        outrosViewHolder.mTitle.setText(myOutrosList.get(i).getItemName());
        outrosViewHolder.mDescription.setText(myOutrosList.get(i).getItemDescription());
        outrosViewHolder.mPrice.setText(myOutrosList.get(i).getItemPrice());
        outrosViewHolder.mMercado.setText(myOutrosList.get(i).getItemMercado());
        outrosViewHolder.mEndereco.setText(myOutrosList.get(i).getItemEndereco());


        outrosViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetalheOutrosActivity.class);
                intent.putExtra("Image", myOutrosList.get(outrosViewHolder.getAdapterPosition()).getItemImage());
                intent.putExtra("Description", myOutrosList.get(outrosViewHolder.getAdapterPosition()).getItemDescription());
                intent.putExtra("Title", myOutrosList.get(outrosViewHolder.getAdapterPosition()).getItemName());
                intent.putExtra("Price", myOutrosList.get(outrosViewHolder.getAdapterPosition()).getItemPrice());
                intent.putExtra("Mercado", myOutrosList.get(outrosViewHolder.getAdapterPosition()).getItemMercado());
                intent.putExtra("Endereco", myOutrosList.get(outrosViewHolder.getAdapterPosition()).getItemEndereco());
                intent.putExtra("keyValue", myOutrosList.get(outrosViewHolder.getAdapterPosition()).getKey());

                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() { return myOutrosList.size(); }

    public void filteredList(ArrayList<CategoriaOutros> filterList) {

        myOutrosList = filterList;
        notifyDataSetChanged();
    }
}

class OutrosViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle,mDescription, mPrice, mMercado, mEndereco;
    CardView mCardView;

    public OutrosViewHolder(@NonNull View itemView) {
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
