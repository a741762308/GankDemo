package com.dongqing.gank.kotlin.ui.present

import android.util.Log
import com.dongqing.gank.kotlin.api.Api
import com.dongqing.gank.kotlin.ui.view.ICommonView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommonPresent : BasePresent<ICommonView>() {
    fun getCategoryData(category: String, page: Int) {
        Api.instance.service.getCategoryData(category, page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map { categoryData -> categoryData.results }
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
