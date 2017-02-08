package com.jsqix.dongqing.gank;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jsqix.dongqing.gank.app.BaseActivity;

public class AboutActivity extends BaseActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout toolbarLayout;
    private TextView textAbout,tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();

    }

    private void initView() {
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle("关于");
        toolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textAbout = (TextView) findViewById(R.id.text_about);

        Spannable span = new SpannableString(getString(R.string.about));
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,WebViewActivity.class);
                intent.putExtra("name","干货集中营");
                intent.putExtra("url","https://github.com/dongjunkun/GanK");
                startActivity(intent);
            }
        }, 99, 103, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,WebViewActivity.class);
                intent.putExtra("name","干货IO");
                intent.putExtra("url","https://github.com/burgessjp/GanHuoIO");
                startActivity(intent);
            }
        }, 113, 121, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,WebViewActivity.class);
                intent.putExtra("name","Type-safe HTTP client for Android and Java by Square");
                intent.putExtra("url","https://github.com/square/retrofit");
                startActivity(intent);
            }
        }, 151, 188, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,WebViewActivity.class);
                intent.putExtra("name","RxJava bindings for Android");
                intent.putExtra("url","https://github.com/ReactiveX/RxAndroid");
                startActivity(intent);
            }
        }, 280, 308, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,WebViewActivity.class);
                intent.putExtra("name","Android 万能的Adapter for ListView,RecyclerView,GridView等，支持多种Item类型的情况");
                intent.putExtra("url","https://github.com/hongyangAndroid/baseAdapter");
                startActivity(intent);
            }
        }, 335, 365, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,WebViewActivity.class);
                intent.putExtra("name","An image loading and caching library for Android focused on smooth scrolling");
                intent.putExtra("url","https://github.com/bumptech/glide");
                startActivity(intent);
            }
        }, 366, 404, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,WebViewActivity.class);
                intent.putExtra("name","Implementation of ImageView for Android that supports zooming, by various touch gestures");
                intent.putExtra("url","https://github.com/chrisbanes/PhotoView/");
                startActivity(intent);
            }
        }, 405, 443, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,WebViewActivity.class);
                intent.putExtra("name","RefreshLayout that support for OverScroll and better than iOS. 支持下拉刷新和上拉加载的RefreshLayout,自带越界回弹效果，支持RecyclerView,AbsListView,ScrollView,WebView");
                intent.putExtra("url","https://github.com/lcodecorex/TwinklingRefreshLayout");
                startActivity(intent);
            }
        }, 444, 481, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,WebViewActivity.class);
                intent.putExtra("name","A beautiful, fluid, and customizable dialogs API");
                intent.putExtra("url","https://github.com/afollestad/material-dialogs");
                startActivity(intent);
            }
        }, 482, 530, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,WebViewActivity.class);
                intent.putExtra("name","Provides simple annotation-based API to handle runtime permissions");
                intent.putExtra("url","https://github.com/hotchemi/PermissionsDispatcher");
                startActivity(intent);
            }
        }, 531, 579, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,WebViewActivity.class);
                intent.putExtra("name","董庆的博客");
                intent.putExtra("url","http://dongqing.website");
                startActivity(intent);
            }
        },span.length()-14,span.length()-11,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,WebViewActivity.class);
                intent.putExtra("name","董庆的Github");
                intent.putExtra("url","https://github.com/a741762308");
                startActivity(intent);
            }
        },span.length()-6,span.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textAbout.setText(span);
        textAbout.setMovementMethod(LinkMovementMethod.getInstance());

        tvVersion = (TextView) findViewById(R.id.tv_version);
        tvVersion.setText("Version "+BuildConfig.VERSION_NAME);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
