package com.example.mvc.screens.questionslist;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvc.R;
import com.example.mvc.questions.Question;
import com.example.mvc.screens.common.BaseObservableViewMvc;
import com.example.mvc.screens.common.BaseViewMvc;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListItemMvcImpl extends BaseObservableViewMvc<QuestionsListItemViewMvc.Listener> implements QuestionsListItemViewMvc {
    private Question mQuestion;
    private TextView mTxtTitle;

    public QuestionsListItemMvcImpl(LayoutInflater layoutInflater, ViewGroup parent) {
        setRootView(layoutInflater.inflate(R.layout.layout_question_list_item, parent, false));
        mTxtTitle = findViewById(R.id.txt_title);

        getRootView().setOnClickListener(v -> {
            for(Listener listener: getListeners()){
                listener.onQuestionClicked(mQuestion);
            }
        });
    }

    @Override
    public void bindQuestion(Question question) {
        mQuestion = question;
        mTxtTitle.setText(question.getTitle());
    }
}
