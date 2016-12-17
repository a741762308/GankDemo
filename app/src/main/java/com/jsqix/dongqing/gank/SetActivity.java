package com.jsqix.dongqing.gank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.jsqix.dongqing.gank.app.BaseAty;
import com.jsqix.dongqing.gank.utils.GlideCacheUtil;
import com.jsqix.utils.Utils;

public class SetActivity extends BaseAty implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView tvFeedBack, tvClear;
    private RelativeLayout relClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvFeedBack = (TextView) findViewById(R.id.tv_feedBack);
        tvFeedBack.setOnClickListener(this);
        tvClear = (TextView) findViewById(R.id.tv_clear);
        relClear = (RelativeLayout) findViewById(R.id.rel_clear);
        relClear.setOnClickListener(this);
        tvClear.setText(GlideCacheUtil.getInstance().getCacheSize(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_feedBack:
                break;
            case R.id.rel_clear:
                new MaterialDialog.Builder(this)
                        .title("提示")
                        .content("确定要清除图片缓存？")
                        .positiveText("确定")
                        .negativeText("取消")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                Utils.makeToast(mContext, "正在清除");
                                GlideCacheUtil.getInstance().clearImageDiskCache(mContext,
                                        new GlideCacheUtil.CallBack() {
                                            @Override
                                            public void onSuccess() {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        tvClear.setText(GlideCacheUtil.getInstance().getCacheSize(mContext));
                                                        Utils.makeToast(mContext, "清除成功");
                                                    }
                                                });
                                            }
                                        });
                            }
                        })
                        .show();
                break;
        }
    }
}
