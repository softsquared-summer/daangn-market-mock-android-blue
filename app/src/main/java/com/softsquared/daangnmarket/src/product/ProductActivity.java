package com.softsquared.daangnmarket.src.product;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

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
    TextView mIdText, mAddressText, mMannerText, mTitleText, mCategoryAndRerollText, mTextText, mChatFavoriteHitsText, mPriceText;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        mIdText = findViewById(R.id.product_tv_id);
        mAddressText = findViewById(R.id.product_tv_address);
        mMannerText = findViewById(R.id.product_tv_manner);
        mTitleText = findViewById(R.id.product_tv_title);
        mCategoryAndRerollText = findViewById(R.id.product_tv_category_and_reroll);
        mTextText = findViewById(R.id.product_tv_text);
        mChatFavoriteHitsText = findViewById(R.id.product_tv_chat_favorite_hits);
        mPriceText = findViewById(R.id.product_tv_price);

        Intent intent = getIntent();
        mProductItem = (ResponseProduct.Result) intent.getSerializableExtra("product");
        mViewPager = findViewById(R.id.product_viewpager);
        mCircleIndicator = findViewById(R.id.product_view_pager_indicator);

        getProductImage();

        mToolbar = findViewById(R.id.tb_product);
        mCollapsingToolbarLayout = findViewById(R.id.product_collapsing_toolbar);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

        getProduct();

        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_actionbar_product, menu);
        return true;
    }

    @Override
    public void validateProductSuccess(boolean isSuccess, int code, String message, com.softsquared.daangnmarket.src.product.models.ResponseProduct.Result result) {
        mCollapsingToolbarLayout.setTitle(result.getTitle());
        mIdText.setText(result.getId());
        mAddressText.setText(result.getAddress());
        mMannerText.setText(Float.toString(result.getManner()) + "'C");
        mTitleText.setText(result.getTitle());
        mCategoryAndRerollText.setText(result.getCategories() + " . 끌올 " + result.getReroll() + " 분 전");
        mTextText.setText(result.getText());
        mChatFavoriteHitsText.setText("채팅 " + result.getChat() + "개 . 관심 " + result.getFavorite() + " . 조회 " + result.getHits());
        mPriceText.setText(result.getPrice() + "원");
    }

    @Override
    public void validateProductFailure() {
        showCustomToast(getString(R.string.network_error));
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
        showCustomToast(getString(R.string.network_error));
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
