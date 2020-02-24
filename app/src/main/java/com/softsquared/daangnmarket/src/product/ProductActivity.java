package com.softsquared.daangnmarket.src.product;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;
import com.softsquared.daangnmarket.src.main.bottommenu.home.models.ResponseProduct;
import com.softsquared.daangnmarket.src.product.interfaces.ProductActivityView;
import com.softsquared.daangnmarket.src.product.models.ResponseProductImage;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class ProductActivity extends BaseActivity implements ProductActivityView {

    Toolbar mToolbar;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    ResponseProduct.Result mProductItem;
    ArrayList<String> mImgList = new ArrayList<>();
    ViewPager mViewPager;
    ProductViewPagerAdapter mProductViewPagerAdapter;
    CircleIndicator mCircleIndicator;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = getIntent();
        mProductItem = (ResponseProduct.Result) intent.getSerializableExtra("product");
        mViewPager = findViewById(R.id.product_viewpager);
        mCircleIndicator = findViewById(R.id.product_view_pager_indicator);

        getProductImage();

        mToolbar = findViewById(R.id.tb_product);
        mCollapsingToolbarLayout = findViewById(R.id.product_collapsing_toolbar);
        //mCollapsingToolbarLayout.setTitle(mProductItem.getProductName());
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_actionbar_product, menu);
        return true;
    }

    @Override
    public void validateProductSuccess(boolean isSuccess, int code, String message, com.softsquared.daangnmarket.src.product.models.ResponseProduct.Result result) {
    }

    @Override
    public void validateProductFailure() {

    }

    @Override
    public void validateProductImageSuccess(boolean isSuccess, int code, String message, ArrayList<ResponseProductImage.Result> resultArrayList) {
        for (int i = 0; i < resultArrayList.size(); i++) {
            mImgList.add(resultArrayList.get(i).getImageUrl());
        }
        mProductViewPagerAdapter = new ProductViewPagerAdapter(this, mImgList) ;
        mViewPager.setAdapter(mProductViewPagerAdapter) ;
        mCircleIndicator.setViewPager(mViewPager);
    }

    @Override
    public void validateProductImageFailure() {

    }

    public void getProductImage() {
        ProductService productService = new ProductService(this);
        productService.getProductImage(mProductItem.getProductNo());
    }

    public void getProduct() {
        ProductService productService = new ProductService(this);
        productService.getProduct(mProductItem.getProductNo());
    }
}
