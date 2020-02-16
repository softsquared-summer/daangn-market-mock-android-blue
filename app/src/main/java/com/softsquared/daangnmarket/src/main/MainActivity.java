package com.softsquared.daangnmarket.src.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;
import com.softsquared.daangnmarket.src.main.bottommenu.category.CategoryFragment;
import com.softsquared.daangnmarket.src.main.bottommenu.chat.ChatFragment;
import com.softsquared.daangnmarket.src.main.bottommenu.home.HomeFragment;
import com.softsquared.daangnmarket.src.main.bottommenu.my.MyFragment;
import com.softsquared.daangnmarket.src.main.bottommenu.write.WriteFragment;
import com.softsquared.daangnmarket.src.main.interfaces.MainActivityView;

public class MainActivity extends BaseActivity implements MainActivityView {

    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private HomeFragment mHomeFragment = new HomeFragment();
    private CategoryFragment mCategoryFragment = new CategoryFragment();
    private WriteFragment mWriteFragment = new WriteFragment();
    private ChatFragment mChatFragment = new ChatFragment();
    private MyFragment mMyFragment = new MyFragment();
    BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    transaction.replace(R.id.main_frame_layout, mWriteFragment).commitAllowingStateLoss();
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
}
