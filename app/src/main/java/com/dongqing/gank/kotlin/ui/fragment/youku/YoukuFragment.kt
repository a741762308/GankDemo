package com.dongqing.gank.kotlin.ui.fragment.youku

import android.os.Bundle
import android.support.v4.app.Fragment
import com.dongqing.gank.kotlin.bean.HomeData
import com.dongqing.gank.kotlin.ui.fragment.BaseHomeFragment
import java.util.*

class YoukuFragment : BaseHomeFragment() {
    override fun setUpViewPage(): HomeData {
        val titles = ArrayList<String>()
        titles.add("电视剧")
        titles.add("电影")
        titles.add("综艺")
        titles.add("动漫")
        titles.add("纪录片")
//        titles.add("音乐");
        titles.add("教育")
        titles.add("体育")
        val fragments = ArrayList<Fragment>()
        for (i in titles.indices) {
            val fragment = YoukuProgramFragment()
            val bundle = Bundle()
            bundle.putString("type", titles[i])
            fragment.arguments = bundle
            fragments.add(fragment)
        }
        return HomeData(titles, fragments)
    }
}