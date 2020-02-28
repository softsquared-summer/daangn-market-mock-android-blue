package com.softsquared.daangnmarket.src.categoryProduct;

import com.softsquared.daangnmarket.src.categoryProduct.interfaces.CategoryProductActivityView;
import com.softsquared.daangnmarket.src.categoryProduct.interfaces.CategoryProductRetrofitInterface;
import com.softsquared.daangnmarket.src.categoryProduct.models.ResponseCategoryProduct;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.daangnmarket.src.ApplicationClass.getRetrofit;

public class CategoryProductService {
    private CategoryProductActivityView mCategoryProductActivityView;

    public CategoryProductService(CategoryProductActivityView categoryProductActivityView) {
        this.mCategoryProductActivityView = categoryProductActivityView;
    }

    public void getCategoryProduct(int categoriesNo, String address, int page) {
        final CategoryProductRetrofitInterface categoryProductRetrofitInterface = (CategoryProductRetrofitInterface) getRetrofit().create(CategoryProductRetrofitInterface.class);
        categoryProductRetrofitInterface.getCategoryProduct(categoriesNo, address, page).enqueue(new Callback<ResponseCategoryProduct>() {
            @Override
            public void onResponse(Call<ResponseCategoryProduct> call, Response<ResponseCategoryProduct> response) {
                final ResponseCategoryProduct responseCategoryProduct = response.body();
                mCategoryProductActivityView.validateCategoryProductSuccess(responseCategoryProduct.getIsSuccess(), responseCategoryProduct.getCode(), responseCategoryProduct.getMessage(), responseCategoryProduct.getResult());
            }

            @Override
            public void onFailure(Call<ResponseCategoryProduct> call, Throwable t) {
                mCategoryProductActivityView.validateCategoryProductFailure();
            }
        });
    }
}
