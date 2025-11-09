package com.example.mvc.screens.questionslist.questionslistitem;

import com.example.mvc.questions.Question;
import com.example.mvc.screens.common.navdrawer.NavDrawerViewMvc;
import com.example.mvc.screens.common.views.ObservableViewMvc;

import java.util.List;

public interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener>, NavDrawerViewMvc {

    public interface Listener {
        void onQuestionClicked(Question question);

        void onQuestionsListClicked();
    }
    void bindQuestions(List<Question> questions);

    void showProgressIndication();

    void hideProgressIndication();
}
