package com.example.mvc.screens.questiondetails;

import com.example.mvc.questions.FetchQuestionDetailsUseCase;
import com.example.mvc.questions.QuestionDetails;
import com.example.mvc.screens.common.dialogs.DialogsEventBus;
import com.example.mvc.screens.common.dialogs.DialogsManager;
import com.example.mvc.screens.common.dialogs.promptdialog.PromptDialogEvent;
import com.example.mvc.screens.common.screensnavigator.ScreensNavigator;
import com.example.mvc.screens.common.toasthelper.ToastHelper;

public class QuestionDetailsController implements FetchQuestionDetailsUseCase.Listener, QuestionDetailsViewMvc.Listener, DialogsEventBus.Listener {

    private final FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase;
    private final ToastHelper toastHelper;
    private final ScreensNavigator mScreensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;
    private String questionId;
    private QuestionDetailsViewMvc mViewMvc;

    public QuestionDetailsController(FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase, ToastHelper toastHelper, ScreensNavigator mScreensNavigator, DialogsManager dialogsManager, DialogsEventBus dialogsEventBus) {
        this.fetchQuestionDetailsUseCase = fetchQuestionDetailsUseCase;
        this.toastHelper = toastHelper;
        this.mScreensNavigator = mScreensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }


    public void setQuestionId(String id){
        questionId = id;
    }
    public void onStart(){
        mViewMvc.registerListener(this);
        fetchQuestionDetailsUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);
        mViewMvc.showProgressIndication();
        fetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(questionId);
    }
    public void onStop(){
        mViewMvc.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
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
        dialogsManager.showUseCaseErrorDialog(null);
    }

    @Override
    public void onNavigateUpClicked() {
        mScreensNavigator.onBackPressed();
    }

    @Override
    public void onDialogEvent(Object event) {
        if(event instanceof PromptDialogEvent){
            switch (((PromptDialogEvent) event).getClickedButton()){
                case POSITIVE:
                    fetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(questionId);
                    break;
                case NEGATIVE:
                    break;
            }
        }
    }
}
