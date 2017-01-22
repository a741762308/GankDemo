package com.jsqix.dongqing.gank;


import android.Manifest;
import android.animation.Animator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.jsqix.dongqing.gank.api.Api;
import com.jsqix.dongqing.gank.app.BaseActivity;
import com.jsqix.dongqing.gank.app.MyApp;
import com.jsqix.dongqing.gank.bean.weather.DailyWeather;
import com.jsqix.dongqing.gank.bean.weather.NowWeather;
import com.jsqix.dongqing.gank.fragment.HomeFragment;
import com.jsqix.dongqing.gank.fragment.WebFragment;
import com.jsqix.dongqing.gank.fragment.douban.DoubanFragment;
import com.jsqix.dongqing.gank.fragment.youku.YoukuFragment;
import com.jsqix.dongqing.gank.theme.Theme;
import com.jsqix.dongqing.gank.theme.ThemeUtils;
import com.jsqix.dongqing.gank.utils.DateUtil;
import com.jsqix.dongqing.gank.utils.MarketUtils;
import com.jsqix.dongqing.gank.utils.StringUtils;
import com.jsqix.dongqing.gank.utils.Utils;

import cn.sharesdk.onekeyshare.OnekeyShare;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@RuntimePermissions
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ColorChooserDialog.ColorCallback, View.OnClickListener {
    private long lastTime = 0;
    private DrawerLayout drawer;
    private FloatingActionButton floatingActionButton;
    private TextView locateCity;
    private AMapLocationClient mLocationClient;
    public AMapLocationClientOption mLocationOption;

    private int[] icons_weather = new int[]{R.mipmap.weather_0, R.mipmap.weather_1, R.mipmap.weather_2,
            R.mipmap.weather_3, R.mipmap.weather_4, R.mipmap.weather_5, R.mipmap.weather_6, R.mipmap.weather_7,
            R.mipmap.weather_8, R.mipmap.weather_9, R.mipmap.weather_10, R.mipmap.weather_11, R.mipmap.weather_12,
            R.mipmap.weather_13, R.mipmap.weather_14, R.mipmap.weather_15, R.mipmap.weather_16, R.mipmap.weather_17,
            R.mipmap.weather_18, R.mipmap.weather_19, R.mipmap.weather_20, R.mipmap.weather_21, R.mipmap.weather_22,
            R.mipmap.weather_23, R.mipmap.weather_24, R.mipmap.weather_25, R.mipmap.weather_26, R.mipmap.weather_27,
            R.mipmap.weather_28, R.mipmap.weather_29, R.mipmap.weather_30, R.mipmap.weather_31, R.mipmap.weather_32,
            R.mipmap.weather_33, R.mipmap.weather_34, R.mipmap.weather_35, R.mipmap.weather_36, R.mipmap.weather_37,
            R.mipmap.weather_38, R.mipmap.weather_99};
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        locateCity = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_city);

        replace(new HomeFragment());
        initClick();
        if (!StringUtils.isEmpty(aCache.getAsString("city"))) {
            getWeather();
            locateCity.setText(aCache.getAsString("city"));
        }
        MainActivityPermissionsDispatcher.onAllowWithCheck(this);
    }

    private void initLocate() {
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(10*60*1000);
        mLocationClient = new AMapLocationClient(mContext);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
//                Log.v("locate:", new Gson().toJson(aMapLocation));
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        aCache.put("city", aMapLocation.getCity());
                        getWeather();
                        locateCity.setText(aMapLocation.getCity());
                    } else {
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        });
    }

    private void getWeather() {
//        getNowWeather();
        getDailyWeather();

    }

    private void getDailyWeather() {
        Api.getInstance().WEATHER.service.getDailyWeather("hx5bcn1nsnrwv3yp", aCache.getAsString("city"), 0, 1)
                .subscribeOn(Schedulers.io())
                .map(new Func1<DailyWeather, DailyWeather.ResultsBean>() {
                    @Override
                    public DailyWeather.ResultsBean call(DailyWeather dailyWeather) {
                        return dailyWeather.getResults().get(0);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DailyWeather.ResultsBean>() {
                    @Override
                    public void onCompleted() {
                        Log.v("success:", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("failure:", e.toString());
                    }

                    @Override
                    public void onNext(DailyWeather.ResultsBean resultsBean) {
                        Log.v("result:", new Gson().toJson(resultsBean));
                        DailyWeather.ResultsBean.DailyBean daily = resultsBean.getDaily().get(0);
                        ImageView iv = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.iv_weather);
                        TextView w = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_weather);
                        TextView t = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_temperature);
                        if (DateUtil.getCurrentDateTimeStatus() == DateUtil.TIME.PM) {
                            iv.setImageResource(icons_weather[StringUtils.toInt(daily.getCode_night())]);
                            w.setText(daily.getText_night());
                        } else {
                            iv.setImageResource(icons_weather[StringUtils.toInt(daily.getCode_day())]);
                            w.setText(daily.getText_day());
                        }
                        t.setText(daily.getLow() + "/" + daily.getHigh() + "°C");
                    }
                });
    }

    private void getNowWeather() {
        Api.getInstance().WEATHER.service.getNowWeather("hx5bcn1nsnrwv3yp", aCache.getAsString("city"))
                .subscribeOn(Schedulers.io())
                .map(new Func1<NowWeather, NowWeather.ResultsBean>() {
                    @Override
                    public NowWeather.ResultsBean call(NowWeather nowWeather) {
                        return nowWeather.getResults().get(0);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NowWeather.ResultsBean>() {
                    @Override
                    public void onCompleted() {
                        Log.v("success:", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("failure:", e.toString());
                    }

                    @Override
                    public void onNext(NowWeather.ResultsBean resultsBean) {
                        Log.v("result:", new Gson().toJson(resultsBean));
                    }
                });
    }

    private void initClick() {
        TextView set = (TextView) findViewById(R.id.btn_set);
        TextView theme = (TextView) findViewById(R.id.btn_theme);
        set.setOnClickListener(this);
        theme.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            long currentTick = System.currentTimeMillis();
            if (currentTick - lastTime > 1500) {
                Snackbar.make(floatingActionButton, "再按一次退出程序", Snackbar.LENGTH_SHORT).show();
                lastTime = currentTick;
            } else {
                MyApp.getInstance().exitApp();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
        }

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            replace(new HomeFragment());
            getSupportActionBar().setTitle("干货首页");
        } else if (id == R.id.nav_xiandu) {
            replace(new WebFragment());
            getSupportActionBar().setTitle("干货闲读");
        } else if (id == R.id.nav_douban) {
            replace(new DoubanFragment());
            getSupportActionBar().setTitle("豆瓣电影");
        } else if (id == R.id.nav_youku) {
            replace(new YoukuFragment());
            getSupportActionBar().setTitle("优酷首页");
        } else if (id == R.id.nav_share) {
            showShare();
        } else if (id == R.id.nav_send) {
            evaluate();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment).addToBackStack(null).commit();
    }


    /**
     * 分享
     */
    private void showShare() {
        String downUrl = "http://fir.im/38zp";
        if ("tencent".equals(BuildConfig.FLAVOR)) {
            downUrl = "http://a.app.qq.com/o/simple.jsp?pkgname=" + getPackageName();
        } else if ("qh360".equals(BuildConfig.FLAVOR)) {
            downUrl = "http://openbox.mobilem.360.cn/qcms/view/t/detail?sid=3598872";
        } else if ("baidu".equals(BuildConfig.FLAVOR)) {
            downUrl = "http://mobile.baidu.com/item?docid=10617876";
        }
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
//        oks.setTitle(getIntent().getStringExtra("name"));
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(downUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我正在使用【干货Gank】,还不错！赶紧下载吧！" + downUrl);
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(downUrl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(downUrl);
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(downUrl);

        // 启动分享GUI
        oks.show(this);
    }

    /**
     * 应用商店评价
     */
    private void evaluate() {
        if (MarketUtils.queryInstalledMarketPkgs(this).size() == 0) {
            Utils.makeToast(this, "您的手机暂未安装任何应用商店，请安装后再试");
        } else {
            MarketUtils.launchAppDetail(getPackageName(), "");
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_set:
                startActivity(new Intent(this, SetActivity.class));
                break;
            case R.id.btn_theme:
                new ColorChooserDialog.Builder(this, R.string.theme)
                        .customColors(R.array.colors, null)
                        .doneButton(R.string.ok)
                        .cancelButton(R.string.cancel)
                        .allowUserColorInput(false)
                        .allowUserColorInputAlpha(false)
                        .show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int selectedColor) {
        if (selectedColor == ThemeUtils.getThemeColor(this, R.attr.colorPrimary))
            return;
        if (selectedColor == getResources().getColor(R.color.colorPrimary)) {
            setTheme(R.style.AppTheme);
            ThemeUtils.setCurrentTheme(this, Theme.Default);

        } else if (selectedColor == getResources().getColor(R.color.colorBluePrimary)) {
            setTheme(R.style.BlueTheme);
            ThemeUtils.setCurrentTheme(this, Theme.Blue);

        } else if (selectedColor == getResources().getColor(R.color.colorRedPrimary)) {
            setTheme(R.style.RedTheme);
            ThemeUtils.setCurrentTheme(this, Theme.Red);

        } else if (selectedColor == getResources().getColor(R.color.colorBrownPrimary)) {
            setTheme(R.style.BrownTheme);
            ThemeUtils.setCurrentTheme(this, Theme.Brown);

        } else if (selectedColor == getResources().getColor(R.color.colorGreenPrimary)) {
            setTheme(R.style.GreenTheme);
            ThemeUtils.setCurrentTheme(this, Theme.Green);

        } else if (selectedColor == getResources().getColor(R.color.colorPurplePrimary)) {
            setTheme(R.style.PurpleTheme);
            ThemeUtils.setCurrentTheme(this, Theme.Purple);

        } else if (selectedColor == getResources().getColor(R.color.colorTealPrimary)) {
            setTheme(R.style.TealTheme);
            ThemeUtils.setCurrentTheme(this, Theme.Teal);

        } else if (selectedColor == getResources().getColor(R.color.colorPinkPrimary)) {
            setTheme(R.style.PinkTheme);
            ThemeUtils.setCurrentTheme(this, Theme.Pink);

        } else if (selectedColor == getResources().getColor(R.color.colorDeepPurplePrimary)) {
            setTheme(R.style.DeepPurpleTheme);
            ThemeUtils.setCurrentTheme(this, Theme.DeepPurple);

        } else if (selectedColor == getResources().getColor(R.color.colorOrangePrimary)) {
            setTheme(R.style.OrangeTheme);
            ThemeUtils.setCurrentTheme(this, Theme.Orange);

        } else if (selectedColor == getResources().getColor(R.color.colorIndigoPrimary)) {
            setTheme(R.style.IndigoTheme);
            ThemeUtils.setCurrentTheme(this, Theme.Indigo);

        } else if (selectedColor == getResources().getColor(R.color.colorLightGreenPrimary)) {
            setTheme(R.style.LightGreenTheme);
            ThemeUtils.setCurrentTheme(this, Theme.LightGreen);

        } else if (selectedColor == getResources().getColor(R.color.colorDeepOrangePrimary)) {
            setTheme(R.style.DeepOrangeTheme);
            ThemeUtils.setCurrentTheme(this, Theme.DeepOrange);

        } else if (selectedColor == getResources().getColor(R.color.colorLimePrimary)) {
            setTheme(R.style.LimeTheme);
            ThemeUtils.setCurrentTheme(this, Theme.Lime);

        } else if (selectedColor == getResources().getColor(R.color.colorBlueGreyPrimary)) {
            setTheme(R.style.BlueGreyTheme);
            ThemeUtils.setCurrentTheme(this, Theme.BlueGrey);

        } else if (selectedColor == getResources().getColor(R.color.colorCyanPrimary)) {
            setTheme(R.style.CyanTheme);
            ThemeUtils.setCurrentTheme(this, Theme.Cyan);

        } else if (selectedColor == getResources().getColor(R.color.colorNightPrimary)) {
            setTheme(R.style.NightTheme);
            ThemeUtils.setCurrentTheme(this, Theme.Night);

        }
        final View rootView = getWindow().getDecorView();
        rootView.setDrawingCacheEnabled(true);
        rootView.buildDrawingCache(true);

        final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        if (null != localBitmap && rootView instanceof ViewGroup) {
            final View tmpView = new View(getApplicationContext());
            tmpView.setBackgroundDrawable(new BitmapDrawable(getResources(), localBitmap));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) rootView).addView(tmpView, params);
            tmpView.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    ThemeUtils.changeTheme(rootView, getTheme());
                    System.gc();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    ((ViewGroup) rootView).removeView(tmpView);
                    localBitmap.recycle();
                    new MaterialDialog.Builder(mContext)
                            .title("提示")
                            .content("更换主题需要重启应用，是否立即重启?")
                            .positiveText("立即")
                            .negativeText("取消")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    finish();
                                    startActivity(new Intent(mContext, WelcomeActivity.class));
                                }
                            })
                            .show();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
        }
    }


    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void onAllow() {
        initLocate();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void requestPermission(final PermissionRequest request) {
        request.proceed();
    }

    @OnPermissionDenied({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void onDeny() {
        Utils.makeToast(this, "请授予定位权限");
    }

    @OnNeverAskAgain({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void onAsk() {
        Utils.makeToast(this, "请到应用详情页权限管理中授予权限！");
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }
}
