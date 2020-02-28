package com.softsquared.daangnmarket.src.location;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.location.interfaces.LocationRecyclerViewAdapterView;
import com.softsquared.daangnmarket.src.location.models.RequestLocationReset;
import com.softsquared.daangnmarket.src.location.models.ResponseAddress;
import com.softsquared.daangnmarket.src.main.MainActivity;

import java.util.ArrayList;

import static com.softsquared.daangnmarket.src.ApplicationClass.X_ACCESS_TOKEN;

public class LocationRecyclerViewAdapter extends RecyclerView.Adapter<LocationRecyclerViewAdapter.ViewHolder> implements LocationRecyclerViewAdapterView {

    ArrayList<ResponseAddress.Result> mList;
    Activity mActivity;

    LocationRecyclerViewAdapter(ArrayList<ResponseAddress.Result> list, Activity activity) {
        mList = list;
        mActivity = activity;
    }

    @Override
    public void validateLocationResetSuccess(boolean isSuccess, int code, String message) {

    }

    @Override
    public void validateLocationResetFailure() {

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
                    SharedPreferences sharedPreferences1 = v.getContext().getSharedPreferences(X_ACCESS_TOKEN, Context.MODE_PRIVATE);
                    String jwt = sharedPreferences1.getString(X_ACCESS_TOKEN, null);
                    int pos = getAdapterPosition();
                    if (jwt == null) {
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        intent.putExtra("address", mList.get(pos));
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("address", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("address", mList.get(pos).getAddress());
                        editor.commit();
                        v.getContext().startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("address", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("address", mList.get(pos).getAddress());
                        editor.commit();
                        locationReset(pos);
                        v.getContext().startActivity(intent);
                    }
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

    public void locationReset(int position) {
        LocationRecyclerViewService locationRecyclerViewService = new LocationRecyclerViewService(this);
        RequestLocationReset requestLocationReset = new RequestLocationReset();
        requestLocationReset.setLocationNo(mList.get(position).getLocationNo());
        locationRecyclerViewService.patchLocationReset(requestLocationReset);
    }

}
