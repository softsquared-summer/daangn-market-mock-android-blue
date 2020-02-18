package com.softsquared.daangnmarket.src.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.main.bottommenu.home.ProductItem;

public class ProductActivity extends AppCompatActivity {

    Toolbar mToolbar;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    AppBarLayout mAppBarLayout;
    ProductItem mProductItem = new ProductItem();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = getIntent();
        mProductItem = (ProductItem) intent.getSerializableExtra("product");

        mToolbar = findViewById(R.id.tb_product);
        mCollapsingToolbarLayout = findViewById(R.id.product_collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle(mProductItem.getProductName());
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
}
