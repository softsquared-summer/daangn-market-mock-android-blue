package com.softsquared.daangnmarket.src.location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.location.models.ResponseAddress;
import com.softsquared.daangnmarket.src.main.bottommenu.home.ProductRecyclerViewAdapter;

import java.util.ArrayList;

public class LocationRecyclerViewAdapter extends RecyclerView.Adapter<LocationRecyclerViewAdapter.ViewHolder> {

    ArrayList<ResponseAddress.Result> mList;

    LocationRecyclerViewAdapter(ArrayList<ResponseAddress.Result> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getAddress());
        }
        mList = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.location_rv_text) ;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.location_recycler_view_item, parent, false) ;
        LocationRecyclerViewAdapter.ViewHolder vh = new LocationRecyclerViewAdapter.ViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String str = mList.get(position).getAddress();
        holder.textView1.setText(str);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
