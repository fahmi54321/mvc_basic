package com.example.mvc.screens.questiondetails;

import static com.example.mvc.screens.questiondetails.QuestionDetailsController.SAVED_STATE_SCREEN_STATE;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mvc.screens.common.controller.BaseFragment;

public class QuestionDetailsFragment extends BaseFragment{
    public static final String ARG_QUESTION_ID = "ARG_QUESTION_ID";
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
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        mQuestionDetailsController.bindView(mViewMvc);
        return mViewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mQuestionDetailsController.setQuestionId(getQuestionId());
        mQuestionDetailsController.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        mQuestionDetailsController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_SCREEN_STATE, mQuestionDetailsController.getSavedState());
    }

    private String getQuestionId() {
        return getArguments().getString(ARG_QUESTION_ID);
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        mQuestionDetailsController.restoreSavedState(
                (QuestionDetailsController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_SCREEN_STATE)
        );
    }
}
