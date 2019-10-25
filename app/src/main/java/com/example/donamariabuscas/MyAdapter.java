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

import java.util.List;

public class MyAdapter  extends  RecyclerView.Adapter<ProdutoViewHolder> {

    private Context mContext;
    private List<Produtos> myProdutoList;

    public MyAdapter(Context mContext, List<Produtos> myProdutoList) {
        this.mContext = mContext;
        this.myProdutoList = myProdutoList;
    }

    @NonNull
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

//         produtoViewHolder.imageView.setImageResource(myProdutoList.get(i).getItemImage());
         produtoViewHolder.mTitle.setText(myProdutoList.get(i).getItemNome());
         produtoViewHolder.mDescricao.setText(myProdutoList.get(i).getItemDescricao());
         produtoViewHolder.mPreco.setText(myProdutoList.get(i).getItemPreco());

         produtoViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(mContext, DetalheActivity.class);
                 intent.putExtra("Image",myProdutoList.get(produtoViewHolder.getAdapterPosition()).getItemImage());
                 intent.putExtra("Descricao",myProdutoList.get(produtoViewHolder.getAdapterPosition()).getItemDescricao());
                 mContext.startActivity(intent);

             }
         });
    }

    @Override
    public int getItemCount() { return myProdutoList.size(); }
}

class ProdutoViewHolder extends RecyclerView.ViewHolder {


    ImageView imageView;
    TextView mTitle, mDescricao, mPreco;
    CardView mCardView;



    public ProdutoViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mDescricao = itemView.findViewById(R.id.tvDescricao);
        mPreco = itemView.findViewById(R.id.tvPreco);


        mCardView = itemView.findViewById(R.id.myCardView);

    }
}