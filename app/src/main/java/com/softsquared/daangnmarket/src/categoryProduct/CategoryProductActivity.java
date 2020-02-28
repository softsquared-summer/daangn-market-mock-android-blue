package com.softsquared.daangnmarket.src.categoryProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;
import com.softsquared.daangnmarket.src.categoryProduct.interfaces.CategoryProductActivityView;
import com.softsquared.daangnmarket.src.categoryProduct.models.ResponseCategoryProduct;

import java.util.ArrayList;

public class CategoryProductActivity extends BaseActivity implements CategoryProductActivityView {

    RecyclerView mRecyclerView;
    Toolbar mToolbar;
    private ArrayList<ResponseCategoryProduct.Result> mData = new ArrayList<>();
    int mPageNo = 1, mCategoryNo;
    String mAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_product);

        Intent intent = getIntent();
        mCategoryNo = intent.getIntExtra("categoryNo", 0);

        SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
        mAddress = sharedPreferences.getString("address", null);

        mToolbar = findViewById(R.id.category_product_toolbar);
        mToolbar.setTitle(intent.getStringExtra("category"));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRecyclerView = findViewById(R.id.category_product_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        showProgressDialog();
        getCategoryProduct(mPageNo);
    }

    @Override
    public void validateCategoryProductSuccess(boolean isSuccess, int code, String message, ArrayList<ResponseCategoryProduct.Result> resultArrayList) {
        if (isSuccess) {
            if (resultArrayList.size() > 0) {
                for (int i = 0; i < resultArrayList.size(); i++) {
                    mData.add(resultArrayList.get(i));
                }
                mPageNo++;
                getCategoryProduct(mPageNo);
            }
            else {
                CategoryProductRecyclerViewAdapter categoryProductRecyclerViewAdapter = new CategoryProductRecyclerViewAdapter(mData);
                mRecyclerView.setAdapter(categoryProductRecyclerViewAdapter);
                hideProgressDialog();
            }
        }
        else {

        }
    }

    @Override
    public void validateCategoryProductFailure() {
        showCustomToast(getString(R.string.network_error));
    }

    public void getCategoryProduct(int pageNo) {
        CategoryProductService categoryProductService = new CategoryProductService(this);
        categoryProductService.getCategoryProduct(mCategoryNo, mAddress, pageNo);
    }
}
