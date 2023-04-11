package com.advaith.physicsformulas.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.advaith.physicsformulas.ApplicationClass;
import com.advaith.physicsformulas.R;
import com.advaith.physicsformulas.activities.MainActivity;
import com.advaith.physicsformulas.activities.MoiActivity;

public class ListmoiFragment extends Fragment {

    ImageButton ivBackmoi;
    CardView formulas;
    View view;

    public ListmoiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_listmoi, container, false);
        ivBackmoi = view.findViewById(R.id.ivBackmoi);
        formulas = view.findViewById(R.id.cvFormulas);
        formulas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MoiActivity) getActivity()).onFormulasBtnClicked();
            }
        });
        ivBackmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                getActivity().finish();
            }
        });
        return view;


    }
}