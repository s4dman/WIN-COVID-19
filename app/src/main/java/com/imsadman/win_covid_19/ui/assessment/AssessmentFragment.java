package com.imsadman.win_covid_19.ui.assessment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.imsadman.win_covid_19.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AssessmentFragment extends Fragment {

    private TextView mAssess;
    private View mPositivePage, mMaybePage, mIsolatePage;
    private Button mQ1YesBtn, mQ1NoBtn, mQ2YesBtn, mQ2NoBtn, mQ3YesBtn, mQ3NoBtn, mQ4YesBtn, mQ4NoBtn, mQ5YesBtn, mQ5NoBtn;
    private ConstraintLayout mQ1Layout, mQ2Layout, mQ3Layout, mQ4Layout, mQ5Layout;
    private Boolean mSymptoms = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_assessment, container, false);
        initViews(root);
        return root;
    }

    private void initViews(View root) {
        mAssess = root.findViewById(R.id.text_assess);
        mPositivePage = root.findViewById(R.id.layout_positive_result);
        mMaybePage = root.findViewById(R.id.layout_maybe_result);
        mIsolatePage = root.findViewById(R.id.layout_isolate_result);
        mQ1YesBtn = root.findViewById(R.id.q1_btn_yes);
        mQ1NoBtn = root.findViewById(R.id.q1_btn_no);
        mQ2YesBtn = root.findViewById(R.id.q2_btn_yes);
        mQ2NoBtn = root.findViewById(R.id.q2_btn_no);
        mQ3YesBtn = root.findViewById(R.id.q3_btn_yes);
        mQ3NoBtn = root.findViewById(R.id.q3_btn_no);
        mQ4YesBtn = root.findViewById(R.id.q4_btn_yes);
        mQ4NoBtn = root.findViewById(R.id.q4_btn_no);
        mQ5YesBtn = root.findViewById(R.id.q5_btn_yes);
        mQ5NoBtn = root.findViewById(R.id.q5_btn_no);
        mQ1Layout = root.findViewById(R.id.ques1);
        mQ2Layout = root.findViewById(R.id.ques2);
        mQ3Layout = root.findViewById(R.id.ques3);
        mQ4Layout = root.findViewById(R.id.ques4);
        mQ5Layout = root.findViewById(R.id.ques5);

        onClick();
    }

    private void onClick() {
        mQ1YesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAssess.setVisibility(View.GONE);
                mQ1Layout.setVisibility(View.GONE);
                mPositivePage.setVisibility(View.VISIBLE);
            }
        });

        mQ1NoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQ1Layout.setVisibility(View.GONE);
                mQ2Layout.setVisibility(View.VISIBLE);
                viewQ2();
            }
        });
    }

    private void viewQ2() {
        if (mQ2Layout.getVisibility() == View.VISIBLE) {
            mQ2YesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mQ2Layout.setVisibility(View.GONE);
                    mQ3Layout.setVisibility(View.VISIBLE);
                    mSymptoms = true;
                    viewQ3();
                }
            });

            mQ2NoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mQ2Layout.setVisibility(View.GONE);
                    mQ3Layout.setVisibility(View.VISIBLE);
                    viewQ3();
                }
            });
        } else Log.d(TAG, "onClick: Not visible");
    }

    private void viewQ3() {
        if (mQ3Layout.getVisibility() == View.VISIBLE) {
            mQ3YesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mQ3Layout.setVisibility(View.GONE);
                    mQ4Layout.setVisibility(View.VISIBLE);
                    viewQ4();
                }
            });

            mQ3NoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mQ3Layout.setVisibility(View.GONE);
                    mQ4Layout.setVisibility(View.VISIBLE);
                    viewQ4();
                }
            });
        }
    }

    private void viewQ4() {
        if (mQ4Layout.getVisibility() == View.VISIBLE) {
            mQ4YesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mAssess.setVisibility(View.GONE);
                    mQ4Layout.setVisibility(View.GONE);
                    mMaybePage.setVisibility(View.VISIBLE);
                }
            });

            mQ4NoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mQ4Layout.setVisibility(View.GONE);
                    mQ5Layout.setVisibility(View.VISIBLE);
                    viewQ5();
                }
            });
        }
    }

    private void viewQ5() {
        if (mQ5Layout.getVisibility() == View.VISIBLE) {
            mQ5YesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mAssess.setVisibility(View.GONE);
                    mQ4Layout.setVisibility(View.GONE);
                    mMaybePage.setVisibility(View.VISIBLE);
                }
            });

            mQ5NoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mSymptoms) {
                        mAssess.setVisibility(View.GONE);
                        mQ5Layout.setVisibility(View.GONE);
                        mMaybePage.setVisibility(View.VISIBLE);
                    } else {
                        mAssess.setVisibility(View.GONE);
                        mQ5Layout.setVisibility(View.GONE);
                        mIsolatePage.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
    }
}