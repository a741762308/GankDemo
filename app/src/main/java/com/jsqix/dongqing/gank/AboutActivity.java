package com.jsqix.dongqing.gank;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.view.MenuItem;
import android.widget.TextView;

import com.jsqix.dongqing.gank.app.BaseAty;

public class AboutActivity extends BaseAty {

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
        span.setSpan(new URLSpan("Http://www.baidu.com"), 95, 99, Spanned.SPAN_POINT_MARK);
        textAbout.setText(span.toString());
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
