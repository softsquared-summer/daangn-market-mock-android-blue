package com.softsquared.daangnmarket.src.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;
import com.softsquared.daangnmarket.src.login.LoginActivity;
import com.softsquared.daangnmarket.src.main.bottommenu.category.CategoryFragment;
import com.softsquared.daangnmarket.src.main.bottommenu.chat.ChatFragment;
import com.softsquared.daangnmarket.src.main.bottommenu.home.HomeFragment;
import com.softsquared.daangnmarket.src.main.bottommenu.my.MyFragment;
import com.softsquared.daangnmarket.src.main.interfaces.MainActivityView;
import com.softsquared.daangnmarket.src.uploadProduct.UploadProductActivity;

import static com.softsquared.daangnmarket.src.ApplicationClass.X_ACCESS_TOKEN;

public class MainActivity extends BaseActivity implements MainActivityView {

    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private HomeFragment mHomeFragment = new HomeFragment();
    private CategoryFragment mCategoryFragment = new CategoryFragment();
    private ChatFragment mChatFragment = new ChatFragment();
    private MyFragment mMyFragment = new MyFragment();
    BottomSheetDialog mBottomSheetDialog;
    BottomNavigationView mBottomNavigationView;
    LinearLayout mSecondhandTradeLayout, mCommunityPromotionLayout;
    String mJwt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(X_ACCESS_TOKEN, MODE_PRIVATE);
        mJwt = sharedPreferences.getString(X_ACCESS_TOKEN, null);

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.main_frame_layout, mHomeFragment).commitAllowingStateLoss();

        mBottomNavigationView = findViewById(R.id.main_btm_nav);
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.menu_item1:
                    transaction.replace(R.id.main_frame_layout, mHomeFragment).commitAllowingStateLoss();
                    break;
                case R.id.menu_item2:
                    transaction.replace(R.id.main_frame_layout, mCategoryFragment).commitAllowingStateLoss();
                    break;
                case R.id.menu_item3:
                    if (mJwt == null) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage(getString(R.string.login_dialog));
                        builder.setPositiveButton("취소",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        builder.setNegativeButton("로그인/가입",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                });
                        builder.show();
                    }
                    else {
                        mBottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                        mBottomSheetDialog.setContentView(R.layout.custom_dialog_click_write);
                        mSecondhandTradeLayout = mBottomSheetDialog.findViewById(R.id.custom_dialog_click_write_secondhand_trade_layout);
                        mCommunityPromotionLayout = mBottomSheetDialog.findViewById(R.id.custom_dialog_click_write_community_promotion_layout);
                        mSecondhandTradeLayout.setOnClickListener(secondhandTradeListener);
                        mCommunityPromotionLayout.setOnClickListener(communityPromotionListener);
                        mBottomSheetDialog.show();
                    }
                    break;
                case R.id.menu_item4:
                    transaction.replace(R.id.main_frame_layout, mChatFragment).commitAllowingStateLoss();
                    break;
                case R.id.menu_item5:
                    transaction.replace(R.id.main_frame_layout, mMyFragment).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }

    private void tryGetTest() {
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.getTest();
    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    public void customOnClick(View view) {
    }

    private View.OnClickListener secondhandTradeListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, UploadProductActivity.class);
            startActivity(intent);
            mBottomSheetDialog.dismiss();
        }
    };

    private View.OnClickListener communityPromotionListener = new View.OnClickListener() {
        public void onClick(View v) {
            mBottomSheetDialog.dismiss();
        }
    };
}
