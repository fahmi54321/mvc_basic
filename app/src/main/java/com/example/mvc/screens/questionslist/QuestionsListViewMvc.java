package com.example.mvc.screens.questionslist;

import android.view.View;

import com.example.mvc.questions.Question;

import java.util.List;

public interface QuestionsListViewMvc {

    public interface Listener {
        void onQuestionClicked(Question question);
    }
    void registerListener(Listener listener);

    void unregisterListener(Listener listener);

    View getRootView();

    void onQuestionClicked(Question question);

    void bindQuestions(List<Question> questions);
}
