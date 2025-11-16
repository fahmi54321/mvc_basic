package com.example.mvc.common.permissions;

import static android.app.Activity.RESULT_OK;
import static com.example.mvc.common.ManifestPermission.PERMISSION_CAMERA;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.mvc.common.BaseObservable;

import java.io.File;

public class CameraHelper extends BaseObservable<CameraHelper.Listener> {

    public interface Listener {
        void onImagePicked(Uri imageUri);
        void onImagePickCanceled();

        void onPermissionCameraNeeded();
    }

    private final ActivityResultCaller caller;
    private final Context context;

    private Uri imageUri;

    public CameraHelper(ActivityResultCaller caller, Context context) {
        this.caller = caller;
        this.context = context;
    }

    public ActivityResultLauncher<Intent> registerLaunchers() {
        return caller.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        if (imageUri != null) {
                            notifyImagePicked(imageUri);
                            clearUri(); // clear setelah dipakai
                        } else {
                            clearUri(); // clear setelah dipakai
                            notifyImagePickCanceled();
                        }
                    } else {
                        clearUri(); // clear setelah dipakai
                        notifyImagePickCanceled();
                    }
                });
    }

    private void clearUri() {
        if(imageUri != null){
            imageUri = null;
        }
    }

    public boolean hasPermission(){
        return ContextCompat.checkSelfPermission(context, PERMISSION_CAMERA)
                == PackageManager.PERMISSION_GRANTED;
    }

    public void openCamera(ActivityResultLauncher<Intent> launcher) {
        if (hasPermission()) {
            launchCamera(launcher);
        } else {
            notifyPermissionNeeded();
        }
    }

    private void launchCamera(ActivityResultLauncher<Intent> launcher) {
        String fileName = "photo_" + System.currentTimeMillis() + ".jpg";
        File photoFile = new File(context.getExternalFilesDir(null), fileName);

        setUri(FileProvider.getUriForFile(context,
                context.getPackageName() + ".provider", photoFile));

        Log.e("registerLaunchers launchCamera uri",""+imageUri);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        launcher.launch(intent);
    }

    private void setUri(Uri uri){
        imageUri = uri;
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
            listener.onPermissionCameraNeeded();
        }
    }
}

