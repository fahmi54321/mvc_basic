package com.example.mvc.screens.questiondetails;

import com.example.mvc.questions.FetchQuestionDetailsUseCase;
import com.example.mvc.questions.QuestionDetails;
import com.example.mvc.screens.common.navdrawer.DrawerItems;
import com.example.mvc.screens.common.screensnavigator.ScreensNavigator;
import com.example.mvc.screens.common.toasthelper.ToastHelper;

public class QuestionDetailsController implements FetchQuestionDetailsUseCase.Listener, QuestionDetailsViewMvc.Listener {

    private final FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase;
    private final ToastHelper toastHelper;

    private final ScreensNavigator mScreensNavigator;

    private QuestionDetailsViewMvc mViewMvc;


    public QuestionDetailsController(FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase, ToastHelper toastHelper, ScreensNavigator mScreensNavigator) {
        this.fetchQuestionDetailsUseCase = fetchQuestionDetailsUseCase;
        this.toastHelper = toastHelper;
        this.mScreensNavigator = mScreensNavigator;
    }

    public void onStart(String id){
        mViewMvc.registerListener(this);
        fetchQuestionDetailsUseCase.registerListener(this);
        mViewMvc.showProgressIndication();
        fetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(id);
    }
    public void onStop(){
        mViewMvc.unregisterListener(this);
        fetchQuestionDetailsUseCase.unregisterListener(this);
    }

    public void bindView(QuestionDetailsViewMvc mViewMvc){
        this.mViewMvc = mViewMvc;
    }

    @Override
    public void onQuestionDetailsFetched(QuestionDetails questionDetails) {
        mViewMvc.hideProgressIndication();
        mViewMvc.bindQuestion(questionDetails);
    }

    @Override
    public void onQuestionDetailsFetchFailed() {
        mViewMvc.hideProgressIndication();
        toastHelper.showUseCaseError();
    }

    @Override
    public void onNavigateUpClicked() {
        mScreensNavigator.onBackPressed();
    }

    public void onDrawerItemClicked(DrawerItems item) {
        switch (item) {
            case QUESTIONS_LIST:
                mScreensNavigator.toQuestionsListClearTop();
        }
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
