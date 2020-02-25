package com.softsquared.daangnmarket.src.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;
import com.softsquared.daangnmarket.src.join.JoinActivity;
import com.softsquared.daangnmarket.src.login.interfaces.LoginActivityView;
import com.softsquared.daangnmarket.src.login.models.LoginResponse;
import com.softsquared.daangnmarket.src.login.models.RequestLogin;
import com.softsquared.daangnmarket.src.login.models.RequestMessage;
import com.softsquared.daangnmarket.src.login.models.RequestPhoneCert;
import com.softsquared.daangnmarket.src.main.MainActivity;

import static com.softsquared.daangnmarket.src.ApplicationClass.X_ACCESS_TOKEN;

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

        mPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length()>=12){
                    mGetCert.setBackgroundResource(R.drawable.dark_round_button);
                }else{
                    mGetCert.setBackgroundResource(R.drawable.light_round_button);
                }
            }
        });

        mCert.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length()>0){
                    mLogin.setBackgroundResource(R.drawable.orange_round_button);
                }else{
                    mLogin.setBackgroundResource(R.drawable.light_round_button);
                }
            }
        });

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
        else if (isSuccess == false && code == 200) {
            showCustomToast("잘못된 핸드폰번호 입니다");
        }
    }

    @Override
    public void validateMessageFailure() {
        showCustomToast(getString(R.string.network_error));
    }

    @Override
    public void validatePhoneCertSuccess(boolean isSuccess, int code, String message) {
        if (isSuccess) {
            String phoneNumber = mPhoneNumber.getText().toString();
            if (code == 100) {
                login();
            }
            else if (code == 101) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                intent.putExtra("phonenumber", phoneNumber);
                startActivity(intent);
                finish();
            }
        }
        else
            showCustomToast(message);
    }

    @Override
    public void validatePhoneCertFailure() {
        showCustomToast(getString(R.string.network_error));
    }

    @Override
    public void validateLoginSuccess(boolean isSuccess, int code, String message, LoginResponse.Result result) {
        if (isSuccess) {
            SharedPreferences sharedPreferences = getSharedPreferences(X_ACCESS_TOKEN, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(X_ACCESS_TOKEN, result.getJwt());
            editor.commit();

            SharedPreferences sharedPreferences1 = getSharedPreferences("userNo", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sharedPreferences1.edit();
            editor1.putInt("userNo", result.getUserNo().get(0).getUserNo());
            editor1.commit();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else {
            showCustomToast(message);
        }
    }

    @Override
    public void validateLoginFailure() {
        //showCustomToast(getString(R.string.network_error));
        showCustomToast(getString(R.string.network_error));
    }

    public void getMessage() {
        LoginService loginService = new LoginService(this);
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setPhoneNum(mPhoneNumber.getText().toString());
        loginService.postPhone(requestMessage);
    }

    public void phoneCert() {
        LoginService loginService = new LoginService(this);
        RequestPhoneCert requestPhoneCert = new RequestPhoneCert();
        requestPhoneCert.setPhoneNum(mPhoneNumber.getText().toString());
        requestPhoneCert.setCertNo(Integer.parseInt(mCert.getText().toString()));
        loginService.postCert(requestPhoneCert);
    }

    public void login() {
        LoginService loginService = new LoginService(this);
        RequestLogin requestLogin = new RequestLogin();
        requestLogin.setPhoneNum(mPhoneNumber.getText().toString());
        loginService.postLogin(requestLogin);
    }

    public void customOnClick (View view) {
        switch (view.getId()) {
            case R.id.login_btn_get_cert:
                mGetCert.setText(getString(R.string.getCertTextRetry));
                getMessage();
                break;
            case R.id.login_btn_start:
                phoneCert();
                break;
        }
    }
}
