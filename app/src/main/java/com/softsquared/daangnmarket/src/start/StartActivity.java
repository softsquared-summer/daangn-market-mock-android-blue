package com.softsquared.daangnmarket.src.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;
import com.softsquared.daangnmarket.src.login.LoginActivity;

public class StartActivity extends BaseActivity {

    Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mStartButton = findViewById(R.id.btn_start);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
