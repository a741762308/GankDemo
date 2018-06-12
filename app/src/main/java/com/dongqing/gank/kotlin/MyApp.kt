package com.dongqing.gank.kotlin

import android.app.Application
import com.mob.MobSDK

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        mInstance = this
        MobSDK.init(this)
    }

    companion object {
        private var mInstance: MyApp? = null

        fun get(): MyApp? {
            return mInstance
        }
    }

}