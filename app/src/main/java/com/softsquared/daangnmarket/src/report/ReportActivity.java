package com.softsquared.daangnmarket.src.report;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.BaseActivity;
import com.softsquared.daangnmarket.src.report.interfaces.ReportActivityView;
import com.softsquared.daangnmarket.src.report.models.RequestProductReport;
import com.softsquared.daangnmarket.src.report.models.RequestUserReport;

public class ReportActivity extends BaseActivity implements ReportActivityView {

    RelativeLayout reportUser, reportProduct;
    TextView reportUserText;
    int mUserNo, mProductNo;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Intent intent = getIntent();
        mUserNo = intent.getIntExtra("userNo", 0);
        mProductNo = intent.getIntExtra("productNo", 0);

        reportUser = findViewById(R.id.report_user);
        reportProduct = findViewById(R.id.report_product);
        reportUserText = findViewById(R.id.report_tv_user);
        mToolbar = findViewById(R.id.report_tb);
        mToolbar.setTitle(getString(R.string.report_toolbar_title));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        reportUserText.setText("'" + intent.getStringExtra("userID") + "'" + getString(R.string.user_report_string));

        reportUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportUser();
            }
        });
        reportProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportProduct();
            }
        });
    }

    void reportUser() {
        ReportService reportService = new ReportService(this);
        RequestUserReport requestUserReport = new RequestUserReport();
        requestUserReport.setReportuserNo(mUserNo);
        reportService.postReportUser(requestUserReport);
    }
    void reportProduct() {
        ReportService reportService = new ReportService(this);
        RequestProductReport requestProductReport = new RequestProductReport();
        requestProductReport.setProductNo(mProductNo);
        reportService.postReportProduct(requestProductReport);
    }

    @Override
    public void validateReportSuccess(boolean isSuccess, int code, String message) {
        if (isSuccess) {
            showCustomToast(message);
            finish();
        }
        else {
            showCustomToast(message);
        }
    }

    @Override
    public void validateReportFailure() {
        showCustomToast(getString(R.string.network_error));
    }
}
