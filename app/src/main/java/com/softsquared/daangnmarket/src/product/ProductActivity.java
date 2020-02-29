package com.softsquared.daangnmarket.src.product;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;
import com.softsquared.daangnmarket.src.anotherAll.AnotherAllActivity;
import com.softsquared.daangnmarket.src.main.bottommenu.home.models.ResponseProduct;
import com.softsquared.daangnmarket.src.product.interfaces.ProductActivityView;
import com.softsquared.daangnmarket.src.product.models.ResponseProductAnother;
import com.softsquared.daangnmarket.src.product.models.ResponseProductImage;
import com.softsquared.daangnmarket.src.report.ReportActivity;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class ProductActivity extends BaseActivity implements ProductActivityView {

    Toolbar mToolbar;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    ArrayList<String> mImgList = new ArrayList<>();
    ViewPager mViewPager;
    ProductViewPagerAdapter mProductViewPagerAdapter;
    CircleIndicator mCircleIndicator;
    TextView mIdText, mAddressText, mMannerText, mTitleText, mCategoryAndRerollText, mTextText, mChatFavoriteHitsText, mPriceText, mAnotherUserId, mSeeAll;
    RecyclerView mAnotherProductRecyclerView;
    int mUserNo, mProductNo;
    String mUserID;
    RelativeLayout mReportLayout;
    ImageView profileImage;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = getIntent();
        mProductNo = intent.getIntExtra("product", 0);
        mViewPager = findViewById(R.id.product_viewpager);
        mCircleIndicator = findViewById(R.id.product_view_pager_indicator);

        mIdText = findViewById(R.id.product_tv_id);
        mAddressText = findViewById(R.id.product_tv_address);
        mMannerText = findViewById(R.id.product_tv_manner);
        mTitleText = findViewById(R.id.product_tv_title);
        mCategoryAndRerollText = findViewById(R.id.product_tv_category_and_reroll);
        mTextText = findViewById(R.id.product_tv_text);
        mChatFavoriteHitsText = findViewById(R.id.product_tv_chat_favorite_hits);
        mPriceText = findViewById(R.id.product_tv_price);
        mAnotherProductRecyclerView = findViewById(R.id.product_rv_another_product);
        mAnotherUserId = findViewById(R.id.product_tv_another_product_id);
        mSeeAll = findViewById(R.id.product_tv_see_all);
        mReportLayout = findViewById(R.id.product_report_layout);
        profileImage = findViewById(R.id.product_iv_user_profile);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mAnotherProductRecyclerView.setLayoutManager(gridLayoutManager);

        StaggeredGridLayoutManager mLayoutManager;
        mLayoutManager = new StaggeredGridLayoutManager(2, 1);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        mLayoutManager.setOrientation(StaggeredGridLayoutManager.VERTICAL);
        mAnotherProductRecyclerView.setLayoutManager(mLayoutManager);

        mAnotherProductRecyclerView.addItemDecoration(new ProductGridSpacingItemDecoration(2, 30, true));

        mToolbar = findViewById(R.id.tb_product);
        mCollapsingToolbarLayout = findViewById(R.id.product_collapsing_toolbar);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

        setSupportActionBar(mToolbar);

        showProgressDialog();
        getProductImage();
        getProduct();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ProductActivity.this, AnotherAllActivity.class);
                intent1.putExtra("userNo", mUserNo);
                startActivity(intent1);
            }
        });

        mReportLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ProductActivity.this, ReportActivity.class);
                intent1.putExtra("userNo", mUserNo);
                intent1.putExtra("userID", mUserID);
                intent1.putExtra("productNo", mProductNo);
                startActivity(intent1);
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
        mUserNo = result.getUserNo();
        mUserID = result.getId();
        mAnotherUserId.setText(result.getId() + getString(R.string.users_another_product));
        Glide.with(this).load(result.getProfileUrl()).into(profileImage);
        profileImage.setBackgroundResource(R.drawable.profile_image_view);
        profileImage.setClipToOutline(true);

        getAnotherProduct();
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

        if (resultArrayList.size() > 0) {
            mProductViewPagerAdapter = new ProductViewPagerAdapter(this, mImgList) ;
            mViewPager.setAdapter(mProductViewPagerAdapter) ;
            mCircleIndicator.setViewPager(mViewPager);
        }
        else {
            mViewPager.setVisibility(View.GONE);
        }
    }

    @Override
    public void validateProductImageFailure() {
        showCustomToast(getString(R.string.network_error));
    }

    @Override
    public void validateProductAnotherSuccess(boolean isSuccess, int code, String message, ArrayList<ResponseProductAnother.Result> resultArrayList) {
        if (isSuccess) {
            if (resultArrayList.size() <= 4) {
                ProductAnotherRecyclerViewAdapter productAnotherRecyclerViewAdapter = new ProductAnotherRecyclerViewAdapter(resultArrayList);
                mAnotherProductRecyclerView.setAdapter(productAnotherRecyclerViewAdapter);
            }
            else {
                ArrayList<ResponseProductAnother.Result> tempList = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    tempList.add(resultArrayList.get(i));
                }
                ProductAnotherRecyclerViewAdapter productAnotherRecyclerViewAdapter = new ProductAnotherRecyclerViewAdapter(tempList);
                mAnotherProductRecyclerView.setAdapter(productAnotherRecyclerViewAdapter);
            }
        }
        else {
            showCustomToast(message);
        }
        hideProgressDialog();
    }

    @Override
    public void validateProductAnotherFailure() {
        showCustomToast(getString(R.string.network_error));
    }

    public void getProductImage() {
        ProductService productService = new ProductService(this);
        productService.getProductImage(mProductNo);
    }

    public void getProduct() {
        ProductService productService = new ProductService(this);
        productService.getProduct(mProductNo);
    }

    public void getAnotherProduct() {
        ProductService productService = new ProductService(this);
        productService.getAnotherProduct(mUserNo, mProductNo, 1);
    }
}
