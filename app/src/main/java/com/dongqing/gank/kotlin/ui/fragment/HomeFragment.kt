package com.dongqing.gank.kotlin.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.dongqing.gank.kotlin.bean.HomeData

class HomeFragment : BaseHomeFragment() {

    override fun setUpViewPage(): HomeData {
        var titles = ArrayList<String>()
        titles.add("推荐")
        titles.add("Android")
        titles.add("iOS")
        titles.add("福利")
        titles.add("前端")
        titles.add("休息视频")
        titles.add("拓展资源")
        titles.add("App")
        titles.add("瞎推荐")
        var fragments = ArrayList<Fragment>()
        for (i in titles.indices) {
            var fragment: Fragment = if (i == 0) {
                RecommendFragment()
            } else {
                CommonFragment()
            }
            var bundle = Bundle()
            bundle.putString("type", titles[i])
            fragment.arguments = bundle
            fragments.add(fragment)
        }
        return HomeData(titles, fragments)
    }
}