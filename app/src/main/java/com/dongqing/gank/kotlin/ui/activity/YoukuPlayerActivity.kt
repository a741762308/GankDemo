package com.dongqing.gank.kotlin.ui.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.http.SslError
import android.os.PowerManager
import android.view.*
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dongqing.gank.kotlin.R
import kotlinx.android.synthetic.main.activity_youku_player.*
import kotlinx.android.synthetic.main.toor_bar.*

class YoukuPlayerActivity : BaseActivity() {
    override fun getLayoutId(): Int {
       return R.layout.activity_youku_player
    }

    private var mWakeLock: PowerManager.WakeLock? = null
    private val POWER_LOCK = "YoukuPlayerActivity"


    private var mOrientationListener: OrientationEventListener? = null
    private var mScreenProtrait = true
    private var mCurrentOrient = false


    override fun initView() {
        val pm = getSystemService(Context.POWER_SERVICE) as PowerManager
        mWakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK or PowerManager.ON_AFTER_RELEASE, POWER_LOCK)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        val settings = webview!!.settings
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.javaScriptEnabled = true
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.setAppCacheEnabled(true)
        settings.domStorageEnabled = true
        settings.setSupportMultipleWindows(true)
        webview!!.loadUrl(intent.getStringExtra("url"))
        settings.userAgentString = "User-Agent: Mozilla/5.0 (Linux; Android 7.0; ZUK Z2131 Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/53.0.2785.49 Mobile MQQBrowser/6.2 TBS/043015 Safari/537.36 MicroMessenger/6.5.3.980 NetType/WIFI Language/zh_CN"
        webview!!.webChromeClient = MyWebChrome()
        webview!!.webViewClient = MyWebView()

        startOrientationChangeListener()

    }

    override fun onPause() {
        super.onPause()
        if (mWakeLock != null) {
            mWakeLock!!.release()
        }
    }

    override fun onResume() {
        super.onResume()
        if (null != mWakeLock && !mWakeLock!!.isHeld) {
            mWakeLock!!.acquire()
        }
    }

    private fun startOrientationChangeListener() {
        mOrientationListener = object : OrientationEventListener(this) {
            override fun onOrientationChanged(rotation: Int) {
                if (rotation >= 0 && rotation <= 45 || rotation >= 315 || rotation >= 135 && rotation <= 225) {//portrait
                    mCurrentOrient = true
                    if (mCurrentOrient != mScreenProtrait) {
                        mScreenProtrait = mCurrentOrient
                        orientationChanged(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    }
                } else if (rotation > 45 && rotation < 135 || rotation > 225 && rotation < 315) {//landscape
                    mCurrentOrient = false
                    if (mCurrentOrient != mScreenProtrait) {
                        mScreenProtrait = mCurrentOrient
                        orientationChanged(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                    }
                }
            }
        }
        mOrientationListener!!.enable()
    }

    private fun orientationChanged(screenOrientationPortrait: Int) {
        requestedOrientation = screenOrientationPortrait
        //        if (screenOrientationPortrait == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
        //            toolbar.setVisibility(View.VISIBLE);
        //            screenBack.setVisibility(View.GONE);
        //            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //        } else {
        //            toolbar.setVisibility(View.GONE);
        //            screenBack.setVisibility(View.VISIBLE);
        //            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        //        if (layoutWeb.getVisibility() == View.VISIBLE) {
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            toolbar!!.visibility = View.VISIBLE
            //            screenBack.setVisibility(View.GONE);
            window.setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            toolbar!!.visibility = View.GONE
            //            screenBack.setVisibility(View.VISIBLE);
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        //        }
    }

    internal inner class MyWebChrome : WebChromeClient() {
        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            toolbar!!.title = title
        }

    }

    internal inner class MyWebView : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return super.shouldOverrideUrlLoading(view, url)
        }

        override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
            handler.proceed()
            super.onReceivedSslError(view, handler, error)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.webview, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_copy) {
            val cmb = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cmb.primaryClip = ClipData.newPlainText(null, intent.getStringExtra("url"))
            toast("已复制到剪贴板，快分享给你的好友吧")
        } else if (id == R.id.action_share) {
            showShare()
        } else if (id == android.R.id.home) {
            finish()
        }

        return true
    }

    override fun onBackPressed() {
        if (resources.configuration.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            if (intent.getStringExtra("url").endsWith(webview!!.url.substring(webview!!.url.lastIndexOf("/")))) {
                finish()
            } else {
                if (webview!!.canGoBack()) {
                    webview!!.goBack()
                } else {
                    finish()
                }
            }
        }
    }
}
