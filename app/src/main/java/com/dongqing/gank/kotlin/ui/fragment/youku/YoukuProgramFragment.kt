package com.dongqing.gank.kotlin.ui.fragment.youku

import android.support.v7.widget.GridLayoutManager
import com.dongqing.gank.kotlin.adapter.YoukuProgramAdapter
import com.dongqing.gank.kotlin.bean.youku.YKProgram
import com.dongqing.gank.kotlin.ui.fragment.RefreshFragment
import com.dongqing.gank.kotlin.ui.present.YoukuPresent
import com.dongqing.gank.kotlin.ui.view.IYoukuView
import com.zhy.adapter.recyclerview.CommonAdapter
import kotlinx.android.synthetic.main.fragment_base.*

class YoukuProgramFragment : RefreshFragment<YoukuPresent, IYoukuView>(), IYoukuView {
    private var data = ArrayList<YKProgram.ShowsBean>()
    private lateinit var adapter: CommonAdapter<YKProgram.ShowsBean>

    override fun onDataReceive(t: List<YKProgram.ShowsBean>) {
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

    override fun initView() {
        super.initView()
        val manager = GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = manager
        adapter = YoukuProgramAdapter(mContext, data)
        recyclerView.adapter = adapter
    }

    override fun getData() {
        mPresenter!!.getYoukuProgramme(categoryType, page)
    }
}