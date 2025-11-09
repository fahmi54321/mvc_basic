package com.example.mvc.screens.common.screensnavigator;

import android.content.Context;

import com.example.mvc.screens.questiondetails.QuestionDetailsActivity;
import com.example.mvc.screens.questionslist.listview.QuestionsListActivity;
import com.example.mvc.screens.questionslist.recycler.QuestionsRecyclerListActivity;

public class ScreensNavigator {
    private final Context mContext;

    public ScreensNavigator(Context mContext) {
        this.mContext = mContext;
    }

    public void toDialogDetails(String id) {
        QuestionDetailsActivity.start(mContext,id);
    }

    public void toQuestionsListClearTop() {
        QuestionsListActivity.startClearTop(mContext);
    }

    public void toQuestionsRecyclerClearTop() {
        QuestionsRecyclerListActivity.startClearTop(mContext);
    }
}
