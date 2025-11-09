package com.example.mvc.screens.questiondetails;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.mvc.R;
import com.example.mvc.questions.QuestionDetails;
import com.example.mvc.screens.common.ViewMvcFactory;
import com.example.mvc.screens.common.navdrawer.BaseNavDrawerViewMvc;
import com.example.mvc.screens.common.navdrawer.DrawerItems;
import com.example.mvc.screens.common.toolbar.ToolbarViewMvc;
import com.example.mvc.screens.common.views.BaseObservableViewMvc;
import com.example.mvc.screens.common.views.BaseViewMvc;

public class QuestionDetailsViewMvcImpl extends BaseNavDrawerViewMvc<QuestionDetailsViewMvc.Listener> implements QuestionDetailsViewMvc {

    private final TextView mTxtQuestionTitle;
    private final TextView mTxtQuestionBody;
    private final ProgressBar mProgressBar;

    private final Toolbar mToolbar;

    private final ToolbarViewMvc mToolbarViewMvc;

    public QuestionDetailsViewMvcImpl(LayoutInflater inflater, ViewGroup container, ViewMvcFactory viewMvcFactory) {
        super(inflater,container);
        setRootView(inflater.inflate(R.layout.layout_questions_details, container, false));

        mTxtQuestionTitle = findViewById(R.id.txt_question_title);
        mTxtQuestionBody = findViewById(R.id.txt_question_body);
        mProgressBar = findViewById(R.id.progress);
        mToolbar = findViewById(R.id.toolbar);

        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(mToolbar);
        initToolbar();
    }

    private void initToolbar() {
        mToolbar.addView(mToolbarViewMvc.getRootView());
        mToolbarViewMvc.setTitle("Details");
        mToolbarViewMvc.enableUpButtonAndListen(() -> {
            for (Listener listener : getListeners()) {
                listener.onNavigateUpClicked();
            }
        });
    }

    @Override
    public void bindQuestion(QuestionDetails question) {
        String questionTitle = question.getTitle();
        String questionBody = question.getBody();

        mTxtQuestionTitle.setText(Html.fromHtml(questionTitle, Html.FROM_HTML_MODE_LEGACY));
        mTxtQuestionBody.setText(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY));
    }

    @Override
    public void showProgressIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDrawerItemClicked(DrawerItems item) {
        for (Listener listener : getListeners()) {
            listener.onDrawerItemClicked(item);
        }

    }
}
