package com.dongqing.gank.kotlin.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import com.dongqing.gank.kotlin.bean.gank.FuliBean
import com.dongqing.gank.kotlin.bean.gank.GankData
import com.dongqing.gank.kotlin.bean.gank.HeaderBean
import com.dongqing.gank.kotlin.bean.gank.RecentBean
import com.dongqing.gank.kotlin.ui.adapter.multi.FuliViewBinder
import com.dongqing.gank.kotlin.ui.adapter.multi.RecentViewBinder
import com.dongqing.gank.kotlin.ui.present.RecommendPresent
import com.dongqing.gank.kotlin.ui.view.IRecommendView
import com.dongqing.gank.kotlin.utils.ACache
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_base.*
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter
import java.util.*


class RecommendFragment : RefreshFragment<RecommendPresent, IRecommendView>(), IRecommendView {
    companion object {
        const val DAY_OF_MILLISECOND = 24 * 60 * 60 * 1000L
    }

    private var mCurrentDate = Date()
    lateinit var items: Items
    lateinit var adapter: MultiTypeAdapter
    override fun initView() {
        super.initView()
        recyclerView.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        items = Items()
        adapter = MultiTypeAdapter()
        adapter.register(FuliBean::class.java, FuliViewBinder())
        adapter.register(RecentBean::class.java, RecentViewBinder())
        recyclerView.adapter = adapter
    }

    override fun getData() {
        if (refrsh) {
            mCurrentDate = Date()
        }
        val calender = Calendar.getInstance()
        calender.time = mCurrentDate
        mPresenter!!.getDayData(calender[Calendar.YEAR], calender[Calendar.MONTH] + 1, calender[Calendar.DAY_OF_MONTH])
    }

    override fun onDataReceive(t: GankData.ResultsBean) {
        refrsh = false
        var result = Gson().toJson(t)
        Log.d("day gank", result)
        if (TextUtils.equals("{}", result)) {
            mCurrentDate = Date(mCurrentDate.time - DAY_OF_MILLISECOND)
            getData()
        } else {
            if (page == 1) {
                items.clear()
                if (refresh_layout != null) {
                    refresh_layout.finishRefreshing()
                }
            }
            if (refresh_layout != null) {
                refresh_layout.finishLoadmore()
            }
            if (t != null) {
                if (t.福利 != null && t.福利.size > 0) {
                    val img = t.福利[0]
                    var gson = Gson()
                    val bean = gson.fromJson(gson.toJson(img), FuliBean::class.java)
                    items.add(bean)
                    ACache.get(mContext).put("imgUrl", img.url)
                }
                if (t.android != null && t.android.size > 0) {
                    var bean = RecentBean()
                    bean.headerBean = HeaderBean("Android")
                    bean.list = t.android
                    items.add(bean)
                }
                if (t.getiOS() != null && t.getiOS().size > 0) {
                    var bean = RecentBean()
                    bean.headerBean = HeaderBean("iOS")
                    bean.list = t.getiOS()
                    items.add(bean)
                }
                if (t.前端 != null && t.前端.size > 0) {
                    var bean = RecentBean()
                    bean.headerBean = HeaderBean("前端")
                    bean.list = t.前端
                    items.add(bean)
                }
                if (t.瞎推荐 != null && t.瞎推荐.size > 0) {
                    var bean = RecentBean()
                    bean.headerBean = HeaderBean("瞎推荐")
                    bean.list = t.瞎推荐
                    items.add(bean)
                }
                if (t.拓展资源 != null && t.拓展资源.size > 0) {
                    var bean = RecentBean()
                    bean.headerBean = HeaderBean("拓展资源")
                    bean.list = t.拓展资源
                    items.add(bean)
                }
                if (t.休息视频 != null && t.休息视频.size > 0) {
                    var bean = RecentBean()
                    bean.headerBean = HeaderBean("休息视频")
                    bean.list = t.休息视频
                    items.add(bean)
                }
                adapter.items = items
                adapter.notifyDataSetChanged()
                if (page == 1) {
                    recyclerView.smoothScrollToPosition(0)
                }
            }
        }
    }

    override fun onDataError() {
        if (refresh_layout != null) {
            refresh_layout.finishRefreshing()
            refresh_layout.finishLoadmore()
        }
    }

    override fun onUpdateDate() {
        mCurrentDate = Date(mCurrentDate.time - DAY_OF_MILLISECOND)
    }
}