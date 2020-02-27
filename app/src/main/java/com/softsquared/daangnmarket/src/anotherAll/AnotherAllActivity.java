package com.softsquared.daangnmarket.src.anotherAll;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.BaseAdapter;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;
import com.softsquared.daangnmarket.src.anotherAll.interfaces.AnotherAllActivityView;

public class AnotherAllActivity extends BaseActivity implements AnotherAllActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_all);
    }
}
