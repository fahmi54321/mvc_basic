package com.example.mvc.screens.questionslist.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvc.R;
import com.example.mvc.questions.Question;
import com.example.mvc.screens.common.BaseObservableViewMvc;
import com.example.mvc.screens.common.ObservableViewMvc;
import com.example.mvc.screens.questionslist.QuestionsListViewMvc;
import com.example.mvc.screens.questionslist.adapter.recycler.QuestionsRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuestionsRecyclerMvcImpl extends BaseObservableViewMvc<QuestionsListViewMvc.Listener> implements QuestionsListViewMvc, QuestionsRecyclerAdapter.Listener {

    private final RecyclerView mRecyclerQuestions;
    private final QuestionsRecyclerAdapter mAdapter;

    public QuestionsRecyclerMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_questions_list_recycler, parent, false));

        mRecyclerQuestions = findViewById(R.id.recycler_questions);
        mRecyclerQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new QuestionsRecyclerAdapter(inflater, this);
        mRecyclerQuestions.setAdapter(mAdapter);
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
}
