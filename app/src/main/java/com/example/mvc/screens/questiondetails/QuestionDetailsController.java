package com.example.mvc.screens.questiondetails;

import com.example.mvc.questions.FetchQuestionDetailsUseCase;
import com.example.mvc.questions.QuestionDetails;
import com.example.mvc.screens.common.dialogs.DialogsEventBus;
import com.example.mvc.screens.common.dialogs.DialogsManager;
import com.example.mvc.screens.common.dialogs.promptdialog.PromptDialogEvent;
import com.example.mvc.screens.common.screensnavigator.ScreensNavigator;
import com.example.mvc.screens.common.toasthelper.ToastHelper;

import java.io.Serializable;

public class QuestionDetailsController implements FetchQuestionDetailsUseCase.Listener, QuestionDetailsViewMvc.Listener, DialogsEventBus.Listener {

    private final FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase;
    private final ToastHelper toastHelper;
    private final ScreensNavigator mScreensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;
    private String questionId;
    private QuestionDetailsViewMvc mViewMvc;

    private enum ScreenState{
        IDLE, DETAILS_SHOWN, NETWORK_ERROR
    }

    private ScreenState mScreenState = ScreenState.IDLE;

    private static final String DIALOG_ID_NETWORK_ERROR = "DIALOG_ID_NETWORK_ERROR";
    public static final String SAVED_STATE_SCREEN_STATE = "SAVED_STATE_SCREEN_STATE";

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
        if(mScreenState != ScreenState.NETWORK_ERROR) {
            fetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(questionId);
        }
    }
    public void onStop(){
        mViewMvc.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
        fetchQuestionDetailsUseCase.unregisterListener(this);
    }

    public SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public void bindView(QuestionDetailsViewMvc mViewMvc){
        this.mViewMvc = mViewMvc;
    }

    @Override
    public void onQuestionDetailsFetched(QuestionDetails questionDetails) {
        mScreenState = ScreenState.DETAILS_SHOWN;
        mViewMvc.hideProgressIndication();
        mViewMvc.bindQuestion(questionDetails);
    }

    @Override
    public void onQuestionDetailsFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        mViewMvc.hideProgressIndication();
        dialogsManager.showUseCaseErrorDialog(DIALOG_ID_NETWORK_ERROR);
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
                    mScreenState = ScreenState.IDLE;
                    fetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(questionId);
                    break;
                case NEGATIVE:
                    mScreenState = ScreenState.IDLE;
                    break;
            }
        }
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }
}
