package com.softsquared.daangnmarket.src.main.bottommenu.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.daangnmarket.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    Toolbar mToolbar;
    ArrayList<ProductItem> mProductItems = new ArrayList<>();
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mToolbar = view.findViewById(R.id.tb_home);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setTitle(getString(R.string.actionbar_title_home));
        setHasOptionsMenu(true);

        ProductItem productItem1 = new ProductItem();
        productItem1.setProductImage(R.drawable.test1);
        productItem1.setProductName("에어팟 2\n무선충전풀박단순개봉품");
        productItem1.setProductAddress("송파구 잠실2동");
        productItem1.setProductUpdate("6");
        productItem1.setProductPrice("17,000");
        productItem1.setProductComment(1);
        productItem1.setProductChat(2);
        productItem1.setProductHeart(3);
        mProductItems.add(productItem1);

        ProductItem productItem2 = new ProductItem();
        productItem2.setProductImage(R.drawable.test1);
        productItem2.setProductName("스타벅스 제주녹차 기프티콘 2만원 권(다른 상품 구매 가능) 아크테릭스 고어텍스 여성 팬츠");
        productItem2.setProductAddress("송파구 잠실2동");
        productItem2.setProductUpdate("6");
        productItem2.setProductPrice("17,000");
        productItem2.setProductComment(1);
        productItem2.setProductChat(0);
        productItem2.setProductHeart(3);
        mProductItems.add(productItem2);

        ProductItem productItem3 = new ProductItem();
        productItem3.setProductImage(R.drawable.test1);
        productItem3.setProductName("스타벅스 제주녹차 기프티콘 2만원 권(다른 상품 구매 가능) 아크테릭스 고어텍스 여성 팬츠");
        productItem3.setProductAddress("송파구 잠실2동");
        productItem3.setProductUpdate("6");
        productItem3.setProductPrice("17,000");
        productItem3.setProductComment(1);
        productItem3.setProductChat(0);
        productItem3.setProductHeart(0);
        mProductItems.add(productItem3);

        mRecyclerView = view.findViewById(R.id.home_rv_product);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ProductRecyclerViewAdapter productRecyclerViewAdapter = new ProductRecyclerViewAdapter(mProductItems);
        mRecyclerView.setAdapter(productRecyclerViewAdapter);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_actionbar_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
