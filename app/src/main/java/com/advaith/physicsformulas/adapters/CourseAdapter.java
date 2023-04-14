package com.advaith.physicsformulas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.advaith.physicsformulas.Course;
import com.advaith.physicsformulas.R;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private ArrayList<Course> courses;
    CourseClicked activity;

    public CourseAdapter(Context context, ArrayList<Course> list){
        courses = list;
        activity = (CourseClicked) context;
    }

    public interface CourseClicked{
        void onCourseClicked(int index);
        void onQuizClickedCourse(int index);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCourse;
        ImageButton btnQuiz;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourse = itemView.findViewById(R.id.tvCourse);
            btnQuiz = itemView.findViewById(R.id.btnQuizCourses);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onCourseClicked(courses.indexOf((Course) v.getTag()));
                }
            });
            btnQuiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onQuizClickedCourse(courses.indexOf((Course) view.getTag()));
                }
            });
        }
    }


    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_row_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(courses.get(position));
        holder.btnQuiz.setTag(courses.get(position));
        holder.tvCourse.setText(courses.get(position).getCourse());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
