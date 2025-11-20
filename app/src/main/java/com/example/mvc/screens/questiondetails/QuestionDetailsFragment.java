package com.example.mvc.screens.questiondetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mvc.common.dependencyinjection.Service;
import com.example.mvc.screens.common.ViewMvcFactory;
import com.example.mvc.screens.common.controller.BaseFragment;

public class QuestionDetailsFragment extends BaseFragment{
    public static final String ARG_QUESTION_ID = "ARG_QUESTION_ID";
    @Service
    private QuestionDetailsController controller;
    @Service
    private ViewMvcFactory viewMvcFactory;
    private QuestionDetailsViewMvc viewMvc;


    public static QuestionDetailsFragment newInstance(String questionId){
        Bundle args = new Bundle();
        args.putString(ARG_QUESTION_ID,questionId);
        QuestionDetailsFragment fragment = new QuestionDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injector().inject(this);
        controller.registerLauncher();
        controller.setQuestionId(getQuestionId());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewMvc = viewMvcFactory.getQuestionDetailsViewMvc(container);

        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        controller.bindView(viewMvc);
        return viewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        controller.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        controller.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(QuestionDetailsController.SAVED_STATE_SCREEN_STATE, controller.getSavedState());
    }

    private String getQuestionId() {
        return getArguments().getString(ARG_QUESTION_ID);
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        controller.restoreSavedState(
                (QuestionDetailsController.SavedState)
                        savedInstanceState.getSerializable(QuestionDetailsController.SAVED_STATE_SCREEN_STATE)
        );
    }
}
