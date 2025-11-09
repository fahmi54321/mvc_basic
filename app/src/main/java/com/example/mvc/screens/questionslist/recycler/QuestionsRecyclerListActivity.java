package com.example.mvc.screens.questionslist.recycler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvc.R;
import com.example.mvc.questions.FetchQuestionListUseCase;
import com.example.mvc.questions.Question;
import com.example.mvc.screens.common.controller.BackPressedListener;
import com.example.mvc.screens.common.controller.BaseActivity;
import com.example.mvc.screens.questiondetails.QuestionDetailsActivity;
import com.example.mvc.screens.questionslist.QuestionsListController;
import com.example.mvc.screens.questionslist.listview.QuestionsListActivity;
import com.example.mvc.screens.questionslist.listview.QuestionsListFragment;
import com.example.mvc.screens.questionslist.questionslistitem.QuestionsListViewMvc;

import java.util.List;

public class QuestionsRecyclerListActivity extends BaseActivity{
    public static void startClearTop(Context context) {
        Intent intent = new Intent(context, QuestionsRecyclerListActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    private BackPressedListener mBackPressedListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_content_frame);
        QuestionsRecyclerListFragment fragment;
        if(savedInstanceState == null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragment = new QuestionsRecyclerListFragment();
            fragmentTransaction.add(R.id.frame_content, fragment);
            fragmentTransaction.commit();
        }else{
            fragment = (QuestionsRecyclerListFragment) getSupportFragmentManager().findFragmentById(R.id.frame_content);
        }

        mBackPressedListener = fragment;
    }

    @Override
    public void onBackPressed() {
        if(!mBackPressedListener.onBackPressed()){
            super.onBackPressed();
        }
    }
}
