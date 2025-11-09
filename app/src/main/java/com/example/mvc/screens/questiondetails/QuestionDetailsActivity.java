package com.example.mvc.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.mvc.screens.common.controller.BaseActivity;
import com.example.mvc.screens.common.navdrawer.DrawerItems;


public class QuestionDetailsActivity extends BaseActivity{

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    private QuestionDetailsController mQuestionDetailsController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QuestionDetailsViewMvc mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(null);

        mQuestionDetailsController = getCompositionRoot().getQuestionDetailsController();
        mQuestionDetailsController.bindView(mViewMvc);
        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQuestionDetailsController.onStart(getQuestionId());

    }

    @Override
    protected void onStop() {
        super.onStop();
        mQuestionDetailsController.onStop();
    }

    private String getQuestionId() {
        return getIntent().getStringExtra(EXTRA_QUESTION_ID);
    }

    @Override
    public void onBackPressed() {
        if(!mQuestionDetailsController.onBackPressed()){
            super.onBackPressed();
        }
    }
}