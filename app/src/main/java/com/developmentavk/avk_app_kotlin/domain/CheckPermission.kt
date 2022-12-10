package com.developmentavk.avk_app_kotlin.domain

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class CheckPermission {
    companion object{
        val REQUEST_CODE_PERMISSION_CAMERA = 105
        val REQUEST_CODE_PERMISSION_EXTERNAL = 106
        val REQUEST_CODE_PERMISSION_NOTIFICATION_POLICY = 107
        fun checkPermission(ctx: Activity) {
            val permissionCamera = ContextCompat.checkSelfPermission(ctx, Manifest.permission.CAMERA)
            val permissionExternal =
                ContextCompat.checkSelfPermission(ctx, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            val permissionVabrate = ContextCompat.checkSelfPermission(ctx, Manifest.permission.VIBRATE)
            val permissionNotification =
                ContextCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_NOTIFICATION_POLICY)

            if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    ctx, arrayOf(Manifest.permission.CAMERA),
                    REQUEST_CODE_PERMISSION_CAMERA
                )
            } else if (permissionExternal != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    ctx, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_CODE_PERMISSION_EXTERNAL
                )
            } else if (permissionVabrate != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ctx, arrayOf(Manifest.permission.VIBRATE), 1)
            } else if (permissionNotification != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    ctx,
                    arrayOf(Manifest.permission.ACCESS_NOTIFICATION_POLICY),
                    REQUEST_CODE_PERMISSION_NOTIFICATION_POLICY
                )
            }
        }
    }
}