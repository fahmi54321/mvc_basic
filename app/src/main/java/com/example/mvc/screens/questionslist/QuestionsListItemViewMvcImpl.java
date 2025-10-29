package com.example.mvc.screens.questionslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvc.R;
import com.example.mvc.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListItemViewMvcImpl implements  QuestionsListItemViewMvc {

    View mRootView;

    private final List<Listener> mListeners = new ArrayList<>(1);
    private Question mQuestion;
    private TextView mTxtTitle;

    public QuestionsListItemViewMvcImpl(LayoutInflater layoutInflater, ViewGroup parent) {
        mRootView = layoutInflater.inflate(R.layout.layout_question_list_item, parent, false);
        mTxtTitle = findViewById(R.id.txt_title);

        getRootView().setOnClickListener(v -> {
            for(Listener listener: mListeners){
                listener.onQuestionClicked(mQuestion);
            }
        });
    }

    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

    @Override
    public void registerListener(Listener listener) {
        mListeners.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        mListeners.remove(listener);
    }

    @Overridem
    public void bindQuestion(Question question) {
        mQuestion = question;
        mTxtTitle.setText(question.getTitle());
    }

    @Override
    public View getRootView() {
        return mRootView;
    }
}
