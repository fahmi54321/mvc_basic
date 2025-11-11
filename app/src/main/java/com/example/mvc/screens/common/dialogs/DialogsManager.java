package com.example.mvc.screens.common.dialogs;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.mvc.screens.common.dialogs.infodialog.InfoDialog;

public class DialogsManager {
    private final FragmentManager fragmentManager;

    public DialogsManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void showUseCaseErrorDialog(String tag){
        DialogFragment dialogFragment = InfoDialog.newInfoDialog(
                "Opps",
                "error",
                "Ok"
        );
        dialogFragment.show(fragmentManager, tag);
    }
}
