package com.dongqing.gank.kotlin.ui.fragment


import android.webkit.WebView
import android.webkit.WebViewClient
import com.dongqing.gank.kotlin.R
import com.dongqing.gank.kotlin.ui.view.IFloatView
import kotlinx.android.synthetic.main.fragment_web.*

class WebFragment : BaseFragment(), IFloatView {
    override fun fastMoveToTop() {
        ns_view.smoothScrollTo(0, 0)
    }

    override fun initView() {
        val settings = webview.settings
        settings.javaScriptEnabled = true
        settings.useWideViewPort = true
        settings.userAgentString = "User-Agent: Mozilla/5.0 (Linux; Android 7.0; ZUK Z2131 Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/53.0.2785.49 Mobile MQQBrowser/6.2 TBS/043015 Safari/537.36 MicroMessenger/6.5.3.980 NetType/WIFI Language/zh_CN"
        webview.loadUrl("http://gank.io/xiandu")
        webview.webViewClient = MyWebView()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_web
    }

    internal inner class MyWebView : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return super.shouldOverrideUrlLoading(view, url)
        }
    }
}
