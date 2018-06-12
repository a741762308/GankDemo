package com.dongqing.gank.kotlin.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.text.TextUtils
import com.dongqing.gank.kotlin.bean.gank.DataBean
import com.dongqing.gank.kotlin.bean.gank.ImgDataBean
import com.dongqing.gank.kotlin.ui.adapter.RecyclerFuliAdapter
import com.dongqing.gank.kotlin.ui.adapter.RecyclerNormalAdapter
import com.dongqing.gank.kotlin.ui.present.CommonPresent
import com.dongqing.gank.kotlin.ui.view.ICommonView
import com.zhy.adapter.recyclerview.CommonAdapter
import kotlinx.android.synthetic.main.fragment_base.*
import java.util.*

class CommonFragment : RefreshFragment<CommonPresent, ICommonView>(), ICommonView {
    private var data = ArrayList<DataBean>()
    private lateinit var adapter: CommonAdapter<DataBean>

    override fun initView() {
        super.initView()
        lateinit var manager: RecyclerView.LayoutManager
        if (TextUtils.equals("福利", categoryType)) {
            manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = RecyclerFuliAdapter(mContext, data)
        } else {
            manager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
            adapter = RecyclerNormalAdapter(mContext, data)
        }
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter

    }

    override fun onDataReceive(t: List<ImgDataBean>) {
        if (page === 1) {
            data.clear()
            if (refresh_layout != null) {
                refresh_layout.finishRefreshing()
            }
        } else {
            if (refresh_layout != null) {
                refresh_layout.finishLoadmore()
            }
        }
        if (t == null || t.isEmpty()) {
            toast("没有更多的数据了")
        } else {
            data.addAll(t)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDataError() {
        if (refresh_layout != null) {
            refresh_layout.finishRefreshing()
            refresh_layout.finishLoadmore()
        }
    }

    override fun getData() {
        mPresenter!!.getCategoryData(categoryType, page)
    }
}