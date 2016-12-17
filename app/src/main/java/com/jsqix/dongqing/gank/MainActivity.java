package com.jsqix.dongqing.gank;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.jsqix.dongqing.gank.adapter.TabFragmentAdapter;
import com.jsqix.dongqing.gank.app.BaseAty;
import com.jsqix.dongqing.gank.app.MyApp;
import com.jsqix.dongqing.gank.fragment.CommonFragment;
import com.jsqix.dongqing.gank.fragment.FuliFragment;
import com.jsqix.dongqing.gank.fragment.RecommendFragment;
import com.jsqix.dongqing.gank.theme.Theme;
import com.jsqix.dongqing.gank.theme.ThemeUtils;
import com.jsqix.dongqing.gank.utils.MarketUtils;
import com.jsqix.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class MainActivity extends BaseAty
        implements NavigationView.OnNavigationItemSelectedListener, ColorChooserDialog.ColorCallback, View.OnClickListener {
    private long lastTime = 0;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initViewPage();
        initClick();
    }

    private void initClick() {
        TextView set = (TextView) findViewById(R.id.btn_set);
        TextView theme = (TextView) findViewById(R.id.btn_theme);
        set.setOnClickListener(this);
        theme.setOnClickListener(this);
    }

    private void initViewPage() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.view_page);
        List<String> titles = new ArrayList<>();
        titles.add("推荐");
        titles.add("Android");
        titles.add("iOS");
        titles.add("福利");
        titles.add("前端");
        titles.add("休息视频");
        titles.add("拓展资源");
        titles.add("App");
        titles.add("瞎推荐");
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            Fragment fragment;
            if (titles.get(i).equals("推荐")) {
                fragment = new RecommendFragment();
            } else if (titles.get(i).equals("福利")) {
                fragment = new FuliFragment();
            } else {
                fragment = new CommonFragment();
            }
            Bundle bundle = new Bundle();
            bundle.putString("type", titles.get(i));
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        TabFragmentAdapter adapter = new TabFragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            long currentTick = System.currentTimeMillis();
            if (currentTick - lastTime > 1500) {
                Utils.makeToast(this, "再按一次退出");
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            showShare();
        } else if (id == R.id.nav_send) {
            evaluate();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
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


}
