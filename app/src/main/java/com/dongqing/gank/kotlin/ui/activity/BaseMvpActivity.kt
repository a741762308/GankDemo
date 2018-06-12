package com.dongqing.gank.kotlin.ui.activity

import com.dongqing.gank.kotlin.ui.present.BasePresent
import com.dongqing.gank.kotlin.ui.view.IBaseView
import com.dongqing.gank.kotlin.utils.TUtil

abstract class BaseMvpActivity<P : BasePresent<V>, V : IBaseView> : BaseActivity(), IBaseView {
    protected var mPresenter: P? = null

    override fun initPresenter() {
        mPresenter = TUtil.getT<P>(this, 0)
        mPresenter!!.attachView(this, this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.detachView()
        }
    }
}
