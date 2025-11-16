package com.example.mvc.common.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.mvc.common.BaseObservable;

public class PermissionsHelper extends BaseObservable<PermissionsHelper.Listener> {
    public PermissionsHelper(ActivityResultCaller caller, Context context) {
        this.caller = caller;
        this.context = context;
    }

    public interface Listener{

        void onPermissionGranted(String permission);

        void onPermissionDeclined(String permission);

        void onPermissionDeclinedDontAskAgain(String permission);
    }

    private final ActivityResultCaller caller;
    private final Context context;

    private Context getContext(){
        return context;
    }

    public boolean hasPermission(String permission){
        return ContextCompat.checkSelfPermission(getContext(), permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermission(String permission, ActivityResultLauncher<String> activityResultLauncher){
        activityResultLauncher.launch(permission);
    }

    public ActivityResultLauncher<String> registerPermission(String permission) {
        return caller.registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (Boolean.TRUE.equals(isGranted)) {
                        notifyPermissionGranted(permission);
                    } else {
                        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)) {
                            notifyPermissionDeclined(permission);
                        } else {
                            notifyPermissionDeclinedDontAskAgain(permission);
                        }
                    }
                });
    }

    private void notifyPermissionDeclinedDontAskAgain(String permission) {
        for(Listener listener: getListeners()){
            listener.onPermissionDeclinedDontAskAgain(permission);
        }
    }

    private void notifyPermissionDeclined(String permission) {
        for(Listener listener: getListeners()){
            listener.onPermissionDeclined(permission);
        }
    }

    private void notifyPermissionGranted(String permission){
        for(Listener listener: getListeners()){
            listener.onPermissionGranted(permission);
        }
    }
}
