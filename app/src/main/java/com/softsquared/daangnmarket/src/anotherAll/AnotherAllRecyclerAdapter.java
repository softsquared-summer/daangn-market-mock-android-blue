package com.softsquared.daangnmarket.src.anotherAll;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.anotherAll.models.ResponseProductAnother;
import com.softsquared.daangnmarket.src.main.bottommenu.home.ProductRecyclerViewAdapter;
import com.softsquared.daangnmarket.src.product.ProductActivity;

import java.util.ArrayList;

public class AnotherAllRecyclerAdapter extends RecyclerView.Adapter<AnotherAllRecyclerAdapter.ViewHolder> {

    private ArrayList<ResponseProductAnother.Result> mData;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvProduct, mIvComment, mIvChat, mIvHeart;
        TextView mTvName, mTvAddressUpdate, mTvPrice, mTvComment, mTvChat, mTvHeart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvProduct = itemView.findViewById(R.id.iv_product);
            mIvComment = itemView.findViewById(R.id.iv_product_comment);
            mIvChat = itemView.findViewById(R.id.iv_product_chat);
            mIvHeart = itemView.findViewById(R.id.iv_product_heart);

            mTvName = itemView.findViewById(R.id.tv_product_name);
            mTvAddressUpdate = itemView.findViewById(R.id.tv_product_address_and_update);
            mTvPrice = itemView.findViewById(R.id.tv_product_price);
            mTvComment = itemView.findViewById(R.id.tv_product_comment);
            mTvChat = itemView.findViewById(R.id.tv_product_chat);
            mTvHeart = itemView.findViewById(R.id.tv_product_heart);

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

    public AnotherAllRecyclerAdapter(ArrayList<ResponseProductAnother.Result> arrayList) {
        mData = arrayList;
    }

    @NonNull
    @Override
    public AnotherAllRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.product_recycler_view_item, parent, false);
        AnotherAllRecyclerAdapter.ViewHolder vh = new AnotherAllRecyclerAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AnotherAllRecyclerAdapter.ViewHolder holder, int position) {
        final String productImage = mData.get(position).getImageUrl();
        String productName = mData.get(position).getTitle();
        String productAddress = mData.get(position).getAddress();
        int productUpdate = mData.get(position).getReroll();
        String productPrice = mData.get(position).getPrice();
        int productComment = mData.get(position).getComment();
        int productChat = mData.get(position).getChat();
        int productHeart = mData.get(position).getFavorite();
        Glide.with(holder.itemView.getContext()).load(productImage).into(holder.mIvProduct);

        String[] strArr = productAddress.split("\\s");
        holder.mIvProduct.setBackgroundResource(R.drawable.round_image_view);
        holder.mIvProduct.setClipToOutline(true);
        holder.mTvName.setText(productName);
        holder.mTvAddressUpdate.setText(strArr[strArr.length - 1] + " . " + productUpdate + "초 전");
        holder.mTvPrice.setText(productPrice + "원");

        if (productComment == 0) {
            holder.mIvComment.setVisibility(View.GONE);
            holder.mTvComment.setVisibility(View.GONE);
        } else {
            holder.mTvComment.setText(Integer.toString(productComment));
        }
        if (productChat == 0) {
            holder.mIvChat.setVisibility(View.GONE);
            holder.mTvChat.setVisibility(View.GONE);
        } else {
            holder.mTvChat.setText(Integer.toString(productChat));
        }
        if (productHeart == 0) {
            holder.mIvHeart.setVisibility(View.GONE);
            holder.mTvHeart.setVisibility(View.GONE);
        } else {
            holder.mTvHeart.setText(Integer.toString(productHeart));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
