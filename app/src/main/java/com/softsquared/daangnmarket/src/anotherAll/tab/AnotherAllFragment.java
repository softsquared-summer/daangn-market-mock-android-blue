package com.softsquared.daangnmarket.src.anotherAll.tab;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.softsquared.daangnmarket.R;
import com.softsquared.daangnmarket.src.anotherAll.AnotherAllRecyclerAdapter;
import com.softsquared.daangnmarket.src.anotherAll.AnotherAllService;
import com.softsquared.daangnmarket.src.anotherAll.interfaces.AnotherAllActivityView;
import com.softsquared.daangnmarket.src.anotherAll.models.ResponseProductAnother;

import java.util.ArrayList;

public class AnotherAllFragment extends Fragment implements AnotherAllActivityView {

    RecyclerView mRecyclerView;
    int mUserNo, mPageNo;
    public ProgressDialog mProgressDialog;
    ArrayList<ResponseProductAnother.Result> mResultList = new ArrayList<>();

    public AnotherAllFragment(int userNo) {
        mUserNo = userNo;
        mPageNo = 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_another_all, container, false);

        mRecyclerView = view.findViewById(R.id.another_all_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        showProgressDialog();
        getAnotherProduct(mUserNo, mPageNo);

        return view;
    }

    @Override
    public void validateProductAnotherSuccess(boolean isSuccess, int code, String message, ArrayList<ResponseProductAnother.Result> resultArrayList) {
        if (isSuccess) {
            if (resultArrayList.size() > 0) {
                for (int i = 0; i < resultArrayList.size(); i++) {
                    mResultList.add(resultArrayList.get(i));
                }
                mPageNo++;
                getAnotherProduct(mUserNo, mPageNo);
            }
        }
        else {
            if (mResultList.size() > 0) {
                AnotherAllRecyclerAdapter anotherAllRecyclerAdapter = new AnotherAllRecyclerAdapter(mResultList);
                mRecyclerView.setAdapter(anotherAllRecyclerAdapter);
            }
            hideProgressDialog();
        }
    }

    @Override
    public void validateProductAnotherFailure() {
        Toast.makeText(getContext(), getString(R.string.network_error), Toast.LENGTH_LONG).show();
    }

    public void getAnotherProduct(int userNo, int pageNo) {
        AnotherAllService anotherAllService = new AnotherAllService(this);
        anotherAllService.getAnotherProductAll(userNo, 0, pageNo);
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCanceledOnTouchOutside(false);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
