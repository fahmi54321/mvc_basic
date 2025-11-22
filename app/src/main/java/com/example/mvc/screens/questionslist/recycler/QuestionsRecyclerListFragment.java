package com.example.mvc.screens.questionslist.recycler;

import static com.example.mvc.screens.questionslist.QuestionsListController.SAVED_STATE_SCREEN_STATE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mvc.screens.common.ViewMvcFactory;
import com.example.mvc.screens.common.controller.BaseFragment;
import com.example.mvc.screens.questionslist.QuestionsListController;
import com.example.mvc.screens.questionslist.questionslistitem.QuestionsListViewMvc;

import javax.inject.Inject;

public class QuestionsRecyclerListFragment extends BaseFragment{
    @Inject
    public QuestionsListController controller;
    @Inject
    public ViewMvcFactory viewMvcFactory;
    private QuestionsListViewMvc viewMvc;

    public static Fragment newInstance() {
        return new QuestionsRecyclerListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injector().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewMvc = viewMvcFactory.getQuestionsListViewMvc(container);
        controller.bindView(viewMvc);
        if(savedInstanceState != null){
            restoreControllerState(savedInstanceState);
        }
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
        outState.putSerializable(SAVED_STATE_SCREEN_STATE, controller.getSavedState());
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        controller.restoreSavedState(
                (QuestionsListController.SavedState) savedInstanceState.getSerializable(SAVED_STATE_SCREEN_STATE)
        );
    }
}
