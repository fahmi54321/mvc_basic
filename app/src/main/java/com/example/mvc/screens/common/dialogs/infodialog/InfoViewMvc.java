package com.example.mvc.screens.common.dialogs.infodialog;

import com.example.mvc.screens.common.views.ObservableViewMvc;

public interface InfoViewMvc extends ObservableViewMvc<InfoViewMvc.Listener> {
    public interface Listener{
        void onPositiveButtonClicked();
    }

    void setTitle(String title);
    void setMessage(String message);
    void setPositiveButtonCaption(String caption);
}
