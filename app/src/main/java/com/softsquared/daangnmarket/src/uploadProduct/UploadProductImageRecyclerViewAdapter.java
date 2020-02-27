package com.softsquared.daangnmarket.src.uploadProduct;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.uploadProduct.interfaces.UploadProductActivityView;

import java.util.ArrayList;

public class UploadProductImageRecyclerViewAdapter extends RecyclerView.Adapter<UploadProductImageRecyclerViewAdapter.ViewHolder> {
    ArrayList<Uri> mUriList;
    UploadProductActivityView mUploadProductActivityView;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.product_upload_rv_image_rv_iv);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    mUploadProductActivityView.removeImage(pos);
                }
            });
        }
    }

    UploadProductImageRecyclerViewAdapter(ArrayList<Uri> uriArrayList, UploadProductActivityView uploadProductActivityView) {
        mUriList = uriArrayList;
        mUploadProductActivityView = uploadProductActivityView;
    }

    @NonNull
    @Override
    public UploadProductImageRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.upload_product_image_recycler_view_item, parent, false) ;
        UploadProductImageRecyclerViewAdapter.ViewHolder vh = new UploadProductImageRecyclerViewAdapter.ViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri uri = mUriList.get(position);

        holder.imageView.setImageURI(uri);
        holder.imageView.setBackgroundResource(R.drawable.round_image_view);
        holder.imageView.setClipToOutline(true);
    }

    @Override
    public int getItemCount() {
        return mUriList.size();
    }
}
