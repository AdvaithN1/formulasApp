package com.advaith.physicsformulas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.advaith.physicsformulas.R;
import com.advaith.physicsformulas.activities.MainActivity;

public class TitleFrag extends Fragment{
    View view;
    TextView tvTitle;
    Button btnClose;

    public TitleFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_title, container, false);

        tvTitle = view.findViewById(R.id.tvTitle);
        if(tvTitle == null){
            System.out.println("Text is null");
        }
        else{
            System.out.println("Text is not null");
        }
        tvTitle.setText(R.string.Kinematics);

        btnClose = view.findViewById(R.id.btnCloseDetail2);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
                ((MainActivity) getActivity()).setApplicationIndex();
            }
        });
        return view;
    }

    public void updateText(String text){
        tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(text);
    }

}