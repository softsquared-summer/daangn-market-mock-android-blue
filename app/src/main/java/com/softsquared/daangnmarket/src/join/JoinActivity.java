package com.softsquared.daangnmarket.src.join;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;
import com.softsquared.daangnmarket.src.join.interfaces.JoinActivityView;
import com.softsquared.daangnmarket.src.join.models.RequestJoin;
import com.softsquared.daangnmarket.src.login.LoginService;
import com.softsquared.daangnmarket.src.login.models.RequestMessage;
import com.softsquared.daangnmarket.src.main.MainActivity;

public class JoinActivity extends BaseActivity implements JoinActivityView {

    EditText mIdEdit;
    Button mJoinButton;
    String mPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Intent intent = getIntent();
        mPhoneNumber = intent.getStringExtra("phonenumber");

        mIdEdit = findViewById(R.id.join_et_id);
        mJoinButton = findViewById(R.id.join_btn_join);
    }

    @Override
    public void validateJoinSuccess(boolean isSuccess, int code, String message) {
        if (isSuccess && code == 100) {
            showCustomToast(message);
            Intent intent = new Intent(JoinActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else {
            showCustomToast(message);
        }
    }

    @Override
    public void validateJoinFailure() {
        showCustomToast(getString(R.string.network_error));
    }

    public void join() {
        JoinService joinService = new JoinService(this);
        RequestJoin requestJoin = new RequestJoin();
        requestJoin.setPhoneNum(mPhoneNumber);
        requestJoin.setId(mIdEdit.getText().toString());
        joinService.postJoin(requestJoin);
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.join_btn_join:
                join();
                break;
        }
    }
}
