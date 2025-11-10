package com.example.mvc.screens.common.screensnavigator;

import android.app.Activity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvc.screens.common.controller.FragmentFrameWrapper;
import com.example.mvc.screens.questiondetails.QuestionDetailsFragment;
import com.example.mvc.screens.questionslist.listview.QuestionsListFragment;
import com.example.mvc.screens.questionslist.recycler.QuestionsRecyclerListFragment;

public class ScreensNavigator {
    private final FragmentManager mFragmentManager;
    private final FragmentFrameWrapper mFragmentFrameWrapper;

    public ScreensNavigator(FragmentManager mFragmentManager, FragmentFrameWrapper mFragmentFrameWrapper) {
        this.mFragmentManager = mFragmentManager;
        this.mFragmentFrameWrapper = mFragmentFrameWrapper;
    }

    public void toQuestionDetails(String id) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(mFragmentFrameWrapper.getFragmentFrame().getId(), QuestionDetailsFragment.newInstance(id)).commit();
    }

    public void toQuestionsList() {
        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(mFragmentFrameWrapper.getFragmentFrame().getId(), QuestionsListFragment.newInstance()).commit();
    }

    public void toQuestionsRecycler() {
        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(mFragmentFrameWrapper.getFragmentFrame().getId(), QuestionsRecyclerListFragment.newInstance()).commit();
    }

    public void onBackPressed() {
        if (mFragmentManager.getBackStackEntryCount() > 0) {
            mFragmentManager.popBackStack();
        } else {
            if (mFragmentFrameWrapper.getFragmentFrame().getContext() instanceof Activity) {
                ((Activity) mFragmentFrameWrapper.getFragmentFrame().getContext())
                        .onBackPressed();
            }
        }
    }
}
