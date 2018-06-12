package com.dongqing.gank.kotlin.ui.fragment


import com.dongqing.gank.kotlin.R
import com.dongqing.gank.kotlin.ui.present.BasePresent
import com.dongqing.gank.kotlin.ui.view.IBaseView
import com.dongqing.gank.kotlin.ui.view.IFloatView
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout
import kotlinx.android.synthetic.main.fragment_base.*

/**
 *
 *
 */
abstract class RefreshFragment<P : BasePresent<V>, V : IBaseView> : BaseMvpFragment<P, V>(), IFloatView {
    var page = 1
    var categoryType = "all"
    var refrsh = false
    override fun getLayoutId(): Int {
        return R.layout.fragment_base
    }


    abstract fun getData()

    override fun initView() {
        refresh_layout.setFloatRefresh(true)
        var headView = ProgressLayout(mContext)
        var colors = resources.getIntArray(R.array.material_colors)
        headView.setColorSchemeColors(*colors)
        refresh_layout.setHeaderView(headView)
        refresh_layout.startRefresh()
        refresh_layout.setOverScrollBottomShow(true)
        if (arguments != null) {
            categoryType = arguments!!.getString("type", "all")
        }
        refresh_layout.setOnRefreshListener(object : RefreshListenerAdapter() {
            override fun onRefresh(refreshLayout: TwinklingRefreshLayout?) {
                page = 1
                refrsh = true
                getData()
            }

            override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                page++
                refrsh = false
                getData()
            }
        })
    }

    override fun fastMoveToTop() {
        recyclerView.smoothScrollToPosition(0)
    }
}
