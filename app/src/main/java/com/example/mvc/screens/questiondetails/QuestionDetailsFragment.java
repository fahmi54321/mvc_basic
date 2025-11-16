package com.example.mvc.screens.questiondetails;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mvc.common.ManifestPermission;
import com.example.mvc.common.permissions.CameraHelper;
import com.example.mvc.common.permissions.ImageHelper;
import com.example.mvc.common.permissions.PermissionsHelper;
import com.example.mvc.screens.common.controller.BaseFragment;

public class QuestionDetailsFragment extends BaseFragment{
    public static final String ARG_QUESTION_ID = "ARG_QUESTION_ID";
    private QuestionDetailsController mQuestionDetailsController;

    public static QuestionDetailsFragment newInstance(String questionId){
        Bundle args = new Bundle();
        args.putString(ARG_QUESTION_ID,questionId);
        QuestionDetailsFragment fragment = new QuestionDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestionDetailsController = getCompositionRoot().getQuestionDetailsController();
        CameraHelper cameraHelper = getCompositionRoot().getCameraPicker();
        PermissionsHelper permissionsHelper = getCompositionRoot().getPermissionsHelper();
        ImageHelper imageHelper = getCompositionRoot().getImageHelper();
        ActivityResultLauncher<String> locationPermissionLauncher = permissionsHelper.registerPermission(ManifestPermission.PERMISSION_ACCESS_FINE_LOCATION);
        ActivityResultLauncher<Intent> cameraLaunchers =  cameraHelper.registerLaunchers();
        ActivityResultLauncher<PickVisualMediaRequest> mediaLauncher =  imageHelper.registerLaunchers();
        mQuestionDetailsController.setPermissionAccessFineLocation(locationPermissionLauncher);
        mQuestionDetailsController.setCameraLaunchers(cameraLaunchers);
        mQuestionDetailsController.setImageLaunchers(mediaLauncher);
        mQuestionDetailsController.setQuestionId(getQuestionId());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        QuestionDetailsViewMvc mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(container);

        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        mQuestionDetailsController.bindView(mViewMvc);
        return mViewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mQuestionDetailsController.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        mQuestionDetailsController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(QuestionDetailsController.SAVED_STATE_SCREEN_STATE, mQuestionDetailsController.getSavedState());
    }

    private String getQuestionId() {
        return getArguments().getString(ARG_QUESTION_ID);
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        mQuestionDetailsController.restoreSavedState(
                (QuestionDetailsController.SavedState)
                        savedInstanceState.getSerializable(QuestionDetailsController.SAVED_STATE_SCREEN_STATE)
        );
    }
}
