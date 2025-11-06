package com.example.mvc.screens.questionslist;

import com.example.mvc.questions.FetchQuestionListUseCase;
import com.example.mvc.questions.Question;
import com.example.mvc.screens.common.MessagesDisplayer;
import com.example.mvc.screens.common.ScreensNavigator;

import java.util.List;

public class QuestionsListController implements QuestionsListViewMvc.Listener, FetchQuestionListUseCase.Listener  {
    private final FetchQuestionListUseCase fetchQuestionListUseCase;
    private final ScreensNavigator mScreensNavigator;
    private final MessagesDisplayer mMessagesDisplayer;
    private QuestionsListViewMvc mViewMvc;

    public QuestionsListController(FetchQuestionListUseCase fetchQuestionListUseCase, ScreensNavigator mScreensNavigator, MessagesDisplayer mMessagesDisplayer) {
        this.fetchQuestionListUseCase = fetchQuestionListUseCase;
        this.mScreensNavigator = mScreensNavigator;
        this.mMessagesDisplayer = mMessagesDisplayer;
    }

    public void bindView(QuestionsListViewMvc mViewMvc){
        this.mViewMvc = mViewMvc;
    }

    public void onStart(){
        mViewMvc.registerListener(this);
        fetchQuestionListUseCase.registerListener(this);
        mViewMvc.showProgressIndication();
        fetchQuestionListUseCase.fetchQuestionAndNotify();
    }
    public void onStop(){
        mViewMvc.unregisterListener(this);
        fetchQuestionListUseCase.unregisterListener(this);
    }

    @Override
    public void onQuestionClicked(Question question) {
        mScreensNavigator.toDialogDetails(question.getId());
    }

    @Override
    public void onQuestionFetchFailed() {
        mMessagesDisplayer.showUseCaseError();
        mViewMvc.hideProgressIndication();
    }

    @Override
    public void onQuestionFetched(List<Question> questions) {
        mViewMvc.bindQuestions(questions);
        mViewMvc.hideProgressIndication();
    }
}
