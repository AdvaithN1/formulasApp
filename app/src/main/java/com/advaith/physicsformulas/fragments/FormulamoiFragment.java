package com.advaith.physicsformulas.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.advaith.physicsformulas.ApplicationClass;
import com.advaith.physicsformulas.R;
import com.advaith.physicsformulas.activities.MainActivity;
import com.advaith.physicsformulas.adapters.FormulamoiAdapter;


public class FormulamoiFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    Button btnCloseMoi;
    View view;

    public FormulamoiFragment() {
        // Required empty public constructor
    }

    public interface DividerClose{
        void onCloseDivFrag();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_formulamoi, container, false);

        btnCloseMoi = view.findViewById(R.id.btnCloseMoi);
        btnCloseMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).onCloseDivFrag();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvMois);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this.getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new FormulamoiAdapter(this.getActivity(), ApplicationClass.mois);
        recyclerView.setAdapter(myAdapter);

    }
}