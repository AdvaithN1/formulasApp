package com.advaith.physicsformulas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.advaith.physicsformulas.Moi;
import com.advaith.physicsformulas.R;

import java.util.ArrayList;

public class FormulamoiAdapter extends RecyclerView.Adapter<FormulamoiAdapter.ViewHolder> {
    private ArrayList<Moi> mois;
    MoiClicked activity;

    public FormulamoiAdapter(Context context, ArrayList<Moi> list){
        activity= (MoiClicked) context;
        mois = list;
    }

    public interface MoiClicked{
        void onMoiClicked(int index);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivVisualmoi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivVisualmoi = itemView.findViewById(R.id.ivVisualmoi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onMoiClicked(mois.indexOf((Moi) v.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public FormulamoiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.formulamoi_row_layout, parent, false);
        //view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (parent.getHeight() * 0.2)));
        view.setLayoutParams(new ViewGroup.LayoutParams((int) (parent.getWidth() * 0.5), (int) (parent.getWidth() * 0.5)));
        return new FormulamoiAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FormulamoiAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(mois.get(position));

        holder.ivVisualmoi.setImageResource(mois.get(position).getVisual());
    }

    @Override
    public int getItemCount() {
        return mois.size();
    }
}
