package com.advaith.physicsformulas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.advaith.physicsformulas.Formula;
import com.advaith.physicsformulas.R;
import com.jstarczewski.pc.mathview.src.MathView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFrag extends Fragment {

    View v;
    ScrollView s;
    LinearLayout svLayout;
    ViewGroup cont;

    public DetailFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_detail, container, false);
        s = v.findViewById(R.id.svScroll);
        svLayout = v.findViewById(R.id.svLayout);
        cont = container;
        return v;
    }



    public void formulaClicked(Formula f){
//        svLayout.removeAllViews();
//        int l = f.getLayout();
//        svLayout.addView(getLayoutInflater().inflate(l, cont, false));
        s.removeAllViews();
        int l = f.getLayout();
        s.addView(getLayoutInflater().inflate(l, cont, false));
        //((MathView) (s.getChildAt(0))).setText(f.getFormulaText());
        ((MathView)(s.getChildAt(0).findViewById(R.id.ivFormDetail2))).setText(f.getFormulaText());
    }
}