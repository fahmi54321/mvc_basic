package com.example.mvc.screens.questionslist;

import com.example.mvc.questions.Question;
import com.example.mvc.screens.common.ObservableViewMvc;
import com.example.mvc.screens.common.ViewMvc;

import java.util.List;

public interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener> {

    public interface Listener {
        void onQuestionClicked(Question question);
    }
    void bindQuestions(List<Question> questions);
}
