package com.example.mvc.screens.questionslist.recycler;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mvc.R;
import com.example.mvc.questions.FetchQuestionListUseCase;
import com.example.mvc.questions.Question;
import com.example.mvc.screens.common.BaseActivity;
import com.example.mvc.screens.questiondetails.QuestionDetailsActivity;
import com.example.mvc.screens.questionslist.QuestionsListViewMvc;

import java.util.List;

public class QuestionsRecyclerListActivity extends BaseActivity implements QuestionsListViewMvc.Listener, FetchQuestionListUseCase.Listener {
    private QuestionsListViewMvc mViewMvc;
    private FetchQuestionListUseCase fetchQuestionListUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsRecyclerViewMvc(null);
        mViewMvc.registerListener(this);

        fetchQuestionListUseCase = getCompositionRoot().getFetchQuestionListUseCase();
        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchQuestionListUseCase.registerListener(this);
        mViewMvc.showProgressIndication();
        fetchQuestionListUseCase.fetchQuestionAndNotify();
    }

    @Override
    protected void onStop() {
        super.onStop();
        fetchQuestionListUseCase.unregisterListener(this);
    }

    @Override
    public void onQuestionClicked(Question question) {
        QuestionDetailsActivity.start(this,question.getId());
    }

    @Override
    public void onQuestionFetchFailed() {
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();
        mViewMvc.hideProgressIndication();
    }

    @Override
    public void onQuestionFetched(List<Question> questions) {
        mViewMvc.bindQuestions(questions);
        mViewMvc.hideProgressIndication();
    }
}
