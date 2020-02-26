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
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.softsquared.daangnmarket.src.main.MainActivity;
import com.softsquared.daangnmarket.src.uploadProduct.interfaces.UploadProductActivityView;
import com.softsquared.daangnmarket.src.uploadProduct.models.RequestUploadProduct;
import com.softsquared.daangnmarket.src.uploadProduct.models.RequestUploadProductImage;
import com.softsquared.daangnmarket.src.uploadProduct.models.ResponseUploadProduct;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

import static com.softsquared.daangnmarket.src.ApplicationClass.X_ACCESS_TOKEN;

public class UploadProductActivity extends BaseActivity implements UploadProductActivityView {

    Toolbar mToolbar;
    EditText mEditTextTitle, mEditTextPrice, mEditTextText;
    Button mImageUploadButton;
    ArrayList<Uri> mUris = new ArrayList<>();
    private StorageReference mStorageReference;
    private FirebaseStorage mStorage;
    int PICK_IMAGE_MULTIPLE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product);

        mStorage = FirebaseStorage.getInstance();
        mStorageReference = mStorage.getReference();

        mEditTextTitle = findViewById(R.id.upload_product_et_title);
        mEditTextPrice = findViewById(R.id.upload_product_et_price);
        mEditTextText = findViewById(R.id.upload_product_et_text);
        mImageUploadButton = findViewById(R.id.upload_btn);

        mImageUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && data != null) {
                if (data.getData() != null) {
                    Uri uri = data.getData();
                    mUris.add(uri);
                }
                else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {
                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mUris.add(uri);
                        }
                    }
                }
            } else {
                showCustomToast("사진을 선택하지 않았습니다");
            }
        } catch (Exception e) {
            showCustomToast("오류");
        }

        super.onActivityResult(requestCode, resultCode, data);
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
                for (int i = 0; i < mUris.size(); i++) {
                    System.out.println(mUris.get(i));
                }
                postUploadProduct();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void validateUploadProductSuccess(boolean isSuccess, int code, String message, final ResponseUploadProduct.Result result) {
        if (isSuccess) {
            for (int i = 0; i < mUris.size(); i++) {
                StorageReference ref = mStorageReference.child("images/" + UUID.randomUUID().toString());
                final int finalI = i;
                ref.putFile(mUris.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                postUploadProductImage(uri, finalI + 1, result.getProductNo().get(0).getProductNo());
                            }
                        });
                    }
                });
            }
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
    public void validateUploadProductImageSuccess(boolean isSuccess, int code, String message, int idx) {
        if (isSuccess) {
            if (idx == mUris.size()) {
                showCustomToast("상품 등록");
                Intent intent = new Intent(UploadProductActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
        else {
            showCustomToast(message);
        }
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
        uploadProductService.postUploadProduct(requestUploadProduct);
    }

    public void postUploadProductImage(Uri downloadURI, int imgIdx, int productNo) {
        System.out.println(downloadURI + " " + imgIdx + " " + productNo);
        UploadProductService uploadProductService = new UploadProductService(this);
        RequestUploadProductImage requestUploadProductImage = new RequestUploadProductImage();
        requestUploadProductImage.setImageUrl(downloadURI.toString());
        requestUploadProductImage.setImageIndex(imgIdx);
        requestUploadProductImage.setProductNo(productNo);
        uploadProductService.postUploadProductImage(requestUploadProductImage, imgIdx);
    }
}
