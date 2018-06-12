package com.dongqing.gank.kotlin.ui.activity

import android.content.Intent
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.MenuItem
import android.view.View
import com.dongqing.gank.kotlin.BuildConfig
import com.dongqing.gank.kotlin.R
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.content_about.*

class AboutActivity : BaseActivity() {
    override fun initView() {
        toolbar_layout.title = "关于"
        toolbar_layout.setExpandedTitleColor(resources.getColor(android.R.color.transparent))
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val span = SpannableString(getString(R.string.about))
        span.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("name", "干货集中营")
                intent.putExtra("url", "http://gank.io")
                startActivity(intent)
            }
        }, 21, 27, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("name", "干货集中营")
                intent.putExtra("url", "https://github.com/dongjunkun/GanK")
                startActivity(intent)
            }
        }, 99, 103, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("name", "干货IO")
                intent.putExtra("url", "https://github.com/burgessjp/GanHuoIO")
                startActivity(intent)
            }
        }, 113, 121, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("name", "Type-safe HTTP client for Android and Java by Square")
                intent.putExtra("url", "https://github.com/square/retrofit")
                startActivity(intent)
            }
        }, 151, 188, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("name", "RxJava bindings for Android")
                intent.putExtra("url", "https://github.com/ReactiveX/RxAndroid")
                startActivity(intent)
            }
        }, 190, 226, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("name", "Android 万能的Adapter for ListView,RecyclerView,GridView等，支持多种Item类型的情况")
                intent.putExtra("url", "https://github.com/hongyangAndroid/baseAdapter")
                startActivity(intent)
            }
        }, 264, 292, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("name", "An image loading and caching library for Android focused on smooth scrolling")
                intent.putExtra("url", "https://github.com/bumptech/glide")
                startActivity(intent)
            }
        }, 294, 331, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("name", "Implementation of ImageView for Android that supports zooming, by various touch gestures")
                intent.putExtra("url", "https://github.com/chrisbanes/PhotoView/")
                startActivity(intent)
            }
        }, 333, 370, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("name", "A circular ImageView for Android")
                intent.putExtra("url", "https://github.com/hdodenhof/CircleImageView/")
                startActivity(intent)
            }
        }, 372, 406, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("name", "RefreshLayout that support for OverScroll and better than iOS. 支持下拉刷新和上拉加载的RefreshLayout,自带越界回弹效果，支持RecyclerView,AbsListView,ScrollView,WebView")
                intent.putExtra("url", "https://github.com/lcodecorex/TwinklingRefreshLayout")
                startActivity(intent)
            }
        }, 408, 444, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("name", "An Android library to create multiple item types list views easily and flexibly")
                intent.putExtra("url", "https://github.com/drakeet/MultiType")
                startActivity(intent)
            }
        }, 446, 489, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("name", "A beautiful, fluid, and customizable dialogs API")
                intent.putExtra("url", "https://github.com/afollestad/material-dialogs")
                startActivity(intent)
            }
        }, 491, 538, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("name", "Android runtime permissions powered by RxJava2")
                intent.putExtra("url", "https://github.com/tbruyelle/RxPermissions")
                startActivity(intent)
            }
        }, 540, 592, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("name", "董庆的博客")
                intent.putExtra("url", "https://dongqing.me")
                startActivity(intent)
            }
        }, span.length - 11, span.length - 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                val intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("name", "董庆的Github")
                intent.putExtra("url", "https://github.com/a741762308")
                startActivity(intent)
            }
        }, span.length - 6, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        text_about.text = span
        text_about.movementMethod = LinkMovementMethod.getInstance()

        tv_version.text = "Version " + BuildConfig.VERSION_NAME
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_about

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
