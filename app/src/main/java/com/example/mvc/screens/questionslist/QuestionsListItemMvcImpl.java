package com.example.mvc.screens.questionslist;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvc.R;
import com.example.mvc.questions.Question;
import com.example.mvc.screens.common.BaseViewMvc;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListItemMvcImpl extends BaseViewMvc implements QuestionsListItemViewMvc {
    private final List<Listener> mListeners = new ArrayList<>(1);
    private Question mQuestion;
    private TextView mTxtTitle;

    public QuestionsListItemMvcImpl(LayoutInflater layoutInflater, ViewGroup parent) {
        setRootView(layoutInflater.inflate(R.layout.layout_question_list_item, parent, false));
        mTxtTitle = findViewById(R.id.txt_title);

        getRootView().setOnClickListener(v -> {
            for(Listener listener: mListeners){
                listener.onQuestionClicked(mQuestion);
            }
        });
    }

    @Override
    public void registerListener(Listener listener) {
        mListeners.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        mListeners.remove(listener);
    }

    @Override
    public void bindQuestion(Question question) {
        mQuestion = question;
        mTxtTitle.setText(question.getTitle());
    }
}
