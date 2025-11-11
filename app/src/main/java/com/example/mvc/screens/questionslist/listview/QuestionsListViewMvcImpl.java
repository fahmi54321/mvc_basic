package com.example.mvc.screens.questionslist.listview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;

import com.example.mvc.R;
import com.example.mvc.questions.Question;
import com.example.mvc.screens.common.navdrawer.NavDrawerHelper;
import com.example.mvc.screens.common.navdrawer.NavDrawerViewMvcImpl;
import com.example.mvc.screens.common.navdrawer.DrawerItems;
import com.example.mvc.screens.common.toolbar.ToolbarViewMvc;
import com.example.mvc.screens.common.ViewMvcFactory;
import com.example.mvc.screens.common.views.BaseObservableViewMvc;
import com.example.mvc.screens.questionslist.questionslistitem.QuestionsListViewMvc;
import com.example.mvc.screens.questionslist.adapter.listview.QuestionsListViewAdapter;

import java.util.List;

public class QuestionsListViewMvcImpl extends BaseObservableViewMvc<QuestionsListViewMvc.Listener> implements QuestionsListViewAdapter.OnQuestionClickListener, QuestionsListViewMvc {

    private final ListView mLstQuestions;
    private final QuestionsListViewAdapter mQuestionsListViewAdapter;

    private final ProgressBar mProgressBar;

    private final Toolbar mToolbar;

    private final NavDrawerHelper mNavDrawerHelper;

    private ToolbarViewMvc mToolbarViewMvc;

    public QuestionsListViewMvcImpl(LayoutInflater layoutInflater, ViewGroup viewGroup, ViewMvcFactory viewMvcFactory, NavDrawerHelper mNavDrawerHelper) {
        this.mNavDrawerHelper = mNavDrawerHelper;

        setRootView(layoutInflater.inflate(R.layout.layout_questions_list, viewGroup, false));
        mLstQuestions = findViewById(R.id.lst_questions);
        mProgressBar = findViewById(R.id.progress);
        mToolbar = findViewById(R.id.toolbar);

        mQuestionsListViewAdapter = new QuestionsListViewAdapter(getContext(), this, viewMvcFactory);
        mLstQuestions.setAdapter(mQuestionsListViewAdapter);

        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(viewGroup);
        mToolbarViewMvc.setTitle("Test");
        mToolbar.addView(mToolbarViewMvc.getRootView());

        mToolbarViewMvc.enableHamburgerButtonAndListen(() -> mNavDrawerHelper.openDrawer());
    }

    @Override
    public void onQuestionClicked(Question question) {
        for (Listener listener : getListeners()) {
            listener.onQuestionClicked(question);
        }
    }

    @Override
    public void bindQuestions(List<Question> questions) {
        mQuestionsListViewAdapter.clear();
        mQuestionsListViewAdapter.addAll(questions);
        mQuestionsListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        mProgressBar.setVisibility(View.GONE);
    }
}
