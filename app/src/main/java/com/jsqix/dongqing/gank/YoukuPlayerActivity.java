package com.jsqix.dongqing.gank;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jsqix.dongqing.gank.api.Api;
import com.jsqix.dongqing.gank.app.BaseActivity;
import com.jsqix.dongqing.gank.bean.youku.YKProgramVideo;
import com.jsqix.dongqing.gank.utils.Utils;
import com.jsqix.dongqing.gank.utils.YoukuUtils;
import com.jsqix.dongqing.gank.view.NestedWebView;
import com.youku.player.VideoDefinition;
import com.youku.player.base.PlayerErrorInfo;
import com.youku.player.base.PlayerListener;
import com.youku.player.base.YoukuPlayerView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zhy.view.flowlayout.TagView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import cn.sharesdk.onekeyshare.OnekeyShare;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class YoukuPlayerActivity extends BaseActivity {
    private PowerManager.WakeLock mWakeLock = null;
    private static final String POWER_LOCK = "YoukuPlayerActivity";

    private LinearLayout LayoutPlayer;
    private YoukuPlayerView playerView;
    private TagFlowLayout setTag;

    private CoordinatorLayout layoutWeb;
    private Toolbar toolbar;
    private NestedWebView webview;
    private ImageView screenBack;
//    private ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youku_player);
        initView();
    }

    private void initView() {
        LayoutPlayer = (LinearLayout) findViewById(R.id.play_layout);
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, POWER_LOCK);
        playerView = (YoukuPlayerView) findViewById(R.id.playView);
        playerView.attachActivity(this);
        playerView.setPreferVideoDefinition(VideoDefinition.VIDEO_HD);
        playerView.setPlayerListener(new MyPlayerListener());
        setTag = (TagFlowLayout) findViewById(R.id.set_tag);
        getVideoProgram();

        layoutWeb = (CoordinatorLayout) findViewById(R.id.web_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        screenBack= (ImageView) findViewById(R.id.screen_back);
        screenBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });
        webview = (NestedWebView) findViewById(R.id.webview);
//        progressbar = (ProgressBar) findViewById(progressbar);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setSupportMultipleWindows(true);
        settings.setUserAgentString("User-Agent: Mozilla/5.0 (Linux; Android 7.0; ZUK Z2131 Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/53.0.2785.49 Mobile MQQBrowser/6.2 TBS/043015 Safari/537.36 MicroMessenger/6.5.3.980 NetType/WIFI Language/zh_CN");
        webview.loadUrl(getIntent().getStringExtra("url"));
        webview.setWebChromeClient(new MyWebChrome());
        webview.setWebViewClient(new MyWebView());
    }


    private void getVideoProgram() {
        TreeMap<String, Object> paras = new TreeMap<>();
        paras.put("action", "youku.content.video.byprogram.get");
        paras.put("q", "show_id:" + getIntent().getStringExtra("id") + " show_videotype:正片");
        paras.put("fd", "");
        paras.put("pn", 1);
        paras.put("pl", 100);
        try {
            paras = YoukuUtils.get_sign(paras, "655690f8143987c6", "55357297598428d79746fd54cb491306");
        } catch (Exception e) {
        }

        Api.getInstance().YOUKU.service.getYKVideoByProgram(paras)
                .subscribeOn(Schedulers.io())
                .map(new Func1<YKProgramVideo, List<YKProgramVideo.ResultsBean>>() {
                    @Override
                    public List<YKProgramVideo.ResultsBean> call(YKProgramVideo ykProgramVideo) {
                        return ykProgramVideo.getResults();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<YKProgramVideo.ResultsBean>>() {
                    @Override
                    public void onCompleted() {
                        Log.v("success:", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("failure:", e.toString());
                    }

                    @Override
                    public void onNext(List<YKProgramVideo.ResultsBean> resultsBeen) {
                        Log.v("result:", new Gson().toJson(resultsBeen));
                        layoutWeb.setVisibility(View.GONE);
                        LayoutPlayer.setVisibility(View.VISIBLE);
                        if (resultsBeen != null && resultsBeen.size() > 1) {
                            final TagAdapter<YKProgramVideo.ResultsBean> tagAdapter = new TagAdapter<YKProgramVideo.ResultsBean>(resultsBeen) {
                                @Override
                                public View getView(FlowLayout parent, int position, YKProgramVideo.ResultsBean s) {
                                    TextView cb = (TextView) LayoutInflater.from(mContext).inflate(R.layout.tag_set, null);
                                    cb.setText(s.getTitle());
                                    return cb;
                                }
                            };
                            setTag.setAdapter(tagAdapter);
                            tagAdapter.setSelectedList(0);
                            playerView.playYoukuVideo(tagAdapter.getItem(0).getVideoid());
                            setTag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                                @Override
                                public boolean onTagClick(View view, int position, FlowLayout parent) {
                                    TagView tagView = (TagView) setTag.getChildAt(position);
                                    Set<Integer> set = new HashSet<>();
                                    if (tagView.isChecked()) {
                                        playerView.playYoukuVideo(tagAdapter.getItem(position).getVideoid());
                                        return true;
                                    } else {
                                        set.add(position);
                                        tagAdapter.setSelectedList(set);
                                    }
                                    return false;
                                }
                            });
                        } else {
                            playerView.playYoukuVideo(resultsBeen.get(0).getVideoid());
                        }

                    }
                });
    }

    // 添加播放器的监听器
    private class MyPlayerListener extends PlayerListener {
        @Override
        public void onComplete() {
            // TODO Auto-generated method stub
            super.onComplete();
        }

        @Override
        public void onError(int code, PlayerErrorInfo info) {
            // TODO Auto-generated method stub
            super.onError(code, info);
//            Utils.makeToast(mContext, info.getDesc());
            layoutWeb.setVisibility(View.VISIBLE);
            LayoutPlayer.setVisibility(View.GONE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        @Override
        public void OnCurrentPositionChanged(int msec) {
            // TODO Auto-generated method stub
            super.OnCurrentPositionChanged(msec);
        }

        @Override
        public void onVideoNeedPassword(int code) {
            // TODO Auto-generated method stub
            super.onVideoNeedPassword(code);
        }

        @Override
        public void onPrepared() {
            super.onPrepared();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        playerView.onPause();
        if (mWakeLock != null) {
            mWakeLock.release();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        playerView.onResume();
        if (null != mWakeLock && (!mWakeLock.isHeld())) {
            mWakeLock.acquire();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        playerView.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (layoutWeb.getVisibility() == View.VISIBLE) {
            if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                toolbar.setVisibility(View.VISIBLE);
                screenBack.setVisibility(View.GONE);
            } else {
                toolbar.setVisibility(View.GONE);
                screenBack.setVisibility(View.VISIBLE);
            }
        }
    }

    class MyWebChrome extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
//            if (newProgress == 100) {
//                progressbar.setVisibility(View.GONE);
//            } else {
//                if (progressbar.getVisibility() == View.GONE)
//                    progressbar.setVisibility(View.VISIBLE);
//                progressbar.setProgress(newProgress);
//            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            toolbar.setTitle(title);
        }

    }

    class MyWebView extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
            super.onReceivedSslError(view, handler, error);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_copy) {
            ClipboardManager cmb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            cmb.setPrimaryClip(ClipData.newPlainText(null, getIntent().getStringExtra("url")));
            Utils.makeToast(this, "已复制到剪贴板，快分享给你的好友吧");
        } else if (id == R.id.action_share) {
            showShare();
        } else if (id == android.R.id.home) {
            finish();
        }

        return true;
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(getIntent().getStringExtra("name"));
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(getIntent().getStringExtra("url"));
        // text是分享文本，所有平台都需要这个字段
        oks.setText(getIntent().getStringExtra("name"));
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(getIntent().getStringExtra("imgUrl") == null ? aCache.getAsString("imgUrl") : getIntent().getStringExtra("imgUrl"));
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(getIntent().getStringExtra("url"));
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("http://fir.im/38zp");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://fir.im/38zp");

        // 启动分享GUI
        oks.show(this);
    }

    @Override
    public void onBackPressed() {
        if (layoutWeb.getVisibility() == View.VISIBLE) {
            if (webview.canGoBack()) {
                webview.goBack();
            } else {
                finish();
            }
        } else {
            finish();
        }
    }
}

