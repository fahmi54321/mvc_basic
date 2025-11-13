package com.example.mvc.screens.common.dialogs.promptdialog;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.example.mvc.R;
import com.example.mvc.screens.common.views.BaseObservableViewMvc;

public class PromptViewMvcImpl extends BaseObservableViewMvc<PromptViewMvc.Listener> implements PromptViewMvc {

    private TextView mTxtTitle;
    private TextView mTxtMessage;
    private AppCompatButton mBtnPositive;
    private AppCompatButton mBtnNegative;

    public PromptViewMvcImpl(LayoutInflater layoutInflater, ViewGroup viewGroup) {

        setRootView(layoutInflater.inflate(R.layout.dialog_prompt, viewGroup, false));

        mTxtTitle = findViewById(R.id.txt_title);
        mTxtMessage = findViewById(R.id.txt_message);
        mBtnPositive = findViewById(R.id.btn_positive);
        mBtnNegative = findViewById(R.id.btn_negative);


        mBtnPositive.setOnClickListener(v -> {
            for (Listener listener: getListeners()){
                listener.onPositiveButtonClicked();
            }
        });

        mBtnNegative.setOnClickListener(v -> {
            for (Listener listener: getListeners()){
                listener.onNegativeButtonClicked();
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

    @Override
    public void setNegativeButtonCaption(String caption) {
        mBtnNegative.setText(caption);
    }
}
