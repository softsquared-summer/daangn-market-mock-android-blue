package com.softsquared.daangnmarket.src.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.softsquared.daangnmarket.R;

import java.util.ArrayList;

public class ProductViewPagerAdapter extends PagerAdapter {
    private Context mContext = null;
    private ArrayList<String> ImgList;

    public ProductViewPagerAdapter(Context context, ArrayList<String> list) {
        mContext = context;
        ImgList = list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null ;

        if (mContext != null) {
            // LayoutInflater를 통해 "/res/layout/page.xml"을 뷰로 생성.
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.product_image_view_pager_item, container, false);

            ImageView imageView = view.findViewById(R.id.iv_product_viewpager) ;
            Glide.with(mContext).load(ImgList.get(position)).into(imageView);
        }

        // 뷰페이저에 추가.
        container.addView(view) ;

        return view ;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 뷰페이저에서 삭제.
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return ImgList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }
}
