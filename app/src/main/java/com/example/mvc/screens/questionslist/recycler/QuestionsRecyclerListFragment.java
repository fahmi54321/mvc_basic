package com.example.mvc.screens.questionslist.recycler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mvc.screens.common.controller.BackPressedListener;
import com.example.mvc.screens.common.controller.BaseFragment;
import com.example.mvc.screens.questionslist.QuestionsListController;
import com.example.mvc.screens.questionslist.questionslistitem.QuestionsListViewMvc;

public class QuestionsRecyclerListFragment extends BaseFragment implements BackPressedListener {
    private QuestionsListController questionsListController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        QuestionsListViewMvc viewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(null);
        questionsListController = getCompositionRoot().getQuestionsListController();
        questionsListController.bindView(viewMvc);
        return viewMvc.getRootView();
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
    public boolean onBackPressed() {
        return questionsListController.onBackPressed();
    }
}
