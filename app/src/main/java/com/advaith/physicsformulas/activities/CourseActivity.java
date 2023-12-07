package com.advaith.physicsformulas.activities;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.advaith.physicsformulas.ApplicationClass;
import com.advaith.physicsformulas.adapters.CourseAdapter;
import com.advaith.physicsformulas.R;

public class CourseActivity extends AppCompatActivity implements CourseAdapter.CourseClicked {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ImageButton ib;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        ib = findViewById(R.id.ivInfo);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                } else {
                    finish();
                }
            }

        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView = findViewById(R.id.rvCourses);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new CourseAdapter(this, ApplicationClass.courses);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onCourseClicked(int index) {
        ApplicationClass.setSubjects(ApplicationClass.courses.get(index).getArray());
        ApplicationClass.firstTime = true;
        switch (index){
            case 0:
                ApplicationClass.setSubject(ApplicationClass.physicsArray);
                ApplicationClass.setSubjectIndex(ApplicationClass.physicsSubjectIndex);
                ApplicationClass.setFormulaIndex(ApplicationClass.physicsFormulaIndex);
                Log.println(Log.ASSERT, "CourseAct", ApplicationClass.subjects.toString());
                Intent intent = new Intent(CourseActivity.this, MainActivity.class);
                intent.putExtra("Course", 0);
                startActivity(intent);
                break;
            case 1:
                ApplicationClass.setSubject(ApplicationClass.calculusArray);
                ApplicationClass.setSubjectIndex(ApplicationClass.calculusSubjectIndex);
                ApplicationClass.setFormulaIndex(ApplicationClass.calculusFormulaIndex);
                Log.println(Log.ASSERT, "Subject INd: ", Integer.toString(ApplicationClass.calculusSubjectIndex));
                Intent intent1 = new Intent(CourseActivity.this, MainActivity.class);
                intent1.putExtra("Course", 1);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onQuizClickedCourse(int index) {
        String prompt = "ALL FORMULAS in " + ApplicationClass.courses.get(index).getCourse();
        Log.println(Log.ASSERT, "", ("QUIZ CLICKED : " + prompt));
        Intent intent = new Intent(this, QuizActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("prompt", prompt);
        intent.putExtra("name", ApplicationClass.courses.get(index).getCourse());
        startActivity(intent);
        this.finish();
    }
    void showCustomDialog() {
        dialog = new Dialog(CourseActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.privacy_policy_dialog);

        final Button btnPrivacy = dialog.findViewById(R.id.btnPrivacy);
        final Button btnClosePrivacy = dialog.findViewById(R.id.btnClosePrivacy);
        btnPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.privacypolicies.com/live/5906f411-a666-4ba5-b953-d00272836c59";
                try {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                catch (Exception e){
                    System.out.println("INTENT ERROR: ActivityNotFoundException");
                }
            }
        });
        btnClosePrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        if(dialog !=null && dialog.isShowing()) {
            dialog.dismiss();
        }
        super.onDestroy();
    }


//    DEPRECATED
//    @Override
//    public void onBackPressed() {
//        if (dialog != null && dialog.isShowing()) {
//            dialog.dismiss();
//        } else {
//            this.finish();
//        }
//        super.onBackPressed();
//    }


}