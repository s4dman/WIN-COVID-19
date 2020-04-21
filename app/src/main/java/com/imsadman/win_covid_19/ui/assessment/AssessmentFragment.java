package com.imsadman.win_covid_19.ui.assessment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.imsadman.win_covid_19.R;

public class AssessmentFragment extends Fragment {

    private View mQues1, mPositivePage;
    private Button mYesBtn, mNoBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_assessment, container, false);
        initViews(root);
        return root;
    }

    private void initViews(View root) {
        mQues1 = root.findViewById(R.id.layout_ques_1);
        mPositivePage = root.findViewById(R.id.layout_positive_result);
        mYesBtn = root.findViewById(R.id.assess_btn_yes);
        mNoBtn = root.findViewById(R.id.assess_btn_no);

        onClick();
    }

    private void onClick() {
        mYesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQues1.setVisibility(View.GONE);
                mPositivePage.setVisibility(View.VISIBLE);
            }
        });

        mNoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}