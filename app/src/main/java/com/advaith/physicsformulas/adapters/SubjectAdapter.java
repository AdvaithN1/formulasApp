package com.advaith.physicsformulas.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
        void onQuizClickedTopic(int index);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardSubject;
        TextView tvSubject;
        ImageButton imgBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardSubject = itemView.findViewById(R.id.tvSubjectCard);
            tvSubject=itemView.findViewById(R.id.tvSubject);
            imgBtn = itemView.findViewById(R.id.btnQuizTopics);
            cardSubject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //v.setBackgroundColor(Color.parseColor("#FF018786"));
                    Log.println(Log.ASSERT, "SUBJECT: ", ((Subject) v.getTag()).getSubject());
                    activity.onItemClicked(subjects.indexOf((Subject) v.getTag()));
                }
            });
            imgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //v.setBackgroundColor(Color.parseColor("#FF018786"));
                    activity.onQuizClickedTopic(subjects.indexOf((Subject) v.getTag()));
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
        holder.cardSubject.setTag(subjects.get(position));
        holder.imgBtn.setTag(subjects.get(position));
        holder.tvSubject.setText(subjects.get(position).getSubject());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }
}