package com.softsquared.daangnmarket.src.main.bottommenu.home;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.softsquared.daangnmarket.R;

public class HomeCustomDialog extends Dialog {
    private Button mPositiveButton, mNegativeButton;
    private TextView mTextView;
    private String mAddress, mStr1, mStr2;

    private View.OnClickListener mPositiveListener;
    private View.OnClickListener mNegativeListener;

    //생성자 생성
    public HomeCustomDialog(@NonNull Context context, View.OnClickListener positiveListener, View.OnClickListener negativeListener, String address, String str1, String str2) {
        super(context);
        this.mPositiveListener = positiveListener;
        this.mNegativeListener = negativeListener;
        this.mAddress = address;
        this.mStr1 = str1;
        this.mStr2 = str2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //다이얼로그 밖의 화면은 흐리게 만들어줌
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.custom_dialog_nonlogin);

        //셋팅
        mPositiveButton=findViewById(R.id.popup_btn_login);
        mNegativeButton=findViewById(R.id.popup_btn_negative);
        mTextView = findViewById(R.id.popup_tv);

        mTextView.setText(mStr1 + " " + mAddress + mStr2);

        //클릭 리스너 셋팅 (클릭버튼이 동작하도록 만들어줌.)
        mPositiveButton.setOnClickListener(mPositiveListener);
        mNegativeButton.setOnClickListener(mNegativeListener);
    }
}
