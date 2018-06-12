package com.dongqing.gank.kotlin.ui.fragment

import android.os.Bundle
import android.widget.Toast
import com.dongqing.gank.kotlin.ui.present.BasePresent
import com.dongqing.gank.kotlin.ui.view.IBaseView
import com.dongqing.gank.kotlin.utils.TUtil
import com.tbruyelle.rxpermissions2.Permission

abstract class BaseMvpFragment<P : BasePresent<V>, V : IBaseView> : BaseFragment(), IBaseView {
    protected var mPresenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = TUtil.getT<P>(this, 0)
        mPresenter!!.attachView(mContext, this as V)
    }

    override fun toast(s: String) {
        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show()
    }

    override fun permissions(p: Permission) {

    }
}
