package com.dongqing.gank.kotlin.ui.present

import android.content.Context
import com.dongqing.gank.kotlin.api.Api
import com.dongqing.gank.kotlin.ui.view.IBaseView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream

abstract class BasePresent<V : IBaseView> {
    protected var mContext: Context? = null
    protected var mView: V? = null

    fun attachView(context: Context?, view: V) {
        this.mContext = context
        this.mView = view
    }

    fun detachView() {
        mContext = null
        mView = null
    }
    fun downloadPicFromNet(url: String, path: String) {
        Api.instance.service.downloadPicFromNet(url)
                .subscribeOn(Schedulers.newThread())
                .map { t ->
                    var picFileDir = File(path)
                    if (!picFileDir.exists()) {
                        picFileDir.mkdirs()
                    }
                    var name = url.substring(url.lastIndexOf("/") + 1)
                    try {
                        var picFile = File(picFileDir, name)
                        var fos = FileOutputStream(picFile)
                        fos.write(t.bytes())
                        fos.flush()
                        fos.close()
                    } catch (e: Exception) {

                    }
                    name
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mView!!.toast("下载成功")
                }, {
                    mView!!.toast("下载失败")
                }, {

                }, {

                })

    }
}