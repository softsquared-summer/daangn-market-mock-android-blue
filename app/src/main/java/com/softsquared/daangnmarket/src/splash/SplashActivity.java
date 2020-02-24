package com.softsquared.daangnmarket.src.splash;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;
import com.softsquared.daangnmarket.src.location.LocationActivity;
import com.softsquared.daangnmarket.src.main.MainActivity;
import com.softsquared.daangnmarket.src.start.StartActivity;

import static com.softsquared.daangnmarket.src.ApplicationClass.X_ACCESS_TOKEN;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new splashHandler(), 1000);
    }

    private class splashHandler implements Runnable{
        public void run(){
            SharedPreferences sharedPreferences = getSharedPreferences(X_ACCESS_TOKEN, MODE_PRIVATE);
            String jwt = sharedPreferences.getString(X_ACCESS_TOKEN, null);

            if (jwt == null) {
                startActivity(new Intent(getApplication(), StartActivity.class));
                SplashActivity.this.finish();
            }
            else {
                System.out.println("jwt = " + jwt);
                startActivity(new Intent(getApplication(), MainActivity.class));
                SplashActivity.this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함
    }
}
