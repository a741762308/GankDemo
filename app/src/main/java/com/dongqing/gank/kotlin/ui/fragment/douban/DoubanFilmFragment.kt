package com.dongqing.gank.kotlin.ui.fragment.douban

import android.support.v7.widget.GridLayoutManager
import com.dongqing.gank.kotlin.adapter.DoubanFilmAdapter
import com.dongqing.gank.kotlin.bean.douban.DoubanFilm
import com.dongqing.gank.kotlin.ui.fragment.RefreshFragment
import com.dongqing.gank.kotlin.ui.present.DoubanPresent
import com.dongqing.gank.kotlin.ui.view.IDoubanView
import kotlinx.android.synthetic.main.fragment_base.*

class DoubanFilmFragment : RefreshFragment<DoubanPresent, IDoubanView>(), IDoubanView {

    private lateinit var adapter: DoubanFilmAdapter
    private var data = ArrayList<DoubanFilm.SubjectsBean>()

    override fun onDataReceive(t: DoubanFilm) {
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
        if (t.subjects == null || t.subjects.size == 0) {
            toast("没有更多的数据了")
        } else {
            data.addAll(t.subjects)
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
        val manager = GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = manager
        adapter = DoubanFilmAdapter(mContext, data)
        recyclerView.adapter = adapter
    }

    override fun getData() {
        if ("正在热映" == categoryType) {
            getWell()
        } else if ("即将上线" == categoryType) {
            getCome()
        }
    }

    private fun getWell() {
        mPresenter!!.getWellFilm(page)
    }

    private fun getCome() {
        mPresenter!!.getComeFilm(page)

    }
}