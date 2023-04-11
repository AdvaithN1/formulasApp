package com.advaith.physicsformulas.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.advaith.physicsformulas.ApplicationClass;
import com.advaith.physicsformulas.R;
import com.advaith.physicsformulas.activities.MainActivity;
import com.advaith.physicsformulas.adapters.FormulaAdapter;


public class CalcCommonFragment extends Fragment {

    TextView tvLimits;
    TextView tvDerivatives;
    TextView tvIntegrals;
    TextView title;
    Button btnClose;

    RecyclerView rvCommons;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;

    public CalcCommonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calc_common, container, false);
        title = v.findViewById(R.id.tvCommonsTitle);
        btnClose = v.findViewById(R.id.btnCloseCommons);
        tvLimits = v.findViewById(R.id.tvLimits);
        tvDerivatives = v.findViewById(R.id.tvDerivs);
        tvIntegrals = v.findViewById(R.id.tvInts);
        rvCommons = v.findViewById(R.id.rvCalcCommon);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).onCloseDivFrag();
            }
        });
        tvLimits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText(R.string.commonLim);
                tvLimits.setTextColor(Color.parseColor("#0000FF"));
                tvDerivatives.setTextColor(Color.parseColor("#000000"));
                tvIntegrals.setTextColor(Color.parseColor("#000000"));
                myAdapter = new FormulaAdapter(getActivity(), ApplicationClass.commonLimits, FormulaAdapter.FROM_COMMONS);
                rvCommons.setAdapter(myAdapter);
                if(isPortrait()){
                    layoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
                }
                else{
                    layoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
                }
                rvCommons.setLayoutManager(layoutManager);
                myAdapter.notifyDataSetChanged();
            }
        });
        tvDerivatives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText(R.string.commonDer);
                tvLimits.setTextColor(Color.parseColor("#000000"));
                tvDerivatives.setTextColor(Color.parseColor("#0000FF"));
                tvIntegrals.setTextColor(Color.parseColor("#000000"));
                myAdapter = new FormulaAdapter(getActivity(), ApplicationClass.commonDerivs, FormulaAdapter.FROM_COMMONS);
                rvCommons.setAdapter(myAdapter);
                if(isPortrait()){
                    layoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
                }
                else{
                    layoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
                }
                rvCommons.setLayoutManager(layoutManager);
                myAdapter.notifyDataSetChanged();
            }
        });
        tvIntegrals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText(R.string.commonInts);
                tvLimits.setTextColor(Color.parseColor("#000000"));
                tvDerivatives.setTextColor(Color.parseColor("#000000"));
                tvIntegrals.setTextColor(Color.parseColor("#0000FF"));
                myAdapter = new FormulaAdapter(getActivity(), ApplicationClass.commonIntegrals, FormulaAdapter.FROM_COMMONS);
                rvCommons.setAdapter(myAdapter);
                if(isPortrait()){
                    layoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
                }
                else{
                    layoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
                }
                rvCommons.setLayoutManager(layoutManager);

                myAdapter.notifyDataSetChanged();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvCommons = view.findViewById(R.id.rvCalcCommon);
        rvCommons.setHasFixedSize(true);

        if(isPortrait()){
            layoutManager = new GridLayoutManager(this.getActivity(), 2, GridLayoutManager.VERTICAL, false);
        }
        else{
            layoutManager = new GridLayoutManager(this.getActivity(), 3, GridLayoutManager.VERTICAL, false);
        }
        rvCommons.setLayoutManager(layoutManager);
        myAdapter = new FormulaAdapter(this.getActivity(), ApplicationClass.commonLimits, FormulaAdapter.FROM_COMMONS);
        rvCommons.setAdapter(myAdapter);
    }
    boolean isPortrait(){
        return ((MainActivity) getActivity()).isPortrait();
    }
}