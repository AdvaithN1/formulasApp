package com.advaith.physicsformulas.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.advaith.physicsformulas.ApplicationClass;
import com.advaith.physicsformulas.R;
import com.advaith.physicsformulas.Subject;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder>{
    private ArrayList<Subject> subjects;
    ItemClicked activity;


    public SubjectAdapter(Context context, ArrayList<Subject> list){
        subjects = list;
        activity = (ItemClicked) context;
    }

    public interface ItemClicked{
        void onItemClicked(int index);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvSubject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubject = itemView.findViewById(R.id.tvSubject);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setBackgroundColor(Color.parseColor("#FF018786"));
                    activity.onItemClicked(subjects.indexOf((Subject) v.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public SubjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subjects_row_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(subjects.get(position));
        holder.tvSubject.setText(subjects.get(position).getSubject());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }
}
