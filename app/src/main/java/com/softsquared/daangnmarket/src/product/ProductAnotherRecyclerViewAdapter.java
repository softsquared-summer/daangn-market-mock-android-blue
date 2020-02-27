package com.softsquared.daangnmarket.src.product;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.main.bottommenu.home.ProductRecyclerViewAdapter;
import com.softsquared.daangnmarket.src.product.models.ResponseProductAnother;

import java.util.ArrayList;

public class ProductAnotherRecyclerViewAdapter extends RecyclerView.Adapter<ProductAnotherRecyclerViewAdapter.ViewHolder> {

    private ArrayList<ResponseProductAnother.Result> mData;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.product_another_grid_layout_item, parent, false) ;
        ProductAnotherRecyclerViewAdapter.ViewHolder vh = new ProductAnotherRecyclerViewAdapter.ViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String imageURL = mData.get(position).getImageUrl();
        String title = mData.get(position).getTitle();
        String price = mData.get(position).getPrice();
        Glide.with(holder.itemView.getContext()).load(imageURL).into(holder.mAnotherImage);
        holder.mAnotherImage.setBackgroundResource(R.drawable.round_image_view);
        holder.mAnotherImage.setClipToOutline(true);
        holder.mTitleText.setText(title);
        holder.mPriceText.setText(price + "원");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mAnotherImage;
        TextView mTitleText, mPriceText;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            mAnotherImage = itemView.findViewById(R.id.product_another_item_iv_image);
            mTitleText = itemView.findViewById(R.id.product_another_item_tv_title);
            mPriceText = itemView.findViewById(R.id.product_another_item_tv_price);

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

    ProductAnotherRecyclerViewAdapter (ArrayList<ResponseProductAnother.Result> arrayList) {
        mData = arrayList;
    }
}
