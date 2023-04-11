package com.advaith.physicsformulas.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.advaith.physicsformulas.Formula;
import com.advaith.physicsformulas.R;
import com.jstarczewski.pc.mathview.src.MathView;

import java.util.ArrayList;

public class FormulaAdapter extends RecyclerView.Adapter<FormulaAdapter.ViewHolder> {
    private ArrayList<Formula> formulas;
    public static final int FROM_FORMULA = 0;
    public static final int FROM_COMMONS = 1;
    private int formCode;
    FormulaClicked activity;

    public FormulaAdapter(Context context, ArrayList<Formula> list, int code) {
        formulas = list;
        activity = (FormulaClicked) context;
        formCode = code;
    }

    public interface FormulaClicked{
        void onFormulaClicked(int index);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        MathView mvFormula;
        CardView c;
        TextView tvPlacement;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                mvFormula = itemView.findViewById(R.id.mvForm);
                c = itemView.findViewById(R.id.cvWeb);
                tvPlacement = itemView.findViewById(R.id.tvPlacement);
            } catch(Exception ignore){}
            switch (formCode){
                case FROM_FORMULA:
                    tvPlacement.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            activity.onFormulaClicked(formulas.indexOf((Formula) v.getTag()));
                        }
                    });
                    break;
                case FROM_COMMONS:
                    tvPlacement.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Toast.makeText((Context) activity, "CLICKED", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
            }


        }
    }

    @NonNull
    @Override
    public FormulaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (formCode == FROM_COMMONS) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commons_row_layout, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.formulas_row_layout, parent, false);
        }
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull FormulaAdapter.ViewHolder holder, int position) {
        holder.tvPlacement.setTag(formulas.get(position));
        holder.mvFormula.setText(formulas.get(position).getFormulaText());
        holder.mvFormula.setTextZoom(formulas.get(position).getZoom());
    }

    @Override
    public int getItemCount() {
        return formulas.size();
    }


}
