package com.dongqing.gank.kotlin.ui.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.http.SslError
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dongqing.gank.kotlin.R
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.toor_bar.*


class WebViewActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_web_view; }


    override fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val settings = webview.settings
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.javaScriptEnabled = true
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.setAppCacheEnabled(true)
        settings.domStorageEnabled = true
        webview.loadUrl(intent.getStringExtra("url"))
        webview.webChromeClient = MyWebChrome()
        webview.webViewClient = MyWebView()
    }

    internal inner class MyWebChrome : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            if (newProgress == 100) {
                progressbar.visibility = View.GONE
            } else {
                if (progressbar.visibility === View.GONE)
                    progressbar.visibility = View.VISIBLE
                progressbar.progress = newProgress
            }
            super.onProgressChanged(view, newProgress)
        }

        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            toolbar.title = title
        }

    }

    internal inner class MyWebView : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return super.shouldOverrideUrlLoading(view, url)
        }

        override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
            super.onReceivedError(view, errorCode, description, failingUrl)
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
        when (item.itemId) {
            R.id.action_copy -> {
                val cmb = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                cmb.primaryClip = ClipData.newPlainText(null, intent.getStringExtra("url"))
                toast("已复制到剪贴板，快分享给你的好友吧")
            }
            R.id.action_share -> {
                showShare()
            }
            else -> finish()
        }
        return true
    }

    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            finish()
        }
    }
}
