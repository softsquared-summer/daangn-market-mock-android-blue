package com.softsquared.daangnmarket.src.main.bottommenu.chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;

import com.softsquared.daangnmarket.R;

public class ChatFragment extends Fragment {

    Toolbar mToolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        mToolbar = view.findViewById(R.id.tb_chat);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setTitle(getString(R.string.actionbar_title_chat));
        // Inflate the layout for this fragment
        return view;
    }
}
