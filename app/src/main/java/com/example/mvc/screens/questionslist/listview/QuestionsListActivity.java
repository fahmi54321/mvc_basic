package com.example.mvc.screens.questionslist.listview;

import android.os.Bundle;

import com.example.mvc.screens.common.BaseActivity;
import com.example.mvc.screens.questionslist.QuestionsListController;
import com.example.mvc.screens.questionslist.QuestionsListViewMvc;

public class QuestionsListActivity extends BaseActivity {
    private QuestionsListController questionsListController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QuestionsListViewMvc viewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(null);
        questionsListController = getCompositionRoot().getQuestionsListController();
        questionsListController.bindView(viewMvc);
        setContentView(viewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        questionsListController.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        questionsListController.onStop();
    }
}
