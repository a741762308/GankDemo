package com.dongqing.gank.kotlin.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import cn.sharesdk.onekeyshare.OnekeyShare
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.dongqing.gank.kotlin.BuildConfig
import com.dongqing.gank.kotlin.R
import com.dongqing.gank.kotlin.bean.weather.DailyWeather
import com.dongqing.gank.kotlin.ui.fragment.HomeFragment
import com.dongqing.gank.kotlin.ui.fragment.WebFragment
import com.dongqing.gank.kotlin.ui.fragment.douban.DoubanFragment
import com.dongqing.gank.kotlin.ui.fragment.youku.YoukuFragment
import com.dongqing.gank.kotlin.ui.present.MainPresent
import com.dongqing.gank.kotlin.ui.view.IFloatView
import com.dongqing.gank.kotlin.ui.view.IMainView
import com.dongqing.gank.kotlin.utils.DateUtil
import com.dongqing.gank.kotlin.utils.StringUtils
import com.google.gson.Gson
import com.tbruyelle.rxpermissions2.Permission
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.toor_bar.*

class MainActivity : BaseMvpActivity<MainPresent, IMainView>(), NavigationView.OnNavigationItemSelectedListener, IMainView, View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    private var lastTime: Long = 0

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fab -> {
                if (currentFragment is IFloatView) {
                    (currentFragment as IFloatView).fastMoveToTop()
                }
            }
            R.id.tv_city -> startLocate()
        }
    }

    var mLocationClient: AMapLocationClient? = null
    var mLocationOption: AMapLocationClientOption? = null
    var currentFragment: Fragment? = null


    private val icons_weather = intArrayOf(R.mipmap.weather_0, R.mipmap.weather_1, R.mipmap.weather_2,
            R.mipmap.weather_3, R.mipmap.weather_4, R.mipmap.weather_5, R.mipmap.weather_6,
            R.mipmap.weather_7, R.mipmap.weather_8, R.mipmap.weather_9, R.mipmap.weather_10,
            R.mipmap.weather_11, R.mipmap.weather_12, R.mipmap.weather_13, R.mipmap.weather_14,
            R.mipmap.weather_15, R.mipmap.weather_16, R.mipmap.weather_17, R.mipmap.weather_18,
            R.mipmap.weather_19, R.mipmap.weather_20, R.mipmap.weather_21, R.mipmap.weather_22,
            R.mipmap.weather_23, R.mipmap.weather_24, R.mipmap.weather_25, R.mipmap.weather_26,
            R.mipmap.weather_27, R.mipmap.weather_28, R.mipmap.weather_29, R.mipmap.weather_30,
            R.mipmap.weather_31, R.mipmap.weather_32, R.mipmap.weather_33, R.mipmap.weather_34,
            R.mipmap.weather_35, R.mipmap.weather_36, R.mipmap.weather_37, R.mipmap.weather_38, R.mipmap.weather_99)

    override fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        setSupportActionBar(toolbar)

        fab.setOnClickListener(this)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        initLocate()

        mPresenter!!.requestPermission()

        replace(HomeFragment())
    }

    private fun replace(fragment: Fragment) {
        if (currentFragment != null) {
            if (currentFragment is HomeFragment && fragment is HomeFragment) {
                return
            } else if (currentFragment is WebFragment && fragment is WebFragment) {
                return
            } else if (currentFragment is DoubanFragment && fragment is DoubanFragment) {
                return
            } else if (currentFragment is YoukuFragment && fragment is YoukuFragment) {
                return
            }
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, fragment).addToBackStack(null).commit()
        currentFragment = fragment
    }

    private fun initLocate() {
        mLocationOption = AMapLocationClientOption()
        mLocationOption!!.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        mLocationOption!!.interval = 10 * 60 * 1000
        mLocationClient = AMapLocationClient(mContext)
        mLocationClient!!.setLocationOption(mLocationOption)
        mLocationClient!!.setLocationListener { aMapLocation ->
            if (aMapLocation != null) {
                if (aMapLocation.errorCode == 0) {
                    aCache.put("city", aMapLocation.city)
                    tv_city.text = aMapLocation.city
                    tv_city.setOnClickListener(mContext as View.OnClickListener)
                    mPresenter!!.getDailyWeather(tv_city.text.toString())
                } else {
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.errorCode + ", errInfo:"
                            + aMapLocation.errorInfo)
                }
            }
        }
    }

    private fun startLocate() {
        mLocationClient!!.startLocation()

    }

    override fun showShare() {
        var downUrl = "http://fir.im/38zp"
        when (BuildConfig.FLAVOR) {
            "tencent" -> downUrl = "http://a.app.qq.com/o/simple.jsp?pkgname=$packageName"
            "qh360" -> downUrl = "http://openbox.mobilem.360.cn/qcms/view/t/detail?sid=3598872"
            "baidu" -> downUrl = "http://mobile.baidu.com/item?docid=10617876"
        }

        val oks = OnekeyShare()
        //关闭sso授权
        oks.disableSSOWhenAuthorize()
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        //        oks.setTitle(getIntent().getStringExtra("name"));
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(downUrl)
        // text是分享文本，所有平台都需要这个字段
        oks.text = "我正在使用【干货Gank】,还不错！赶紧下载吧！$downUrl"
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        //        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(downUrl)
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        //        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(downUrl)
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(downUrl)

        // 启动分享GUI
        oks.show(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            var currentTick = System.currentTimeMillis()
            if (currentTick - lastTime > 1500) {
                Snackbar.make(fab, "再按一次退出程序", Snackbar.LENGTH_SHORT).show();
                lastTime = currentTick
            } else {
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                replace(HomeFragment())
                supportActionBar!!.title = "干货首页"
            }
            R.id.nav_xiandu -> {
                replace(WebFragment())
                supportActionBar!!.title = "干货闲读"
            }
            R.id.nav_douban -> {
                replace(DoubanFragment())
                supportActionBar!!.title = "豆瓣电影"
            }
            R.id.nav_youku -> {
                replace(YoukuFragment())
                supportActionBar!!.title = "优酷首页"
            }
            R.id.nav_share -> {
                showShare()
            }
            R.id.nav_send -> {
                evaluate()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showSnackbar(view: View, s: String) {
        Snackbar.make(view, s, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }

    override fun permissions(p: Permission) {
        when {
            p.granted -> {
                startLocate()
            }
            p.shouldShowRequestPermissionRationale -> {
                toast("请授予定位权限")
            }
            else -> {
                toast("请到应用详情页权限管理中授予权限！")
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.fromParts("package", packageName, null)
                startActivity(intent)
            }
        }
    }

    override fun getWeather(result: DailyWeather.ResultsBean) {
        Log.v("weather result", Gson().toJson(result))
        val daily = result.daily[0]
        if (DateUtil.currentDateTimeStatus === DateUtil.TIME.PM) {
            iv_weather.setImageResource(icons_weather[StringUtils.toInt(daily.code_night)])
            tv_weather.text = daily.text_night
        } else {
            iv_weather.setImageResource(icons_weather[StringUtils.toInt(daily.code_day)])
            tv_weather.text = daily.text_day
        }
        tv_temperature.text = daily.low + "/" + daily.high + "°C"
    }
}
