package com.example.mvc.screens.questiondetails;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;

import com.example.mvc.common.ManifestPermission;
import com.example.mvc.common.permissions.CameraHelper;
import com.example.mvc.common.permissions.ImageHelper;
import com.example.mvc.common.permissions.PermissionsHelper;
import com.example.mvc.questions.FetchQuestionDetailsUseCase;
import com.example.mvc.questions.QuestionDetails;
import com.example.mvc.screens.common.dialogs.DialogsEventBus;
import com.example.mvc.screens.common.dialogs.DialogsManager;
import com.example.mvc.screens.common.dialogs.promptdialog.PromptDialogEvent;
import com.example.mvc.screens.common.screensnavigator.ScreensNavigator;
import com.example.mvc.screens.common.toasthelper.ToastHelper;

import java.io.Serializable;

public class QuestionDetailsController implements FetchQuestionDetailsUseCase.Listener, QuestionDetailsViewMvc.Listener, DialogsEventBus.Listener, PermissionsHelper.Listener, CameraHelper.Listener, ImageHelper.Listener {

    private final FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase;
    private final ToastHelper toastHelper;
    private final ScreensNavigator mScreensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;
    private final PermissionsHelper permissionsHelper;
    private final CameraHelper cameraHelper;
    private final ImageHelper imageHelper;
    private String questionId;
    private QuestionDetailsViewMvc mViewMvc;

    private enum ScreenState{
        IDLE, DETAILS_SHOWN, NETWORK_ERROR
    }

    private ScreenState mScreenState = ScreenState.IDLE;

    private static final String DIALOG_ID_NETWORK_ERROR = "DIALOG_ID_NETWORK_ERROR";
    public static final String SAVED_STATE_SCREEN_STATE = "SAVED_STATE_SCREEN_STATE";

    private ActivityResultLauncher<String> permissionLauncher;

    private ActivityResultLauncher<Intent> cameraLaunchers;

    private ActivityResultLauncher<PickVisualMediaRequest> imageLaunchers;

    public QuestionDetailsController(FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase, ToastHelper toastHelper, ScreensNavigator mScreensNavigator, DialogsManager dialogsManager, DialogsEventBus dialogsEventBus, PermissionsHelper permissionsHelper, CameraHelper cameraHelper, ImageHelper imageHelper) {
        this.fetchQuestionDetailsUseCase = fetchQuestionDetailsUseCase;
        this.toastHelper = toastHelper;
        this.mScreensNavigator = mScreensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
        this.permissionsHelper = permissionsHelper;
        this.cameraHelper = cameraHelper;
        this.imageHelper = imageHelper;
    }

    public void setPermissionAccessFineLocation(ActivityResultLauncher<String> permissionLauncher){
        this.permissionLauncher = permissionLauncher;
    }

    public void setCameraLaunchers(ActivityResultLauncher<Intent> cameraLaunchers){
        this.cameraLaunchers = cameraLaunchers;
    }

    public void setImageLaunchers(ActivityResultLauncher<PickVisualMediaRequest> imageLaunchers){
        this.imageLaunchers = imageLaunchers;
    }


    public void setQuestionId(String id){
        questionId = id;
    }
    public void onStart(){
        mViewMvc.registerListener(this);
        fetchQuestionDetailsUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);
        permissionsHelper.registerListener(this);
        mViewMvc.showProgressIndication();
        cameraHelper.registerListener(this);
        imageHelper.registerListener(this);
        if(mScreenState != ScreenState.NETWORK_ERROR) {
            fetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(questionId);
        }
    }
    public void onStop(){
        mViewMvc.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
        permissionsHelper.unregisterListener(this);
        cameraHelper.unregisterListener(this);
        imageHelper.unregisterListener(this);
        fetchQuestionDetailsUseCase.unregisterListener(this);
    }

    public SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public void bindView(QuestionDetailsViewMvc mViewMvc){
        this.mViewMvc = mViewMvc;
    }

    @Override
    public void onQuestionDetailsFetched(QuestionDetails questionDetails) {
        mScreenState = ScreenState.DETAILS_SHOWN;
        mViewMvc.hideProgressIndication();
        mViewMvc.bindQuestion(questionDetails);
    }

    @Override
    public void onQuestionDetailsFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        mViewMvc.hideProgressIndication();
        dialogsManager.showUseCaseErrorDialog(DIALOG_ID_NETWORK_ERROR);
    }

    @Override
    public void onNavigateUpClicked() {
        mScreensNavigator.onBackPressed();
    }

    @Override
    public void onLocationRequestClicked() {
        if (permissionsHelper.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            dialogsManager.showPermissionGrantedDialog(null);
        } else {
            permissionsHelper.requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, permissionLauncher);
        }
    }

    @Override
    public void onDialogEvent(Object event) {
        if(event instanceof PromptDialogEvent){
            switch (((PromptDialogEvent) event).getClickedButton()){
                case POSITIVE:
                    mScreenState = ScreenState.IDLE;
                    fetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(questionId);
                    break;
                case NEGATIVE:
                    mScreenState = ScreenState.IDLE;
                    break;
            }
        }
    }

    @Override
    public void onPermissionGranted(String permission) {
        if(permission.equals(ManifestPermission.PERMISSION_ACCESS_FINE_LOCATION)) {
            dialogsManager.showPermissionGrantedDialog(null);
        }else if(permission.equals(ManifestPermission.PERMISSION_CAMERA)) {
            dialogsManager.showPermissionGrantedDialog(null);
        }else if(permission.equals(ManifestPermission.PERMISSION_READ_EXTERNAL_STORAGE)) {
            dialogsManager.showPermissionGrantedDialog(null);
        }
    }

    @Override
    public void onPermissionDeclined(String permission) {
        if(permission.equals(ManifestPermission.PERMISSION_ACCESS_FINE_LOCATION)) {
            dialogsManager.showDeclinedDialog(null);
        }else if(permission.equals(ManifestPermission.PERMISSION_CAMERA)) {
            dialogsManager.showDeclinedDialog(null);
        }else if(permission.equals(ManifestPermission.PERMISSION_READ_EXTERNAL_STORAGE)) {
            dialogsManager.showDeclinedDialog(null);
        }
    }

    @Override
    public void onPermissionDeclinedDontAskAgain(String permission) {
        if(permission.equals(ManifestPermission.PERMISSION_ACCESS_FINE_LOCATION)) {
            dialogsManager.showPermissionDeclinedCantAskMoreDialog(null);
        }else if(permission.equals(ManifestPermission.PERMISSION_CAMERA)) {
            dialogsManager.showPermissionDeclinedCantAskMoreDialog(null);
        }else if(permission.equals(ManifestPermission.PERMISSION_READ_EXTERNAL_STORAGE)) {
            dialogsManager.showPermissionDeclinedCantAskMoreDialog(null);
        }
    }

    @Override
    public void onImagePicked(Uri imageUri) {
        toastHelper.showMsgRandom(imageUri.toString());
    }

    @Override
    public void onImagePickCanceled() {
        toastHelper.showDataNotFound();
    }

    @Override
    public void onPermissionReadExternalStorageNeeded() {
        permissionsHelper.requestPermission(
                ManifestPermission.PERMISSION_READ_EXTERNAL_STORAGE,
                permissionLauncher
        );
    }

    @Override
    public void onPermissionCameraNeeded() {
        permissionsHelper.requestPermission(
                ManifestPermission.PERMISSION_CAMERA,
                permissionLauncher
        );
    }

    @Override
    public void onCameraClicked() {
        cameraHelper.openCamera(cameraLaunchers);
    }

    @Override
    public void onMediaClicked() {
        imageHelper.openMedia(imageLaunchers);
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }
}
