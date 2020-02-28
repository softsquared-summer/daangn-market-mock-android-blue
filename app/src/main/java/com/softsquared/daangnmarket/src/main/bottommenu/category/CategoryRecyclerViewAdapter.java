package com.softsquared.daangnmarket.src.main.bottommenu.category;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.categoryProduct.CategoryProductActivity;
import com.softsquared.daangnmarket.src.main.bottommenu.category.models.ResponseCategory;
import com.softsquared.daangnmarket.src.product.ProductActivity;

import java.util.ArrayList;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {
    private ArrayList<ResponseCategory.Result> mData;

    CategoryRecyclerViewAdapter(ArrayList<ResponseCategory.Result> arrayList) {
        mData = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.category_recycler_view_item, parent, false) ;
        CategoryRecyclerViewAdapter.ViewHolder vh = new CategoryRecyclerViewAdapter.ViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(mData.get(position).getCategories());
        int temp = position + 1;
        int resId = holder.itemView.getResources().getIdentifier("category_icon" + temp, "drawable", "com.softsquared.daangnmarket");
        holder.imageView.setImageResource(resId);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.category_rv_item_iv);
            textView = itemView.findViewById(R.id.category_rv_item_tv);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), CategoryProductActivity.class);
                    intent.putExtra("category", mData.get(pos).getCategories());
                    intent.putExtra("categoryNo", mData.get(pos).getCategoriesNo());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
