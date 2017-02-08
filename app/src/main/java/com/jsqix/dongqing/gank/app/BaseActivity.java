package com.jsqix.dongqing.gank.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jsqix.dongqing.gank.R;
import com.jsqix.dongqing.gank.theme.Theme;
import com.jsqix.dongqing.gank.theme.ThemeUtils;
import com.jsqix.dongqing.gank.utils.ACache;

/**
 * Created by dongqing on 2016/12/14.
 */

public class BaseActivity extends AppCompatActivity {
    public Activity mContext;
    public ACache aCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp.addToActivityList(this);
        MyApp.setCrashHandler(this);
        aCache = ACache.get(this);
        onPreCreate();
        mContext=this;

    }

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
//    }

    private void onPreCreate() {
        Theme theme = ThemeUtils.getCurrentTheme(this);
        switch (theme) {
            case Default:
                setTheme(R.style.AppTheme);
                break;
            case Blue:
                setTheme(R.style.BlueTheme);
                break;
            case Red:
                setTheme(R.style.RedTheme);
                break;
            case Brown:
                setTheme(R.style.BrownTheme);
                break;
            case Green:
                setTheme(R.style.GreenTheme);
                break;
            case Purple:
                setTheme(R.style.PurpleTheme);
                break;
            case Teal:
                setTheme(R.style.TealTheme);
                break;
            case Pink:
                setTheme(R.style.PinkTheme);
                break;
            case DeepPurple:
                setTheme(R.style.DeepPurpleTheme);
                break;
            case Orange:
                setTheme(R.style.OrangeTheme);
                break;
            case Indigo:
                setTheme(R.style.IndigoTheme);
                break;
            case LightGreen:
                setTheme(R.style.LightGreenTheme);
                break;
            case Lime:
                setTheme(R.style.LimeTheme);
                break;
            case DeepOrange:
                setTheme(R.style.DeepOrangeTheme);
                break;
            case Cyan:
                setTheme(R.style.CyanTheme);
                break;
            case BlueGrey:
                setTheme(R.style.BlueGreyTheme);
                break;
            case Night:
                setTheme(R.style.NightTheme);
                break;
        }
    }
}
