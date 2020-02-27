package com.softsquared.daangnmarket.src.main.bottommenu.category;

import com.softsquared.daangnmarket.src.main.bottommenu.category.interfaces.CategoryFragmentView;
import com.softsquared.daangnmarket.src.main.bottommenu.category.interfaces.CategoryRetrofitInterface;
import com.softsquared.daangnmarket.src.main.bottommenu.category.models.ResponseCategory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.daangnmarket.src.ApplicationClass.getRetrofit;

public class CategoryService {
    private final CategoryFragmentView mCategoryFragmentView;

    CategoryService(final CategoryFragmentView categoryFragmentView) {
        this.mCategoryFragmentView = categoryFragmentView;
    }

    void getCategory() {
        final CategoryRetrofitInterface categoryRetrofitInterface = getRetrofit().create(CategoryRetrofitInterface.class);
        categoryRetrofitInterface.getCategory().enqueue(new Callback<ResponseCategory>() {
            @Override
            public void onResponse(Call<ResponseCategory> call, Response<ResponseCategory> response) {
                final ResponseCategory responseCategory = response.body();

                mCategoryFragmentView.validateCategorySuccess(responseCategory.getIsSuccess(), responseCategory.getCode(), responseCategory.getMessage(), responseCategory.getResult());
            }

            @Override
            public void onFailure(Call<ResponseCategory> call, Throwable t) {
                mCategoryFragmentView.validateCategoryFailure();
            }
        });
    }
}
