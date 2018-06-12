package com.dongqing.gank.kotlin.ui.fragment.douban

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.dongqing.gank.kotlin.bean.HomeData
import com.dongqing.gank.kotlin.ui.fragment.BaseHomeFragment
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class DoubanFragment : BaseHomeFragment() {
    override fun setUpViewPage(): HomeData {
        tabs.tabMode = TabLayout.MODE_FIXED
        val titles = ArrayList<String>()
        titles.add("正在热映")
        titles.add("即将上线")
        val fragments = ArrayList<Fragment>()
        for (i in titles.indices) {
            val fragment = DoubanFilmFragment()
            val bundle = Bundle()
            bundle.putString("type", titles[i])
            fragment.arguments = bundle
            fragments.add(fragment)
        }
        return HomeData(titles, fragments)
    }
}