package com.example.mvc.screens.common.screensnavigator;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvc.screens.questiondetails.QuestionDetailsActivity;
import com.example.mvc.screens.questionslist.listview.QuestionsListActivity;
import com.example.mvc.screens.questionslist.recycler.QuestionsRecyclerListActivity;

public class ScreensNavigator {
    private final Activity mActivity;

    public ScreensNavigator(Activity mActivity) {
        this.mActivity = mActivity;
    }

    private Context getContext(){
        return mActivity;
    }

    public void toDialogDetails(String id) {
        QuestionDetailsActivity.start(getContext(),id);
    }

    public void toQuestionsListClearTop() {
        QuestionsListActivity.startClearTop(getContext());
    }

    public void toQuestionsRecyclerClearTop() {
        QuestionsRecyclerListActivity.startClearTop(getContext());
    }

    public void onBackPressed() {
        mActivity.onBackPressed();
    }
}
