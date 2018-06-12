package com.dongqing.gank.kotlin.ui.present

import android.util.Log
import com.dongqing.gank.kotlin.api.Api
import com.dongqing.gank.kotlin.ui.view.IYoukuView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class YoukuPresent : BasePresent<IYoukuView>() {
    fun getYoukuProgramme(category: String, page: Int) {
        Api.instance.service.getYkProgrammeByCategory("655690f8143987c6", category, page, Api.PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map { ykProgram -> ykProgram.shows }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mView!!.onDataReceive(it)
                }, {
                    it.printStackTrace()
                    mView!!.onDataError()
                }, {
                    Log.v("success:", "onCompleted")
                }, {

                })
    }
}