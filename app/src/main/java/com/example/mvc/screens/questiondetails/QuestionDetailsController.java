package com.example.mvc.screens.questiondetails;

import com.example.mvc.questions.FetchQuestionDetailsUseCase;
import com.example.mvc.questions.QuestionDetails;
import com.example.mvc.screens.common.MessagesDisplayer;

public class QuestionDetailsController implements FetchQuestionDetailsUseCase.Listener {

    private final FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase;
    private final MessagesDisplayer messagesDisplayer;

    private QuestionDetailsViewMvc mViewMvc;

    public QuestionDetailsController(FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase, MessagesDisplayer messagesDisplayer) {
        this.fetchQuestionDetailsUseCase = fetchQuestionDetailsUseCase;
        this.messagesDisplayer = messagesDisplayer;
    }

    public void onStart(String id){
        fetchQuestionDetailsUseCase.registerListener(this);
        mViewMvc.showProgressIndication();
        fetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(id);
    }
    public void onStop(){
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
        messagesDisplayer.showUseCaseError();
    }
}
