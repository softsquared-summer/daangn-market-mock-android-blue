package com.softsquared.daangnmarket.src.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;
import com.softsquared.daangnmarket.src.login.interfaces.LoginActivityView;
import com.softsquared.daangnmarket.src.login.models.RequestMessage;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    Toolbar mToolbar;
    EditText mPhoneNumber, mCert;
    Button mGetCert, mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mToolbar = findViewById(R.id.login_toolbar);
        mPhoneNumber = findViewById(R.id.login_et_phone_number);
        mCert = findViewById(R.id.login_et_cert);
        mGetCert = findViewById(R.id.login_btn_get_cert);
        mLogin = findViewById(R.id.login_btn_start);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPhoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void validateMessageSuccess(boolean isSuccess, int code) {
        if (isSuccess == true && code == 100) {
            showCustomToast("인증번호를 보냈습니다");
        }
        else if (isSuccess == true && code == 200) {
            showCustomToast("잘못된 핸드폰번호 입니다");
        }
    }

    @Override
    public void validateMessageFailure(String message) {
        if (message == null) {
            showCustomToast(getString(R.string.network_error));
        }
    }

    public void getCert() {
        LoginService loginService = new LoginService(this);
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setPhoneNum(mPhoneNumber.getText().toString());
        loginService.postPhone(requestMessage);
    }

    public void customOnClick (View view) {
        switch (view.getId()) {
            case R.id.login_btn_get_cert:
                getCert();
        }
    }
}
