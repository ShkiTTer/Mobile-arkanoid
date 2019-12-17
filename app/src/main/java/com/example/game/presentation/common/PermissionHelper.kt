package com.example.game.presentation.common

import android.app.Activity
import android.content.pm.PackageManager

class PermissionHelper(private val activity: Activity) {
    fun requestPermissions(permissions: Array<String>, requestCode: Int) {
        activity.requestPermissions(permissions, requestCode)
    }

    fun checkPermission(permission: String): Boolean =
        activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
}