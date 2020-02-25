package com.softsquared.daangnmarket.src.uploadProduct;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;
import com.softsquared.daangnmarket.src.uploadProduct.interfaces.UploadProductActivityView;
import com.softsquared.daangnmarket.src.uploadProduct.models.RequestUploadProduct;
import com.softsquared.daangnmarket.src.uploadProduct.models.RequestUploadProductImage;
import com.softsquared.daangnmarket.src.uploadProduct.models.ResponseUploadProduct;

import java.util.UUID;

import static com.softsquared.daangnmarket.src.ApplicationClass.X_ACCESS_TOKEN;

public class UploadProductActivity extends BaseActivity implements UploadProductActivityView {

    Toolbar mToolbar;
    EditText mEditTextTitle, mEditTextPrice, mEditTextText;
    Button mImageUploadButton;
    ClipData mClipData;
    private StorageReference mStorageReference;
    private FirebaseApp app;
    private FirebaseStorage mStorage;
    String mJwt;
    Uri mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product);

        SharedPreferences sharedPreferences = getSharedPreferences(X_ACCESS_TOKEN, MODE_PRIVATE);
        mJwt = sharedPreferences.getString(X_ACCESS_TOKEN, null);


        mStorage = FirebaseStorage.getInstance();
        mStorageReference = mStorage.getReference();

        mEditTextTitle = findViewById(R.id.upload_product_et_title);
        mEditTextPrice = findViewById(R.id.upload_product_et_price);
        mEditTextText = findViewById(R.id.upload_product_et_text);
        mImageUploadButton = findViewById(R.id.upload_btn);

        mImageUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(UploadProductActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(UploadProductActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                    return;
                }

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });


        mToolbar = findViewById(R.id.upload_product_toolbar);
        mToolbar.setTitle(getString(R.string.upload_product_toolbar_title));
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar_upload_product, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.upload_product_action_menu_done:
                postUploadProduct();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data.getClipData() != null)
                mClipData = data.getClipData();
            else
                mData = data.getData();
        }
    }

    @Override
    public void validateUploadProductSuccess(boolean isSuccess, int code, String message, final ResponseUploadProduct.Result result) {
        if (isSuccess) {
            if (mClipData.getItemCount() > 0) {
                for (int i = 0; i < mClipData.getItemCount(); i++) {
                    Uri uri = mClipData.getItemAt(i).getUri();
                    StorageReference ref = mStorageReference.child("images/" + UUID.randomUUID().toString());
                    final int finalI = i;
                    ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    postUploadProductImage(uri, finalI, result.getProductNo().get(0).getProductNo());
                                }
                            });
                        }
                    });
                }
            }
            else {
                StorageReference ref = mStorageReference.child("images/" + UUID.randomUUID().toString());
                ref.putFile(mData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                postUploadProductImage(uri, 1, result.getProductNo().get(0).getProductNo());
                            }
                        });
                    }
                });
            }
            finish();
        }
        else {
            showCustomToast(message);
        }
    }

    @Override
    public void validateUploadProductFailure() {
        showCustomToast(getString(R.string.network_error));
    }

    @Override
    public void validateUploadProductImageSuccess(boolean isSuccess, int code, String message) {
        showCustomToast("중고물품등록");
    }

    @Override
    public void validateUploadProductImageFailure() {
        showCustomToast(getString(R.string.network_error));
    }

    public void postUploadProduct() {
        UploadProductService uploadProductService = new UploadProductService(this);
        RequestUploadProduct requestUploadProduct = new RequestUploadProduct();
        requestUploadProduct.setTitle(mEditTextTitle.getText().toString());
        requestUploadProduct.setCategoriesNo("1");
        requestUploadProduct.setPrice(mEditTextPrice.getText().toString());
        requestUploadProduct.setText(mEditTextText.getText().toString());
        uploadProductService.postUploadProduct(mJwt, requestUploadProduct);
    }

    public void postUploadProductImage(Uri downloadURI, int imgIdx, int productNo) {
        UploadProductService uploadProductService = new UploadProductService(this);
        System.out.println(downloadURI.toString() + " " + imgIdx + 1 + " " + productNo);
        RequestUploadProductImage requestUploadProductImage = new RequestUploadProductImage();
        requestUploadProductImage.setImageUrl(downloadURI.toString());
        requestUploadProductImage.setImageIndex(imgIdx + 1);
        requestUploadProductImage.setProductNo(productNo);
        uploadProductService.postUploadProductImage(requestUploadProductImage);
    }
}
