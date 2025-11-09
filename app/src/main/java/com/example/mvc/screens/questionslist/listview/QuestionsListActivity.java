package com.example.mvc.screens.questionslist.listview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvc.R;
import com.example.mvc.screens.common.controller.BackPressedListener;
import com.example.mvc.screens.common.controller.BaseActivity;

public class QuestionsListActivity extends BaseActivity {

    public static void startClearTop(Context context) {
        Intent intent = new Intent(context, QuestionsListActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    private BackPressedListener mBackPressedListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_content_frame);
        QuestionsListFragment fragment;
        if(savedInstanceState == null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragment = new QuestionsListFragment();
            fragmentTransaction.add(R.id.frame_content, fragment);
            fragmentTransaction.commit();
        }else{
            fragment = (QuestionsListFragment) getSupportFragmentManager().findFragmentById(R.id.frame_content);
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
