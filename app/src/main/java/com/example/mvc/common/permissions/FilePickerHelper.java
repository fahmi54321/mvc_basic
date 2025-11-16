package com.example.mvc.common.permissions;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.mvc.common.BaseObservable;

public class FilePickerHelper extends BaseObservable<FilePickerHelper.Listener> {

    public interface Listener {
        void onImagePicked(Uri imageUri);
        void onImagePickCanceled();
    }

    private final ActivityResultCaller caller;
    private final Context context;
    private final PermissionsHelper permissionsHelper;

    private ActivityResultLauncher<Uri> cameraLauncher;
    private ActivityResultLauncher<String> galleryLauncher;

    private Uri imageUri;

    public FilePickerHelper(ActivityResultCaller caller, Context context, PermissionsHelper permissionsHelper) {
        this.caller = caller;
        this.context = context;
        this.permissionsHelper = permissionsHelper;
        registerLaunchers();
    }

    private void registerLaunchers() {
        cameraLauncher = caller.registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                success -> {
                    if (Boolean.TRUE.equals(success) && imageUri != null) {
                        notifyImagePicked(imageUri);
                    } else {
                        notifyImagePickCanceled();
                    }
                });

        galleryLauncher = caller.registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        notifyImagePicked(uri);
                    } else {
                        notifyImagePickCanceled();
                    }
                });
    }

    public void openCamera() {
        if (permissionsHelper.hasPermission(Manifest.permission.CAMERA)) {
            launchCamera();
        } else {
            ActivityResultLauncher<String> permissionLauncher =
                    permissionsHelper.registerPermission(Manifest.permission.CAMERA);
            permissionLauncher.launch(Manifest.permission.CAMERA);
        }
    }

    private void launchCamera() {
        imageUri = createImageUri();
        cameraLauncher.launch(imageUri);
    }

    public void openGallery() {
        galleryLauncher.launch("image/*");
    }

    private Uri createImageUri() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "temp_image_" + System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }

    private void notifyImagePicked(Uri uri) {
        for (Listener listener : getListeners()) {
            listener.onImagePicked(uri);
        }
    }

    private void notifyImagePickCanceled() {
        for (Listener listener : getListeners()) {
            listener.onImagePickCanceled();
        }
    }
}

