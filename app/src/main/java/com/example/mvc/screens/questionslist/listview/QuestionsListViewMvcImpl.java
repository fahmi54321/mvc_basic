package com.example.mvc.screens.questionslist.listview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.mvc.R;
import com.example.mvc.questions.Question;
import com.example.mvc.screens.common.BaseObservableViewMvc;
import com.example.mvc.screens.common.ViewMvcFactory;
import com.example.mvc.screens.questionslist.QuestionsListViewMvc;
import com.example.mvc.screens.questionslist.adapter.listview.QuestionsListViewAdapter;

import java.util.List;

public class QuestionsListViewMvcImpl extends BaseObservableViewMvc<QuestionsListViewMvc.Listener> implements QuestionsListViewAdapter.OnQuestionClickListener, QuestionsListViewMvc {

    private final ListView mLstQuestions;
    private final QuestionsListViewAdapter mQuestionsListViewAdapter;

    private final ProgressBar mProgressBar;

    public QuestionsListViewMvcImpl(LayoutInflater layoutInflater, ViewGroup viewGroup, ViewMvcFactory viewMvcFactory) {
        setRootView(layoutInflater.inflate(R.layout.layout_questions_list,viewGroup, false));
        mLstQuestions = findViewById(R.id.lst_questions);
        mProgressBar = findViewById(R.id.progress);
        mQuestionsListViewAdapter = new QuestionsListViewAdapter(getContext(), this, viewMvcFactory);
        mLstQuestions.setAdapter(mQuestionsListViewAdapter);
    }
    @Override
    public void onQuestionClicked(Question question) {
        for (Listener listener: getListeners()){
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
