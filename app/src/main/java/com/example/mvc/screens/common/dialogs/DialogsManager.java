package com.example.mvc.screens.common.dialogs;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.mvc.screens.common.dialogs.infodialog.InfoDialog;
import com.example.mvc.screens.common.dialogs.promptdialog.PromptDialog;

public class DialogsManager {
    private final FragmentManager fragmentManager;

    public DialogsManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void showUseCaseErrorDialog(@Nullable String tag){
//        DialogFragment dialogFragment = InfoDialog.newInfoDialog(
//                "Opps",
//                "error",
//                "Ok"
//        );
//        dialogFragment.show(fragmentManager, tag);
        DialogFragment dialogFragment = PromptDialog.newPromptDialog(
                "Opss",
                "Error",
                "Retry",
                "Close"
        );
        dialogFragment.show(fragmentManager, tag);
    }

    public @Nullable String getShownDialogTag() {
        for(Fragment fragment: fragmentManager.getFragments()){
            if(fragment instanceof BaseDialog){
                return fragment.getTag();
            }
        }
        return null;
    }
}
