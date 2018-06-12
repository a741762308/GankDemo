package com.dongqing.gank.kotlin.ui.present

import com.dongqing.gank.kotlin.api.Api
import com.dongqing.gank.kotlin.ui.view.IRecommendView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RecommendPresent : BasePresent<IRecommendView>() {
    fun getDayData(year: Int, month: Int, day: Int) {
        Api.instance.service.getDayData(year, month, day)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map { gankData -> gankData.results }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mView!!.onDataReceive(it)
                }, {
                    it!!.printStackTrace()
                    mView!!.onDataError()
                }, {
                    mView!!.onUpdateDate()
                }, {

                })
    }
}