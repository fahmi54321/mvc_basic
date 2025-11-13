package com.example.mvc.screens.common.dialogs.infodialog;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.mvc.screens.common.dialogs.BaseDialog;

public class InfoDialog extends BaseDialog implements InfoViewMvc.Listener {

    protected static final String ARG_TITLE = "ARG_TITLE";
    protected static final String ARG_MESSAGE = "ARG_MESSAGE";
    protected static final String ARG_BUTTON_CAPTION = "ARG_BUTTON_CAPTION";

    public static InfoDialog newInfoDialog(String title, String message, String buttonCaption) {
        InfoDialog infoDialog = new InfoDialog();
        Bundle args = new Bundle(3);
        args.putString(ARG_TITLE, title);
        args.putString(ARG_MESSAGE, message);
        args.putString(ARG_BUTTON_CAPTION, buttonCaption);
        infoDialog.setArguments(args);
        return infoDialog;
    }

    private InfoViewMvc mViewMvc;

    @NonNull
    @Override
    public final Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() == null) {
            throw new IllegalStateException("arguments mustn't be null");
        }

        mViewMvc = getCompositionRoot().getViewMvcFactory().getInfoViewMvc(null);

        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(mViewMvc.getRootView());

        mViewMvc.setTitle(getArguments().getString(ARG_TITLE));
        mViewMvc.setMessage(getArguments().getString(ARG_MESSAGE));
        mViewMvc.setPositiveButtonCaption(getArguments().getString(ARG_BUTTON_CAPTION));

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
    }

    @Override
    public void onPositiveButtonClicked() {
        dismiss();
    }
}
