package com.example.bhavesh.taskmanager.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Admin on 06-02-2018.
 */

public class BaseActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 0;
    private static IPermissionListener mListener;
    private static Activity sTopActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivityToTask(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivityToTask(this);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public static void requestRuntimePermission(String[] permissions, IPermissionListener permissionListener) {

        mListener = permissionListener;
        sTopActivity = ActivityManager.getTaskTopActivity();
        List<String> permissionList = new ArrayList<>();

        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(sTopActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(sTopActivity,permissionList.toArray(new String[permissionList.size()]), REQUEST_CODE);
        } else {
            mListener.OnGranted();
        }

    }

    /**
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE :
                List<String> deniedPermissions = new ArrayList<>();
                for (int i = 0; i < permissions.length; i++) {
                    String permission  = permissions[i];
                    int grant = grantResults[i];
                    if (grant != PackageManager.PERMISSION_GRANTED) {

                        deniedPermissions.add(permission);
                    }
                }
                if (deniedPermissions.isEmpty()) {

                    mListener.OnGranted();
                } else {

                    mListener.OnDenided(deniedPermissions);
                }
                break;
            default:

                break;
        }
    }


    public interface IPermissionListener {

        void OnGranted();

        void OnDenided(List<String> deniedPermission);
    }

}
