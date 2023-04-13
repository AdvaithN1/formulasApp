package com.advaith.physicsformulas.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.advaith.physicsformulas.ApplicationClass;
import com.advaith.physicsformulas.Moi;
import com.advaith.physicsformulas.adapters.FormulamoiAdapter;
import com.advaith.physicsformulas.fragments.CalcCommonFragment;
import com.advaith.physicsformulas.fragments.DetailFrag;
import com.advaith.physicsformulas.adapters.FormulaAdapter;
import com.advaith.physicsformulas.fragments.FormulaFrag;
import com.advaith.physicsformulas.fragments.FormulamoiFragment;
import com.advaith.physicsformulas.fragments.ListFrag;
import com.advaith.physicsformulas.R;
import com.advaith.physicsformulas.Subject;
import com.advaith.physicsformulas.adapters.SubjectAdapter;
import com.advaith.physicsformulas.fragments.TitleFrag;
import com.jstarczewski.pc.mathview.src.MathView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements SubjectAdapter.ItemClicked, FormulaAdapter.FormulaClicked, FormulamoiAdapter.MoiClicked, FormulamoiFragment.DividerClose {

    ListFrag listFrag;
    FragmentManager fragmentManager;
    ArrayList<Subject> subjects;
    TitleFrag titleFrag;
    FormulaFrag formulaFrag;
    DetailFrag detailFrag;
    Dialog dialog;
    int index;//idk what this is
    int subjectIndex = ApplicationClass.getSubjectIndex();
    int subjectColorIndex; //Subject index that should be colored in
    int formulaIndex = ApplicationClass.getFormulaIndex();

    //Guideline variables
    ImageView ivDivider;

    Guideline guideline;
    boolean firstTime = true;
    float change;

    float upperBound;
    float lowerBound;

    boolean isOpen = true;//if the moment of inertias is open
    ImageButton ibOpenMoi;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        subjects = ApplicationClass.subjects;

        fragmentManager = this.getSupportFragmentManager();
        listFrag = (ListFrag) fragmentManager.findFragmentById(R.id.fragmentList);
        titleFrag = (TitleFrag)  fragmentManager.findFragmentById(R.id.fragmentTitle);
        formulaFrag = (FormulaFrag) getSupportFragmentManager().findFragmentById(R.id.fragmentFormula);
        detailFrag = (DetailFrag) getSupportFragmentManager().findFragmentById(R.id.fragmentDetail);
        switch(getIntent().getExtras().getInt("Course")){
            case 0:
                break;
            case 1:
                fragmentManager.beginTransaction().replace(R.id.fragmentGuidelined, new CalcCommonFragment()).commit();
                break;
        }
        upperBound = (float) 0.15;
        lowerBound = (float) 0.85;

        guideline = findViewById(R.id.guideline);
        ivDivider = findViewById(R.id.main_divider_img);
        ibOpenMoi = findViewById(R.id.ibOpenMoi);
        ibOpenMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) guideline.getLayoutParams();
                params.guidePercent = (float)0.5;
                guideline.setLayoutParams(params);
                ivDivider.setVisibility(View.VISIBLE);
                ibOpenMoi.setVisibility(View.GONE);
                isOpen = true;
            }
        });

        ivDivider.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                int height;
                switch (motionEvent.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("Moved Down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        height = findViewById(R.id.main).getHeight();
                        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) guideline.getLayoutParams();
                        if(firstTime){
                            change = motionEvent.getRawY() - ((float) height/2);
                            firstTime = false;
                        }
                        if(params.guidePercent>0.85){
                            params.guidePercent = (float) 0.84;
                        }
                        else if(params.guidePercent<0.15){
                            params.guidePercent = (float) 0.16;
                        }
                        else {
                            if(!(((motionEvent.getRawY() - change) / height)>0.85 || ((motionEvent.getRawY() - change) / height)<0.15)) {
                                params.guidePercent = ((motionEvent.getRawY() - change) / height);
                            }
                        }
                        guideline.setLayoutParams(params);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        onCloseDivFrag();
    }

    public boolean isPortrait(){
        return findViewById(R.id.layout_port) != null;
    }

    public void onItemClicked(int index) {

        titleFrag.updateText(subjects.get(index).getSubject());
        System.out.println("sub: " + subjectIndex + "form: " + formulaIndex);

        formulaFrag.setList(subjects.get(index));

        if(isPortrait()){

            fragmentManager.beginTransaction()
                    .hide(detailFrag)
                    .commit();

            fragmentManager.beginTransaction()
                    .hide(listFrag)
                    .commit();

            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .hide(listFrag)
                    .show(formulaFrag)
                    .show(titleFrag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null)
                    .commit();

        }
        else{//in landscape
            for(int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                fragmentManager.popBackStack();
            }

            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .hide(detailFrag)
                    .show(formulaFrag)
                    .show(titleFrag)
                    .commit();
        }
        ApplicationClass.setSubjectIndex(index);
        listFrag.resetColorAll();
        subjectIndex = index;
        ApplicationClass.setFormulaIndex(-1);
        formulaIndex = -1;
        this.index = index;
        subjectIndex=index;
        subjectColorIndex=index;
        listFrag.setColor(subjectIndex);
    }

    @Override
    public void onFormulaClicked(int index1) {
        fragmentManager.beginTransaction()
                .hide(formulaFrag)
                .commit();
        fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .show(detailFrag)
                .commit();
        detailFrag.formulaClicked(ApplicationClass.subjects.get(index).getArray().get(index1));
        //in portrait mode
        if (!isPortrait()) {

            for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                fragmentManager.popBackStack();
            }

        }
        fragmentManager.beginTransaction()
                .show(detailFrag)
                .hide(formulaFrag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack("Formula Clicked")
                .commit();
        ApplicationClass.setFormulaIndex(index1);
        formulaIndex = index1;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //phone in portrait mode
        Log.println(Log.ASSERT, "INDEXES: ", subjectIndex + " " + formulaIndex);

        if (isPortrait()) {
            //System.out.println("In portrait mode!!!");
            fragmentManager.beginTransaction()
                    .hide(titleFrag)
                    .hide(formulaFrag)
                    .hide(detailFrag)
                    .show(listFrag)
                    .commit();
            for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                fragmentManager.popBackStack();
            }
            if (subjectIndex != -1)//subject clicked
            {
                if (formulaIndex != -1)//formula clicked
                {
                    int a = formulaIndex;
                    onItemClicked(subjectIndex);
                    //System.out.println("Formula portrait layout change");
                    onFormulaClicked(a);
                } else {
                    onItemClicked(subjectIndex);
                }
            }
        }
        //in landscape mode
        else{
            //System.out.println("In landscape mode!!!");
            fragmentManager.beginTransaction()
                    .hide(detailFrag)
                    .show(titleFrag)
                    .show(formulaFrag)
                    .show(listFrag)
                    .commit();
            for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                fragmentManager.popBackStack();
            }
            if (subjectIndex != -1)//subject clicked
            {
                //System.out.println("Subject landscape layout change " + subjectIndex + " formula: " + formulaIndex);
                if (formulaIndex != -1)//formula clicked
                {
                    int a = formulaIndex;
                    onItemClicked(subjectIndex);
                    //System.out.println("Formula landscape layout change");
                    onFormulaClicked(a);
                }
                else{
                    onItemClicked(subjectIndex);
                    for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        fragmentManager.popBackStack();
                    }
                }
            }
            else{
                onItemClicked(0);
            }
        }
        if(isPortrait() && ApplicationClass.firstTime){
            fragmentManager.beginTransaction()
                    .hide(titleFrag)
                    .hide(formulaFrag)
                    .hide(detailFrag)
                    .show(listFrag)
                    .commit();
        }
        ApplicationClass.firstTime = false;
        System.out.println(ApplicationClass.getFormulaIndex() + " " + ApplicationClass.getSubjectIndex());
        listFrag.resetColorAll();
    }

    public void onQuizClickedTopic(int index) {
        String prompt = "";
        for(int i = 0; i<ApplicationClass.courses.get(getIntent().getExtras().getInt("Course")).getArray().get(index).getArray().size(); i++){
            prompt = prompt + ApplicationClass.courses.get(getIntent().getExtras().getInt("Course")).getArray().get(index).getArray().get(i).getFormTextNormal() + ", ";
        }
        Log.println(Log.ASSERT, "", ("QUIZ CLICKED : " + prompt));
        Intent intent = new Intent(this, QuizActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("prompt", prompt);
        intent.putExtra("name", ApplicationClass.courses.get(getIntent().getExtras().getInt("Course")).getArray().get(index).getSubject());
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onBackPressed() {
        if(!isPortrait()) { //landscape mode
            if(formulaIndex != -1){
                super.onBackPressed();
                ApplicationClass.setFormulaIndex(-1);
                formulaIndex = -1;
            }
            else{
                for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                    fragmentManager.popBackStack();
                }
                setApplicationIndex();
                Intent intent = new Intent(this, CourseActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
            }
        }
        else{ //portrait mode
            if (listFrag != null && listFrag.isVisible()) {
                for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                    fragmentManager.popBackStack();
                }
                setApplicationIndex();
                Intent intent = new Intent(this, CourseActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                //ApplicationClass.setSubjectIndex(-1);
                setApplicationIndex();
                startActivity(intent);
                this.finish();
            }
            else {
                if (detailFrag != null && detailFrag.isVisible()) {
                    fragmentManager.beginTransaction()
                            .hide(detailFrag)
                            .commit();

                    fragmentManager.beginTransaction()
                            .hide(listFrag)
                            .commit();

                    fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                            //.hide(frag2)
                            .hide(listFrag)
                            .show(formulaFrag)
                            .show(titleFrag)
                            //.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                    ApplicationClass.setFormulaIndex(-1);
                    formulaIndex = -1;
                }
                else{
                    fragmentManager.beginTransaction()
                            .hide(titleFrag)
                            .hide(formulaFrag)
                            .hide(detailFrag)
                            .show(listFrag)
                            .commit();
                }
            }
        }
    }

    @Override
    public void onMoiClicked(int index) {
        showCustomDialog(ApplicationClass.mois.get(index));
    }

    void showCustomDialog(Moi m){
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.moi_dialog);

        final Button btnClose = dialog.findViewById(R.id.btnClosedia);
        final ImageView ivVisual = dialog.findViewById(R.id.ivVisualdia);
        final MathView ivForm = dialog.findViewById(R.id.mvMoidia);
        final TextView tvDesc = dialog.findViewById(R.id.tvMoidia);

        ivVisual.setImageResource(m.getVisual());
        ivForm.setText(m.getFormula());
        tvDesc.setText(m.getDescription());

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    public void onCloseDivFrag() {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) guideline.getLayoutParams();
        params.guidePercent = 1;
        guideline.setLayoutParams(params);
        ivDivider.setVisibility(View.GONE);
        ibOpenMoi.setVisibility(View.VISIBLE);
        isOpen = false;
    }

    public int getSubjectColorIndex(){
        return subjectColorIndex;
    }

    public void setApplicationIndex(){
        Log.println(Log.ASSERT, "CalculusInd", Integer.toString(ApplicationClass.calculusSubjectIndex));
        Log.println(Log.ASSERT, "seting indexes", Integer.toString(getIntent().getExtras().getInt("Course")));
        switch(getIntent().getExtras().getInt("Course")){
            case 0:
                ApplicationClass.setPhysicsFormulaIndex(formulaIndex);
                ApplicationClass.setPhysicsSubjectIndex(subjectIndex);
                break;
            case 1:
                Log.println(Log.ASSERT, "SETTING CASE 1", "Case 1");
                ApplicationClass.setCalculusFormulaIndex(formulaIndex);
                ApplicationClass.setCalculusSubjectIndex(subjectIndex);
                break;
        }
        Log.println(Log.ASSERT, "Done Setting", Integer.toString(ApplicationClass.calculusSubjectIndex));
    }


}