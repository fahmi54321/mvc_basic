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

    public interface LocationRequestListener{
        void onLocationRequestClicked();
    }

    public interface CameraListener{
        void onCameraClicked();
    }

    public interface MediaListener{
        void onMediaClicked();
    }

    private final TextView mTxtTitle;
    private final ImageButton mBtnBack;
    private final ImageButton mBtnHamburger;
    private final ImageButton mBtnLocationRequest;
    private final ImageButton mBtnCamera;
    private final ImageButton mBtnMedia;

    private NavigateUpClickListener mNavigateUpClickListener;
    private HamburgerClicklistener mHamburgerClicklistener;

    private LocationRequestListener mLocationRequestListener;
    private CameraListener mCameraListener;
    private MediaListener mMediaListener;

    public ToolbarViewMvc(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        setRootView(layoutInflater.inflate(R.layout.layout_toolbar, viewGroup, false));
        mTxtTitle = findViewById(R.id.txt_toolbar_title);
        mBtnBack = findViewById(R.id.btn_back);
        mBtnHamburger = findViewById(R.id.btn_hamburger);
        mBtnLocationRequest = findViewById(R.id.btn_location);
        mBtnCamera = findViewById(R.id.btn_camera);
        mBtnMedia = findViewById(R.id.btn_media);
        mBtnBack.setOnClickListener(view -> mNavigateUpClickListener.onNavigateUpClicked());
        mBtnHamburger.setOnClickListener(v -> mHamburgerClicklistener.onHamburgerClicked());
        mBtnLocationRequest.setOnClickListener(v -> mLocationRequestListener.onLocationRequestClicked());
        mBtnCamera.setOnClickListener(v -> mCameraListener.onCameraClicked());
        mBtnMedia.setOnClickListener(v -> mMediaListener.onMediaClicked());
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

    public void enableLocationRequestButtonAndListen(LocationRequestListener listener) {
        mLocationRequestListener = listener;
        mBtnLocationRequest.setVisibility(View.VISIBLE);
    }

    public void enableCameraButtonAndListen(CameraListener listener) {
        mCameraListener = listener;
        mBtnCamera.setVisibility(View.VISIBLE);
    }

    public void enableMediaButtonAndListen(MediaListener listener) {
        mMediaListener = listener;
        mBtnMedia.setVisibility(View.VISIBLE);
    }
}
