package com.example.mvc.screens.questionslist.listview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvc.screens.common.controller.BaseActivity;
import com.example.mvc.screens.questionslist.QuestionsListController;
import com.example.mvc.screens.questionslist.questionslistitem.QuestionsListViewMvc;

public class QuestionsListActivity extends BaseActivity {

    public static void startClearTop(Context context) {
        Intent intent = new Intent(context, QuestionsListActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
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


    @Override
    public void onBackPressed() {
        if(!questionsListController.onBackPressed()){
            super.onBackPressed();
        }
    }
}
