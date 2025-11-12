package com.example.mvc.screens.questionslist.listview;

import static com.example.mvc.screens.questionslist.QuestionsListController.SAVED_STATE_SCREEN_STATE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mvc.screens.common.controller.BaseFragment;
import com.example.mvc.screens.questionslist.QuestionsListController;
import com.example.mvc.screens.questionslist.questionslistitem.QuestionsListViewMvc;

public class QuestionsListFragment extends BaseFragment{
    private QuestionsListController questionsListController;

    public static Fragment newInstance() {
        return new QuestionsListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        QuestionsListViewMvc viewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(container);
        questionsListController = getCompositionRoot().getQuestionsListController();
        questionsListController.bindView(viewMvc);
        if(savedInstanceState != null){
            restoreControllerState(savedInstanceState);
        }
        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        questionsListController.restoreSavedState(
                (QuestionsListController.SavedState) savedInstanceState.getSerializable(SAVED_STATE_SCREEN_STATE)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        questionsListController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        questionsListController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_SCREEN_STATE,questionsListController.getSavedState());
    }
}
