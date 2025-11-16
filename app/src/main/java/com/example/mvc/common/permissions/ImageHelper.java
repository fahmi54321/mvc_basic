package com.example.mvc.common.permissions;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;

import com.example.mvc.common.BaseObservable;

public class ImageHelper extends BaseObservable<ImageHelper.Listener> {

    public interface Listener {
        void onImagePicked(Uri imageUri);
        void onImagePickCanceled();

        void onPermissionReadExternalStorageNeeded();
    }

    private final ActivityResultCaller caller;
    private final Context context;

    public ImageHelper(ActivityResultCaller caller, Context context) {
        this.caller = caller;
        this.context = context;
    }

    public ActivityResultLauncher<PickVisualMediaRequest> registerLaunchers() {
        return caller.registerForActivityResult(
                new ActivityResultContracts.PickVisualMedia(),
                uri -> {
                    if (uri != null) {
                        notifyImagePicked(uri);
                    } else {
                        notifyImagePickCanceled();
                    }
                });
    }

    public boolean hasPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return true;
        } else {
            return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED;
        }
    }

    public void openMedia(ActivityResultLauncher<PickVisualMediaRequest> launcher) {
        if (hasPermission()) {
            launchMedia(launcher);
        } else {
            notifyPermissionNeeded();
        }
    }

    private void launchMedia(ActivityResultLauncher<PickVisualMediaRequest> launcher) {
        launcher.launch(
                new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build()
        );
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

    private void notifyPermissionNeeded() {
        for (Listener listener : getListeners()) {
            listener.onPermissionReadExternalStorageNeeded();
        }
    }
}

