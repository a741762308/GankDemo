package com.dongqing.gank.kotlin.ui.present

import android.Manifest
import android.app.Activity
import android.util.Log
import android.view.View
import com.dongqing.gank.kotlin.api.Api
import com.dongqing.gank.kotlin.bean.weather.DailyWeather
import com.dongqing.gank.kotlin.ui.view.IMainView
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

 class MainPresent : BasePresent<IMainView>() {
    fun requestPermission() {
        val p = RxPermissions(mContext as Activity)
        p.requestEach(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe { permission ->
                    mView!!.permissions(permission)
                }
    }

    fun getDailyWeather(city: String) {
        Api.instance.service.getDailyWeather("hx5bcn1nsnrwv3yp", city, 0, 1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map { t -> t.results[0] }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<DailyWeather.ResultsBean> {
                    override fun onComplete() {
                        Log.v("success:", "onCompleted")
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: DailyWeather.ResultsBean) {
                        mView!!.getWeather(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.v("failure:", e.toString())
                    }

                })
    }

    fun showSnackbar(view: View, s: String) {
        mView!!.showSnackbar(view, s)
    }
}