package com.softsquared.daangnmarket.src.main.bottommenu.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.daangnmarket.R;

import java.util.ArrayList;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {

    private ArrayList<ProductItem> mData = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_product);
            textView = itemView.findViewById(R.id.tv_product_name);
        }
    }

    ProductRecyclerViewAdapter(ArrayList<ProductItem> list) {
        mData = list;
    }

    @NonNull
    @Override
    public ProductRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.product_recycler_view_item, parent, false) ;
        ProductRecyclerViewAdapter.ViewHolder vh = new ProductRecyclerViewAdapter.ViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerViewAdapter.ViewHolder holder, int position) {
        int productImage = mData.get(position).getProductImage();
        String productName = mData.get(position).getProductName();
        System.out.println(productImage + " " + productName);
        holder.imageView.setImageResource(productImage);
        holder.textView.setText(productName);
    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}
