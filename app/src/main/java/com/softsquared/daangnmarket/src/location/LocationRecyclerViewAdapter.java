package com.softsquared.daangnmarket.src.location;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.location.models.ResponseAddress;
import com.softsquared.daangnmarket.src.main.MainActivity;
import com.softsquared.daangnmarket.src.main.bottommenu.home.ProductRecyclerViewAdapter;
import com.softsquared.daangnmarket.src.start.StartActivity;

import java.util.ArrayList;

public class LocationRecyclerViewAdapter extends RecyclerView.Adapter<LocationRecyclerViewAdapter.ViewHolder> {

    ArrayList<ResponseAddress.Result> mList;
    Activity mActivity;

    LocationRecyclerViewAdapter(ArrayList<ResponseAddress.Result> list, Activity activity) {
        mList = list;
        mActivity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.location_rv_text) ;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    intent.putExtra("address", mList.get(pos));
                    v.getContext().startActivity(intent);
                    mActivity.finish();
                }
            });
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
