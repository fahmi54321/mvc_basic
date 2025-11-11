package com.example.mvc.screens.common.main;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.example.mvc.screens.common.controller.BaseActivity;
import com.example.mvc.screens.common.fragmentframehelper.FragmentFrameWrapper;
import com.example.mvc.screens.common.navdrawer.NavDrawerHelper;
import com.example.mvc.screens.common.navdrawer.NavDrawerViewMvc;
import com.example.mvc.screens.common.screensnavigator.ScreensNavigator;

public class MainActivity extends BaseActivity implements
        FragmentFrameWrapper,
        NavDrawerViewMvc.Listener,
        NavDrawerHelper {
    private ScreensNavigator mScreensNavigator;
    private NavDrawerViewMvc mViewMvc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScreensNavigator = getCompositionRoot().getScreensNavigator();
        mViewMvc = getCompositionRoot().getViewMvcFactory().getNavDrawerViewMvc(null);
        setContentView(mViewMvc.getRootView());
        if (savedInstanceState == null) {
            mScreensNavigator.toQuestionsList();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
    }

    @Override
    public void onQuestionListClicked() {
        mScreensNavigator.toQuestionsList();
    }

    @Override
    public void onBackPressed() {
        if(isDrawerOpen()){
            closeDrawer();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public FrameLayout getFragmentFrame() {
        return mViewMvc.getFragmentFrame();
    }

    @Override
    public void openDrawer() {
        mViewMvc.openDrawer();
    }

    @Override
    public void closeDrawer() {
        mViewMvc.closeDrawer();
    }

    @Override
    public boolean isDrawerOpen() {
        return mViewMvc.isDrawerOpen();
    }
}
