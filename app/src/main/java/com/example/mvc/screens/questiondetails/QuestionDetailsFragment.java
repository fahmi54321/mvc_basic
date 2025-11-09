package com.example.mvc.screens.questiondetails;

import static com.example.mvc.screens.questiondetails.QuestionDetailsActivity.ARG_QUESTION_ID;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mvc.screens.common.controller.BackPressedListener;
import com.example.mvc.screens.common.controller.BaseFragment;

public class QuestionDetailsFragment extends BaseFragment implements BackPressedListener {
    private QuestionDetailsController mQuestionDetailsController;

    public static QuestionDetailsFragment newInstance(String questionId){
        Bundle args = new Bundle();
        args.putString(ARG_QUESTION_ID,questionId);
        QuestionDetailsFragment fragment = new QuestionDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        QuestionDetailsViewMvc mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(container);

        mQuestionDetailsController = getCompositionRoot().getQuestionDetailsController();
        mQuestionDetailsController.bindView(mViewMvc);
        return mViewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mQuestionDetailsController.onStart(getQuestionId());

    }

    @Override
    public void onStop() {
        super.onStop();
        mQuestionDetailsController.onStop();
    }

    private String getQuestionId() {
        return getArguments().getString(ARG_QUESTION_ID);
    }

    @Override
    public boolean onBackPressed() {
        return mQuestionDetailsController.onBackPressed();
    }
}
