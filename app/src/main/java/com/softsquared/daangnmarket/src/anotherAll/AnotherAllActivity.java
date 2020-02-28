package com.softsquared.daangnmarket.src.anotherAll;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;

public class AnotherAllActivity extends BaseActivity {

    Toolbar mToolbar;
    TabLayout mTabLayout;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_all);

        Intent intent = getIntent();
        int userNo = intent.getIntExtra("userNo", 0);

        mToolbar = findViewById(R.id.another_all_tb);
        mToolbar.setTitle(getString(R.string.another_all_toolbar_title));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTabLayout = findViewById(R.id.another_all_tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.tab_all)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.tab_selling)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.tab_sell_complete)));

        mViewPager = findViewById(R.id.another_all_view_pager);
        AnotherTabPagerAdapter anotherTabPagerAdapter = new AnotherTabPagerAdapter(getSupportFragmentManager(), 0, mTabLayout.getTabCount(), userNo);
        mViewPager.setAdapter(anotherTabPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
