package com.softsquared.daangnmarket.src.anotherAll;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.softsquared.daangnmarket.src.anotherAll.tab.AnotherAllFragment;
import com.softsquared.daangnmarket.src.anotherAll.tab.AnotherCompleteFragment;
import com.softsquared.daangnmarket.src.anotherAll.tab.AnotherSellingFragment;

public class AnotherTabPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;
    private int mUserNo;

    public AnotherTabPagerAdapter(@NonNull FragmentManager fm, int behavior, int tabCount, int userNo) {
        super(fm, behavior);
        this.tabCount = tabCount;
        this.mUserNo = userNo;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       switch (position) {
           case 0 :
               AnotherAllFragment anotherAllFragment = new AnotherAllFragment(mUserNo);
               return anotherAllFragment;
           case 1:
               AnotherSellingFragment anotherSellingFragment = new AnotherSellingFragment(mUserNo);
               return anotherSellingFragment;
           case 2:
               AnotherCompleteFragment anotherCompleteFragment = new AnotherCompleteFragment(mUserNo);
               return anotherCompleteFragment;
           default:
               return null;
       }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
