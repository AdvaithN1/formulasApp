package com.advaith.physicsformulas.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.advaith.physicsformulas.ApplicationClass;
import com.advaith.physicsformulas.R;
import com.advaith.physicsformulas.activities.CourseActivity;
import com.advaith.physicsformulas.activities.MainActivity;
import com.advaith.physicsformulas.activities.MoiActivity;
import com.advaith.physicsformulas.adapters.SubjectAdapter;

public class ListFrag extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    //RecyclerView.LayoutManager layoutManager;
    LinearLayoutManager layoutManager;
    ImageButton btnBack;
    View view;
    CardView cvMois;

    public ListFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list, container, false);
        btnBack = view.findViewById(R.id.btn_topicsBack);
        cvMois = view.findViewById(R.id.cvMois);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CourseActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                ((MainActivity) getActivity()).setApplicationIndex();
                System.out.println(ApplicationClass.getFormulaIndex() + " " + ApplicationClass.getSubjectIndex());
                getActivity().finish();
            }
        });
        cvMois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MoiActivity.class);
                ApplicationClass.setSubjectIndex(-1);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rvSubjects);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new SubjectAdapter(this.getActivity(), ApplicationClass.subjects);
        recyclerView.setAdapter(myAdapter);

        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                resetColorAll();
                setColor(((MainActivity) getActivity()).getSubjectColorIndex());
                if(getActivity().findViewById(R.id.layout_port) == null) {
                    recyclerView.setLayoutParams(new ConstraintLayout.LayoutParams(v.getWidth(), v.getHeight()));
                }
            }
        });
    }

    public void setColor(int index){
        if(index!=-1 && recyclerView.getChildAt(index-layoutManager.findFirstVisibleItemPosition())!=null) {
            recyclerView.getChildAt(index-layoutManager.findFirstVisibleItemPosition()).setBackgroundColor(Color.parseColor("#FF018786"));
        }
    }


    public void resetColorAll(){
        for(int i = 0; i<recyclerView.getChildCount(); i++){
            recyclerView.getChildAt(i).setBackgroundColor(Color.parseColor("#A8A8A6"));
        }
    }
}