package com.dongqing.gank.kotlin.com.dongqing.gank.kotlin.ui.present

import android.Manifest
import android.app.Activity
import com.dongqing.gank.kotlin.com.dongqing.gank.kotlin.ui.view.IPhotoView
import com.dongqing.gank.kotlin.ui.present.BasePresent
import com.tbruyelle.rxpermissions2.RxPermissions

class PhotoViewPresent : BasePresent<IPhotoView>() {
    fun requestPermission() {
        RxPermissions(mContext as Activity)
                .requestEach((Manifest.permission.WRITE_EXTERNAL_STORAGE))
                .subscribe { permission ->
                    mView!!.permissions(permission)
                }
    }
}