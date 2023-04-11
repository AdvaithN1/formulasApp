package com.advaith.physicsformulas.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.advaith.physicsformulas.ApplicationClass;
import com.advaith.physicsformulas.Moi;
import com.advaith.physicsformulas.R;
import com.advaith.physicsformulas.adapters.FormulamoiAdapter;
import com.advaith.physicsformulas.fragments.FormulamoiFragment;
import com.advaith.physicsformulas.fragments.ListmoiFragment;
import com.jstarczewski.pc.mathview.src.MathView;

public class MoiActivity extends AppCompatActivity implements FormulamoiAdapter.MoiClicked {

    FragmentManager fragmentManager;
    ListmoiFragment listFrag;
    FormulamoiFragment formFrag;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moi);
        fragmentManager = this.getSupportFragmentManager();
        listFrag = (ListmoiFragment) fragmentManager.findFragmentById(R.id.fragmentListmoi);
        formFrag = (FormulamoiFragment) fragmentManager.findFragmentById(R.id.fragmentFormulamoi);
    }

    public void onFormulasBtnClicked(){
        System.out.println("Clicked!");
        if(isPortrait()) {
            fragmentManager.beginTransaction()
                    .hide(listFrag)
                    .show(formFrag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    .addToBackStack(null)
                    .commit();
        }
        else{
            fragmentManager.beginTransaction()
                    .show(listFrag)
                    .show(formFrag)
                    .commit();
        }
    }

    @Override
    public void onMoiClicked(int index) {
        showCustomDialog(ApplicationClass.mois.get(index));

    }

    void showCustomDialog(Moi m){
        dialog = new Dialog(MoiActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.moi_dialog);

        final Button btnClose = dialog.findViewById(R.id.btnClosedia);
        final ImageView ivVisual = dialog.findViewById(R.id.ivVisualdia);
        final MathView mvForm = dialog.findViewById(R.id.mvMoidia);
        final TextView tvDesc = dialog.findViewById(R.id.tvMoidia);

        ivVisual.setImageResource(m.getVisual());
        mvForm.setText(m.getFormula());
        tvDesc.setText(m.getDescription());

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    boolean isPortrait(){
        return findViewById(R.id.layout_portraitmoi) != null;
    }

    @Override
    protected void onResume() {
        if(isPortrait()){
            fragmentManager.beginTransaction()
                    .show(listFrag)
                    .hide(formFrag)
                    .commit();
        }
        else{
            System.out.println("Clicked");
            fragmentManager.beginTransaction()
                    .show(formFrag)
                    .commit();
            fragmentManager.beginTransaction()
                    .show(listFrag)
                    .commit();
            for(int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                fragmentManager.popBackStack();
            }
            onFormulasBtnClicked();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if(dialog !=null && dialog.isShowing()) {
            dialog.dismiss();
        }
        super.onDestroy();

    }
}