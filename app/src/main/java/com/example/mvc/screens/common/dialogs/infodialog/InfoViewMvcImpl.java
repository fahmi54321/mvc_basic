package com.example.mvc.screens.common.dialogs.infodialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.example.mvc.R;
import com.example.mvc.screens.common.views.BaseObservableViewMvc;

public class InfoViewMvcImpl extends BaseObservableViewMvc<InfoViewMvc.Listener> implements InfoViewMvc {

    private TextView mTxtTitle;
    private TextView mTxtMessage;
    private AppCompatButton mBtnPositive;

    public InfoViewMvcImpl(LayoutInflater layoutInflater, ViewGroup viewGroup) {

        setRootView(layoutInflater.inflate(R.layout.dialog_info, viewGroup, false));

        mTxtTitle = findViewById(R.id.txt_title);
        mTxtMessage = findViewById(R.id.txt_message);
        mBtnPositive = findViewById(R.id.btn_positive);

        mBtnPositive.setOnClickListener(v -> {
            for(Listener listener: getListeners()){
                listener.onPositiveButtonClicked();
            }
        });
    }

    @Override
    public void setTitle(String title) {
        mTxtTitle.setText(title);
    }

    @Override
    public void setMessage(String message) {
        mTxtMessage.setText(message);
    }

    @Override
    public void setPositiveButtonCaption(String caption) {
        mBtnPositive.setText(caption);
    }
}
