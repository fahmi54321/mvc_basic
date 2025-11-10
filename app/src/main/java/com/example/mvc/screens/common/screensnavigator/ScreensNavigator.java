package com.example.mvc.screens.common.screensnavigator;

import com.example.mvc.screens.common.fragmentframehelper.FragmentFrameHelper;
import com.example.mvc.screens.questiondetails.QuestionDetailsFragment;
import com.example.mvc.screens.questionslist.listview.QuestionsListFragment;
import com.example.mvc.screens.questionslist.recycler.QuestionsRecyclerListFragment;

public class ScreensNavigator {
    private final FragmentFrameHelper fragmentFrameHelper;

    public ScreensNavigator(FragmentFrameHelper fragmentFrameHelper) {

        this.fragmentFrameHelper = fragmentFrameHelper;
    }

    public void toQuestionDetails(String id) {
        fragmentFrameHelper.replaceFragment(QuestionDetailsFragment.newInstance(id));
    }

    public void toQuestionsList() {
        fragmentFrameHelper.replaceFragmentAndClearBackstack(QuestionsListFragment.newInstance());
    }

    public void toQuestionsRecycler() {
        fragmentFrameHelper.replaceFragmentAndClearBackstack(QuestionsRecyclerListFragment.newInstance());
    }

    public void onBackPressed() {
        fragmentFrameHelper.navigateUp();
    }
}
