package com.example.mvc.screens.questionslist.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvc.R;
import com.example.mvc.questions.Question;
import com.example.mvc.screens.common.navdrawer.BaseNavDrawerViewMvc;
import com.example.mvc.screens.common.navdrawer.DrawerItems;
import com.example.mvc.screens.common.toolbar.ToolbarViewMvc;
import com.example.mvc.screens.common.views.BaseObservableViewMvc;
import com.example.mvc.screens.common.ViewMvcFactory;
import com.example.mvc.screens.questionslist.questionslistitem.QuestionsListViewMvc;
import com.example.mvc.screens.questionslist.adapter.recycler.QuestionsRecyclerAdapter;

import java.util.List;

public class QuestionsRecyclerMvcImpl extends BaseNavDrawerViewMvc<QuestionsListViewMvc.Listener> implements QuestionsListViewMvc, QuestionsRecyclerAdapter.Listener {

    private final RecyclerView mRecyclerQuestions;
    private final QuestionsRecyclerAdapter mAdapter;

    private final ProgressBar mProgressBar;

    private final Toolbar mToolbar;

    private ToolbarViewMvc mToolbarViewMvc;

    public QuestionsRecyclerMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent, ViewMvcFactory viewMvcFactory) {
        super(inflater,parent);
        setRootView(inflater.inflate(R.layout.layout_questions_list_recycler, parent, false));

        mRecyclerQuestions = findViewById(R.id.recycler_questions);
        mProgressBar = findViewById(R.id.progress);
        mRecyclerQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new QuestionsRecyclerAdapter( this, viewMvcFactory);
        mRecyclerQuestions.setAdapter(mAdapter);

        mToolbar = findViewById(R.id.toolbar);
        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(mToolbar);
        mToolbarViewMvc.setTitle("Test");
        mToolbar.addView(mToolbarViewMvc.getRootView());

    }

    @Override
    public void onQuestionClicked(Question question) {
        for (Listener listener : getListeners()) {
            listener.onQuestionClicked(question);
        }
    }

    @Override
    public void bindQuestions(List<Question> questions) {
        mAdapter.bindQuestions(questions);
    }

    @Override
    public void showProgressIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDrawerItemClicked(DrawerItems item) {
        for(Listener listener: getListeners()){
            switch (item){
                case QUESTIONS_LIST:{
                    listener.onQuestionsListClicked();
                }
            }
        }
    }
}
