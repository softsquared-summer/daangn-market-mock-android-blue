package com.softsquared.daangnmarket.src.main.bottommenu.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.main.bottommenu.home.models.ResponseProduct;
import com.softsquared.daangnmarket.src.product.ProductActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {

    private ArrayList<ResponseProduct.Result> mData;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_product, iv_comment, iv_chat, iv_heart;
        TextView tv_name, tv_address_update, tv_price, tv_comment, tv_chat, tv_heart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_product = itemView.findViewById(R.id.iv_product);
            iv_comment = itemView.findViewById(R.id.iv_product_comment);
            iv_chat = itemView.findViewById(R.id.iv_product_chat);
            iv_heart = itemView.findViewById(R.id.iv_product_heart);

            tv_name = itemView.findViewById(R.id.tv_product_name);
            tv_address_update = itemView.findViewById(R.id.tv_product_address_and_update);
            tv_price = itemView.findViewById(R.id.tv_product_price);
            tv_comment = itemView.findViewById(R.id.tv_product_comment);
            tv_chat = itemView.findViewById(R.id.tv_product_chat);
            tv_heart = itemView.findViewById(R.id.tv_product_heart);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), ProductActivity.class);
                    intent.putExtra("product", mData.get(pos).getProductNo());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    ProductRecyclerViewAdapter(ArrayList<ResponseProduct.Result> list) {
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
    public void onBindViewHolder(@NonNull final ProductRecyclerViewAdapter.ViewHolder holder, int position) {
        final String productImage = mData.get(position).getImageUrl();
        String productName = mData.get(position).getTitle();
        String productAddress = mData.get(position).getAddress();
        int productUpdate = mData.get(position).getReroll();
        String productPrice = mData.get(position).getPrice();
        int productComment = mData.get(position).getComments();
        int productChat = mData.get(position).getChat();
        int productHeart = mData.get(position).getFavorite();
        Glide.with(holder.itemView.getContext()).load(productImage).into(holder.iv_product);

        String[] strArr = productAddress.split("\\s");

        holder.iv_product.setBackgroundResource(R.drawable.round_image_view);
        holder.iv_product.setClipToOutline(true);
        holder.tv_name.setText(productName);
        holder.tv_address_update.setText(strArr[strArr.length - 1] + " . " + productUpdate + "초 전");
        holder.tv_price.setText(productPrice + "원");

        if (productComment == 0) {
            holder.iv_comment.setVisibility(View.GONE);
            holder.tv_comment.setVisibility(View.GONE);
        } else {
            holder.tv_comment.setText(Integer.toString(productComment));
        }
        if (productChat == 0) {
            holder.iv_chat.setVisibility(View.GONE);
            holder.tv_chat.setVisibility(View.GONE);
        } else {
            holder.tv_chat.setText(Integer.toString(productChat));
        }
        if (productHeart == 0) {
            holder.iv_heart.setVisibility(View.GONE);
            holder.tv_heart.setVisibility(View.GONE);
        } else {
            holder.tv_heart.setText(Integer.toString(productHeart));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}
