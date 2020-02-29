package com.softsquared.daangnmarket.src.join;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;
import com.softsquared.daangnmarket.src.join.interfaces.JoinActivityView;
import com.softsquared.daangnmarket.src.join.models.RequestJoin;
import com.softsquared.daangnmarket.src.join.models.RequestLogin;
import com.softsquared.daangnmarket.src.join.models.ResponseLogin;
import com.softsquared.daangnmarket.src.main.MainActivity;

import java.util.UUID;

import static com.softsquared.daangnmarket.src.ApplicationClass.X_ACCESS_TOKEN;

public class JoinActivity extends BaseActivity implements JoinActivityView {

    Toolbar mToolbar;
    EditText mIdEdit;
    String mPhoneNumber;
    int mLocationNo;
    ImageView mProfileImageView;
    private static final int REQUEST_CODE = 1;
    Uri mUri = null;
    private StorageReference mStorageReference;
    private FirebaseStorage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mStorage = FirebaseStorage.getInstance();
        mStorageReference = mStorage.getReference();

        mToolbar = findViewById(R.id.join_toolbar);
        mToolbar.setTitle(getString(R.string.join_toolbar_title));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        mPhoneNumber = intent.getStringExtra("phonenumber");

        SharedPreferences sharedPreferences = getSharedPreferences("locationNo", MODE_PRIVATE);
        mLocationNo = sharedPreferences.getInt("locationNo", 0);
        System.out.println(mLocationNo);

        mIdEdit = findViewById(R.id.join_et_id);
        mProfileImageView = findViewById(R.id.join_iv_profile);

        Glide.with(this).load(getString(R.string.default_profile_url)).into(mProfileImageView);
        mProfileImageView.setBackgroundResource(R.drawable.profile_image_view);
        mProfileImageView.setClipToOutline(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    Uri uri = data.getData();
                    Glide.with(this).load(uri.toString()).into(mProfileImageView);
                    mUri = uri;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void validateJoinSuccess(boolean isSuccess, int code, String message) {
        if (isSuccess) {
            postLogin();
        }
        else {
            showCustomToast(message);
        }
    }

    @Override
    public void validateJoinFailure() {
        showCustomToast(getString(R.string.network_error));
    }

    @Override
    public void validateLoginSuccess(boolean isSuccess, int code, String message, ResponseLogin.Result result) {
        if (isSuccess) {
            SharedPreferences sharedPreferences = getSharedPreferences(X_ACCESS_TOKEN, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(X_ACCESS_TOKEN, result.getJwt());
            editor.commit();

            SharedPreferences sharedPreferences1 = getSharedPreferences("userNo", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sharedPreferences1.edit();
            editor1.putInt("userNo", result.getUserNo().get(0).getUserNo());
            editor1.commit();

            Intent intent = new Intent(JoinActivity.this, MainActivity.class);
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
        showCustomToast(getString(R.string.network_error));
    }

    public void join() {
        JoinService joinService = new JoinService(this);
        RequestJoin requestJoin = new RequestJoin();
        System.out.println(mPhoneNumber + " " + mIdEdit.getText().toString() + " " + mLocationNo);
        requestJoin.setPhoneNum(mPhoneNumber);
        requestJoin.setId(mIdEdit.getText().toString());
        requestJoin.setLocationNo(mLocationNo);
        joinService.postJoin(requestJoin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar_upload_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.upload_product_action_menu_done:
                join();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void postLogin() {
        JoinService joinService = new JoinService(this);
        RequestLogin requestLogin = new RequestLogin();
        requestLogin.setPhoneNum(mPhoneNumber);
        joinService.postLogin(requestLogin);
    }
}
