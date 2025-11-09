package com.example.mvc.screens.questionslist;

import com.example.mvc.questions.FetchQuestionListUseCase;
import com.example.mvc.questions.Question;
import com.example.mvc.screens.common.toasthelper.ToastHelper;
import com.example.mvc.screens.common.screensnavigator.ScreensNavigator;
import com.example.mvc.screens.questionslist.questionslistitem.QuestionsListViewMvc;

import java.util.List;

public class QuestionsListController implements QuestionsListViewMvc.Listener, FetchQuestionListUseCase.Listener  {
    private final FetchQuestionListUseCase fetchQuestionListUseCase;
    private final ScreensNavigator mScreensNavigator;
    private final ToastHelper mToastHelper;
    private QuestionsListViewMvc mViewMvc;

    public QuestionsListController(FetchQuestionListUseCase fetchQuestionListUseCase, ScreensNavigator mScreensNavigator, ToastHelper mToastHelper) {
        this.fetchQuestionListUseCase = fetchQuestionListUseCase;
        this.mScreensNavigator = mScreensNavigator;
        this.mToastHelper = mToastHelper;
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
    public void onQuestionsListClicked() {
        // this is the questions list screen, no-op
    }

    @Override
    public void onQuestionFetchFailed() {
        mToastHelper.showUseCaseError();
        mViewMvc.hideProgressIndication();
    }

    @Override
    public void onQuestionFetched(List<Question> questions) {
        mViewMvc.bindQuestions(questions);
        mViewMvc.hideProgressIndication();
    }

    public boolean onBackPressed() {
        if(mViewMvc.isDrawerOpen()){
            mViewMvc.closeDrawer();
            return true;
        }else{
            return false;
        }
    }
}
