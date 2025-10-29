package com.example.mvc.screens.questionslist.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mvc.R;
import com.example.mvc.questions.Question;
import com.example.mvc.screens.questionslist.QuestionsListViewMvc;
import com.example.mvc.screens.questionslist.adapter.listview.QuestionsListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListViewMvcImpl implements QuestionsListViewAdapter.OnQuestionClickListener, QuestionsListViewMvc {

    private final List<Listener> mListeners = new ArrayList<>(1);
    private final View rootView;
    private final ListView mLstQuestions;
    private final QuestionsListViewAdapter mQuestionsListViewAdapter;
    public QuestionsListViewMvcImpl(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        rootView = layoutInflater.inflate(R.layout.layout_questions_list,viewGroup, false);
        mLstQuestions = findViewById(R.id.lst_questions);
        mQuestionsListViewAdapter = new QuestionsListViewAdapter(getContext(), this);
        mLstQuestions.setAdapter(mQuestionsListViewAdapter);
    }

    @Override
    public void registerListener(Listener listener){
        mListeners.add(listener);
    }
    @Override
    public void unregisterListener(Listener listener){
        mListeners.remove(listener);
    }

    private Context getContext() {
        return rootView.getContext();
    }

    private <T extends View> T findViewById(int id) {
        return rootView.findViewById(id);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void onQuestionClicked(Question question) {
        for (Listener listener: mListeners){
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
