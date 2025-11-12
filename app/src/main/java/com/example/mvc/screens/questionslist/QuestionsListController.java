package com.example.mvc.screens.questionslist;

import com.example.mvc.questions.FetchQuestionListUseCase;
import com.example.mvc.questions.Question;
import com.example.mvc.screens.common.dialogs.DialogsEventBus;
import com.example.mvc.screens.common.dialogs.DialogsManager;
import com.example.mvc.screens.common.dialogs.promptdialog.PromptDialogEvent;
import com.example.mvc.screens.common.toasthelper.ToastHelper;
import com.example.mvc.screens.common.screensnavigator.ScreensNavigator;
import com.example.mvc.screens.questiondetails.QuestionDetailsController;
import com.example.mvc.screens.questionslist.questionslistitem.QuestionsListViewMvc;

import java.io.Serializable;
import java.util.List;

public class QuestionsListController implements QuestionsListViewMvc.Listener, FetchQuestionListUseCase.Listener, DialogsEventBus.Listener {
    private final FetchQuestionListUseCase fetchQuestionListUseCase;
    private final ScreensNavigator mScreensNavigator;
    private final ToastHelper mToastHelper;
    private final DialogsManager mDialogsManager;

    private final DialogsEventBus mDialogsEventBus;

    private QuestionsListViewMvc mViewMvc;

    private enum ScreenState{
        IDLE, LIST_SHOWN, NETWORK_ERROR
    }

    private ScreenState mScreenState = ScreenState.IDLE;

    private static final String DIALOG_ID_NETWORK_ERROR = "DIALOG_ID_NETWORK_ERROR";
    public static final String SAVED_STATE_SCREEN_STATE = "SAVED_STATE_SCREEN_STATE";

    public QuestionsListController(FetchQuestionListUseCase fetchQuestionListUseCase, ScreensNavigator mScreensNavigator, ToastHelper mToastHelper, DialogsManager mDialogsManager, DialogsEventBus mDialogsEventBus) {
        this.fetchQuestionListUseCase = fetchQuestionListUseCase;
        this.mScreensNavigator = mScreensNavigator;
        this.mToastHelper = mToastHelper;
        this.mDialogsManager = mDialogsManager;
        this.mDialogsEventBus = mDialogsEventBus;
    }

    public SavedState getSavedState(){
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState){
        mScreenState = savedState.mScreenState;
    }

    public void bindView(QuestionsListViewMvc mViewMvc){
        this.mViewMvc = mViewMvc;
    }

    public void onStart(){
        mViewMvc.registerListener(this);
        mDialogsEventBus.registerListener(this);
        fetchQuestionListUseCase.registerListener(this);
        mViewMvc.showProgressIndication();
        if(mScreenState != ScreenState.NETWORK_ERROR){
            fetchQuestionListUseCase.fetchQuestionAndNotify();
        }
    }
    public void onStop(){
        mViewMvc.unregisterListener(this);
        mDialogsEventBus.unregisterListener(this);
        fetchQuestionListUseCase.unregisterListener(this);
    }

    @Override
    public void onQuestionClicked(Question question) {
        mScreensNavigator.toQuestionDetails(question.getId());
    }

    @Override
    public void onQuestionFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        mDialogsManager.showUseCaseErrorDialog(DIALOG_ID_NETWORK_ERROR);
        mViewMvc.hideProgressIndication();
    }

    @Override
    public void onQuestionFetched(List<Question> questions) {
        mScreenState = ScreenState.LIST_SHOWN;
        mViewMvc.bindQuestions(questions);
        mViewMvc.hideProgressIndication();
    }

    @Override
    public void onDialogEvent(Object event) {
        if(event instanceof PromptDialogEvent){
            switch (((PromptDialogEvent) event).getClickedButton()){
                case POSITIVE:
                    mScreenState = ScreenState.IDLE;
                    fetchQuestionListUseCase.fetchQuestionAndNotify();
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
