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

public class MyAdapterCarne extends RecyclerView.Adapter<CarneViewHolder> {


    private Context mContext;
    private List<CategoriaCarne> myCarneList;

    public MyAdapterCarne(Context mContext, List<CategoriaCarne> myCarneList) {
        this.mContext = mContext;
        this.myCarneList = myCarneList;
    }

    @NonNull
    @Override
    public CarneViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row_item, viewGroup, false);

        return new CarneViewHolder(mView);

    }

    @Override
    public void onBindViewHolder(@NonNull CarneViewHolder feijaoViewHolder, int i) {


        Glide.with(mContext)
                .load(myCarneList.get(i).getItemImage())
                .into(feijaoViewHolder.imageView);

        //   produtoViewHolder.imageView.setImageResource(myProdutoList.get(i).getItemImage());
        feijaoViewHolder.mTitle.setText(myCarneList.get(i).getItemName());
        feijaoViewHolder.mDescription.setText(myCarneList.get(i).getItemDescription());
        feijaoViewHolder.mPrice.setText(myCarneList.get(i).getItemPrice());
        feijaoViewHolder.mMercado.setText(myCarneList.get(i).getItemMercado());
        feijaoViewHolder.mEndereco.setText(myCarneList.get(i).getItemEndereco());


        feijaoViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetalheCarneActivity.class);
                intent.putExtra("Image", myCarneList.get(feijaoViewHolder.getAdapterPosition()).getItemImage());
                intent.putExtra("Description", myCarneList.get(feijaoViewHolder.getAdapterPosition()).getItemDescription());
                intent.putExtra("Title", myCarneList.get(feijaoViewHolder.getAdapterPosition()).getItemName());
                intent.putExtra("Price", myCarneList.get(feijaoViewHolder.getAdapterPosition()).getItemPrice());
                intent.putExtra("Mercado", myCarneList.get(feijaoViewHolder.getAdapterPosition()).getItemMercado());
                intent.putExtra("Endereco", myCarneList.get(feijaoViewHolder.getAdapterPosition()).getItemEndereco());
                intent.putExtra("keyValue", myCarneList.get(feijaoViewHolder.getAdapterPosition()).getKey());

                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() { return myCarneList.size(); }

    public void filteredList(ArrayList<CategoriaCarne> filterList) {

        myCarneList = filterList;
        notifyDataSetChanged();
    }
}

class CarneViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle,mDescription, mPrice, mMercado, mEndereco;
    CardView mCardView;

    public CarneViewHolder(@NonNull View itemView) {
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
