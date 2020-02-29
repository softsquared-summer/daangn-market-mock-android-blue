package com.softsquared.daangnmarket.src.profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;
import com.softsquared.daangnmarket.src.profile.interfaces.ProfileActivityView;
import com.softsquared.daangnmarket.src.profile.models.RequestChangeProfile;

import java.util.UUID;

public class ProfileActivity extends BaseActivity implements ProfileActivityView {

    Toolbar mToolbar;
    ImageView mProfileImage;
    TextView mIdText;
    String mURL, mID;
    private final int GET_GALLERY_IMAGE = 200;
    private StorageReference mStorageReference;
    private FirebaseStorage mStorage;
    Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_avtivity);

        mStorage = FirebaseStorage.getInstance();
        mStorageReference = mStorage.getReference();

        Intent intent = getIntent();
        mURL = intent.getStringExtra("url");
        mID = intent.getStringExtra("id");

        mToolbar = findViewById(R.id.profile_toolbar);
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

        mProfileImage = findViewById(R.id.profile_iv);
        mIdText = findViewById(R.id.profile_Id);
        Glide.with(getApplicationContext()).load(mURL).into(mProfileImage);
        mProfileImage.setBackgroundResource(R.drawable.profile_image_view);
        mProfileImage.setClipToOutline(true);
        mIdText.setText(mID);

        mProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
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
                done();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            mProfileImage.setImageURI(selectedImageUri);
            mProfileImage.setBackgroundResource(R.drawable.profile_image_view);
            mProfileImage.setClipToOutline(true);
            mUri = selectedImageUri;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    void changeProfile(String url) {
        ProfileService profileService = new ProfileService(this);
        RequestChangeProfile requestChangeProfile = new RequestChangeProfile();
        requestChangeProfile.setProfileUrl(url);
        profileService.patchProfile(requestChangeProfile);
    }

    void done() {
        StorageReference ref = mStorageReference.child("images/" + UUID.randomUUID().toString());
        ref.putFile(mUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        changeProfile(uri.toString());
                    }
                });
            }
        });
    }

    @Override
    public void validateChangeProfileSuccess(boolean isSuccess, int code, String message) {
        if (isSuccess) {
            finish();
        }
        else {
            showCustomToast(message);
        }
    }

    @Override
    public void validateChangeProfileFailure() {
        showCustomToast(getString(R.string.network_error));
    }
}
