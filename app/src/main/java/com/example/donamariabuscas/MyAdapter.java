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

public class MyAdapter  extends  RecyclerView.Adapter<ProdutoViewHolder> {

    private Context mContext;
    private List<Produtos> myProdutoList;

    public MyAdapter(Context mContext, List<Produtos> myProdutoList) {
        this.mContext = mContext;
        this.myProdutoList = myProdutoList;
    }

    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row_item, viewGroup, false);

        return new ProdutoViewHolder(mView);

    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder produtoViewHolder, int i) {

       Glide.with(mContext)
                .load(myProdutoList.get(i).getItemImage())
               .into(produtoViewHolder.imageView);

      //   produtoViewHolder.imageView.setImageResource(myProdutoList.get(i).getItemImage());
         produtoViewHolder.mTitle.setText(myProdutoList.get(i).getItemName());
         produtoViewHolder.mDescription.setText(myProdutoList.get(i).getItemDescription());
         produtoViewHolder.mPrice.setText(myProdutoList.get(i).getItemPrice());
         produtoViewHolder.mMercado.setText(myProdutoList.get(i).getItemMercado());
         produtoViewHolder.mEndereco.setText(myProdutoList.get(i).getItemEndereco());


         produtoViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(mContext, DetalheActivity.class);
                 intent.putExtra("Image",myProdutoList.get(produtoViewHolder.getAdapterPosition()).getItemImage());
                 intent.putExtra("Description",myProdutoList.get(produtoViewHolder.getAdapterPosition()).getItemDescription());
                 intent.putExtra("Title",myProdutoList.get(produtoViewHolder.getAdapterPosition()).getItemName());
                 intent.putExtra("Price",myProdutoList.get(produtoViewHolder.getAdapterPosition()).getItemPrice());
                 intent.putExtra("Mercado",myProdutoList.get(produtoViewHolder.getAdapterPosition()).getItemMercado());
                 intent.putExtra("Endereco",myProdutoList.get(produtoViewHolder.getAdapterPosition()).getItemEndereco());
                 intent.putExtra("keyValue", myProdutoList.get(produtoViewHolder.getAdapterPosition()).getKey());

                 mContext.startActivity(intent);

             }
         });
    }

    @Override
    public int getItemCount() { return myProdutoList.size(); }

    public void filteredList(ArrayList<Produtos> filterList) {

        myProdutoList = filterList;
        notifyDataSetChanged();
    }
}

class ProdutoViewHolder extends RecyclerView.ViewHolder {


    ImageView imageView;
    TextView mTitle, mDescription, mPrice, mMercado, mEndereco;
    CardView mCardView;



    public ProdutoViewHolder(@NonNull View itemView) {
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