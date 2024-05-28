package com.libremobileos.desktopmode;

import android.os.Bundle;

import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;
import androidx.activity.result.contract.ActivityResultContracts;
import com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity;

public class SettingsActivity extends CollapsingToolbarBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(com.android.settingslib.R.id.content_frame, new SettingsFragment())
                    .commit();
        }
        checkAndRequestForPermissionNotification();
    }
    private void checkAndRequestForPermissionNotification() {
        if (ContextCompat.checkSelfPermission(SettingsActivity.this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            }).launch(Manifest.permission.POST_NOTIFICATIONS);
        }
    }
}
