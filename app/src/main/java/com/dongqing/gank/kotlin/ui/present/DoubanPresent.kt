package com.dongqing.gank.kotlin.ui.present

import android.util.Log
import com.dongqing.gank.kotlin.api.Api
import com.dongqing.gank.kotlin.ui.view.IDoubanView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DoubanPresent : BasePresent<IDoubanView>() {
    fun getWellFilm(page: Int) {
        Api.instance.service.filmWell((page - 1) * Api.PAGE_SIZE, Api.PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mView!!.onDataReceive(it)
                }, {
                    mView!!.onDataError()
                }, {
                    Log.v("success:", "onCompleted")
                }, {

                })

    }

    fun getComeFilm(page: Int) {
        Api.instance.service.filmCome((page - 1) * Api.PAGE_SIZE, Api.PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mView!!.onDataReceive(it)
                }, {
                    mView!!.onDataError()
                }, {
                    Log.v("success:", "onCompleted")
                }, {

                })
    }
}
