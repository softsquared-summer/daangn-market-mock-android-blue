package com.softsquared.daangnmarket.src.appSetting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.start.StartActivity;

import static com.softsquared.daangnmarket.src.ApplicationClass.X_ACCESS_TOKEN;

public class AppSettingActivity extends AppCompatActivity {

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_setting);

        mToolbar = findViewById(R.id.app_setting_toolbar);
        mToolbar.setTitle(getString(R.string.app_setting_toolbar_title));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void logout() {
        System.out.println("여기");
        SharedPreferences sharedPreferences = getSharedPreferences(X_ACCESS_TOKEN, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(AppSettingActivity.this, StartActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void customOnClick(View view) {
        switch(view.getId()) {
            case R.id.app_setting_logout:
                logout();
        }
    }
}
