package com.example.mvc.screens.common.main;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvc.R;
import com.example.mvc.screens.common.controller.BackPressDispatcher;
import com.example.mvc.screens.common.controller.BackPressedListener;
import com.example.mvc.screens.common.controller.BaseActivity;
import com.example.mvc.screens.common.controller.FragmentFrameWrapper;
import com.example.mvc.screens.questionslist.listview.QuestionsListFragment;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends BaseActivity implements BackPressDispatcher, FragmentFrameWrapper {

    private final Set<BackPressedListener> mBackPressedListeners = new HashSet<>();

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
        }
    }

    @Override
    public void registerListener(BackPressedListener listener) {
        mBackPressedListeners.add(listener);
    }

    @Override
    public void unregisterListener(BackPressedListener listener) {
        mBackPressedListeners.remove(listener);
    }

    @Override
    public void onBackPressed() {

        boolean isBackPressConsumedByAndListener = false;
        for(BackPressedListener listener: mBackPressedListeners){
            if(listener.onBackPressed()){
                isBackPressConsumedByAndListener = true;
            }
        }
        if(!isBackPressConsumedByAndListener){
            super.onBackPressed();
        }
    }

    @Override
    public FrameLayout getFragmentFrame() {
        return findViewById(R.id.frame_content);
    }
}
