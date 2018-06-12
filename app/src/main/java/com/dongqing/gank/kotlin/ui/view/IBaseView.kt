package com.dongqing.gank.kotlin.ui.view

import com.tbruyelle.rxpermissions2.Permission

interface IBaseView {
    fun toast(s: String)

    fun permissions(p: Permission)
}