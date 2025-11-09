package com.example.mvc.screens.common.toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mvc.R;
import com.example.mvc.screens.common.views.BaseViewMvc;

public class ToolbarViewMvc extends BaseViewMvc {

    public interface NavigateUpClickListener {
        void onNavigateUpClicked();
    }

    public interface HamburgerClicklistener{
        void onHamburgerClicked();
    }

    private final TextView mTxtTitle;
    private final ImageButton mBtnBack;
    private final ImageButton mBtnHamburger;

    private NavigateUpClickListener mNavigateUpClickListener;
    private HamburgerClicklistener mHamburgerClicklistener;

    public ToolbarViewMvc(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        setRootView(layoutInflater.inflate(R.layout.layout_toolbar, viewGroup, false));
        mTxtTitle = findViewById(R.id.txt_toolbar_title);
        mBtnBack = findViewById(R.id.btn_back);
        mBtnHamburger = findViewById(R.id.btn_hamburger);
        mBtnBack.setOnClickListener(view -> mNavigateUpClickListener.onNavigateUpClicked());
        mBtnHamburger.setOnClickListener(v -> mHamburgerClicklistener.onHamburgerClicked());
    }

    public void setTitle(String title){
        mTxtTitle.setText(title);
    }

    public void enableUpButtonAndListen(NavigateUpClickListener navigateUpClickListener) {
        if(mNavigateUpClickListener != null){
            throw new RuntimeException("hamburger and up shouldn't be shown together");
        }
        mNavigateUpClickListener = navigateUpClickListener;
        mBtnBack.setVisibility(View.VISIBLE);
    }

    public void enableHamburgerButtonAndListen(HamburgerClicklistener hamburgerClicklistener){
        if(mHamburgerClicklistener != null){
            throw new RuntimeException("hamburger and up shouldn't be shown together");
        }
        mHamburgerClicklistener = hamburgerClicklistener;
        mBtnHamburger.setVisibility(View.VISIBLE);
    }
}
