package com.example.mvc.screens.questionslist.recycler;

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

public class QuestionsRecyclerListFragment extends BaseFragment{
    private QuestionsListController questionsListController;

    public static Fragment newInstance() {
        return new QuestionsRecyclerListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        QuestionsListViewMvc viewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(container);
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
}
