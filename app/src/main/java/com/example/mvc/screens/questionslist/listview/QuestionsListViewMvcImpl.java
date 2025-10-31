package com.example.mvc.screens.questionslist.listview;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mvc.R;
import com.example.mvc.questions.Question;
import com.example.mvc.screens.common.BaseObservableViewMvc;
import com.example.mvc.screens.questionslist.QuestionsListViewMvc;
import com.example.mvc.screens.questionslist.adapter.listview.QuestionsListViewAdapter;

import java.util.List;

public class QuestionsListViewMvcImpl extends BaseObservableViewMvc<QuestionsListViewMvc.Listener> implements QuestionsListViewAdapter.OnQuestionClickListener, QuestionsListViewMvc {

    private final ListView mLstQuestions;
    private final QuestionsListViewAdapter mQuestionsListViewAdapter;
    public QuestionsListViewMvcImpl(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        setRootView(layoutInflater.inflate(R.layout.layout_questions_list,viewGroup, false));
        mLstQuestions = findViewById(R.id.lst_questions);
        mQuestionsListViewAdapter = new QuestionsListViewAdapter(getContext(), this);
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
}
