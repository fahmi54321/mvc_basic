package com.example.mvc.screens.questionslist;

import android.view.View;

import com.example.mvc.questions.Question;
import com.example.mvc.screens.common.ObservableViewMvc;
import com.example.mvc.screens.common.ViewMvc;

public interface QuestionsListItemViewMvc extends ObservableViewMvc<QuestionsListItemViewMvc.Listener> {
    public interface Listener{
        void onQuestionClicked(Question question);
    }
    void bindQuestion(Question question);
}
