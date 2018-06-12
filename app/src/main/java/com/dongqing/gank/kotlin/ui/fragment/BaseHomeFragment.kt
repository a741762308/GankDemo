package com.dongqing.gank.kotlin.ui.fragment

import android.support.design.widget.TabLayout
import com.dongqing.gank.kotlin.R
import com.dongqing.gank.kotlin.bean.HomeData
import com.dongqing.gank.kotlin.ui.adapter.TabFragmentAdapter
import com.dongqing.gank.kotlin.ui.view.IFloatView
import kotlinx.android.synthetic.main.fragment_home.*

abstract class BaseHomeFragment : BaseFragment(), IFloatView {
    private var homeData: HomeData? = null

    protected abstract fun setUpViewPage(): HomeData

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        initViewPage()
    }

    private fun initViewPage() {
        homeData = setUpViewPage()
        val adapter = TabFragmentAdapter(childFragmentManager, homeData!!.fragments, homeData!!.titles)
        view_page.adapter = adapter
        tabs.setupWithViewPager(view_page)
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun fastMoveToTop() {
        var current = homeData!!.fragments[tabs.selectedTabPosition]
        if (current is IFloatView) {
            (current as IFloatView).fastMoveToTop()
        }
    }
}
