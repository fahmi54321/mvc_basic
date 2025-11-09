package com.example.mvc.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvc.R;
import com.example.mvc.screens.common.controller.BackPressedListener;
import com.example.mvc.screens.common.controller.BaseActivity;


public class QuestionDetailsActivity extends BaseActivity{

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";
    public static final String ARG_QUESTION_ID = "ARG_QUESTION_ID";

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    private BackPressedListener mBackPressedListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_content_frame);
        QuestionDetailsFragment fragment;
        if(savedInstanceState == null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragment = QuestionDetailsFragment.newInstance(getQuestionId());
            fragmentTransaction.add(R.id.frame_content, fragment);
            fragmentTransaction.commit();
        }else{
            fragment = (QuestionDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.frame_content);
        }

        mBackPressedListener = fragment;
    }

    private String getQuestionId() {
        return getIntent().getStringExtra(EXTRA_QUESTION_ID);
    }

    @Override
    public void onBackPressed() {
        if(!mBackPressedListener.onBackPressed()){
            super.onBackPressed();
        }
    }

}