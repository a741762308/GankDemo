package com.jsqix.dongqing.gank;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.jsqix.dongqing.gank.theme.Theme;
import com.jsqix.dongqing.gank.theme.ThemeUtils;
import com.jsqix.utils.BaseActivity;


public class WelcomeActivity extends BaseActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        onPreCreate();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }, 1000);
    }

    private void onPreCreate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        Theme theme = ThemeUtils.getCurrentTheme(this);
        switch (theme) {
            case Default:
                image.setImageResource(R.drawable.default_background);
                break;
            case Blue:
                image.setImageResource(R.drawable.blue_background);
                break;
            case Red:
                image.setImageResource(R.drawable.red_background);
                break;
            case Brown:
                image.setImageResource(R.drawable.brown_background);
                break;
            case Green:
                image.setImageResource(R.drawable.green_background);
                break;
            case Purple:
                image.setImageResource(R.drawable.purple_background);
                break;
            case Teal:
                image.setImageResource(R.drawable.teal_background);
                break;
            case Pink:
                image.setImageResource(R.drawable.pink_background);
                break;
            case DeepPurple:
                image.setImageResource(R.drawable.deep_purple_background);
                break;
            case Orange:
                image.setImageResource(R.drawable.orange_background);
                break;
            case Indigo:
                image.setImageResource(R.drawable.indigo_background);
                break;
            case LightGreen:
                image.setImageResource(R.drawable.light_green_background);
                break;
            case Lime:
                image.setImageResource(R.drawable.lime_background);
                break;
            case DeepOrange:
                image.setImageResource(R.drawable.deep_orange_background);
                break;
            case Cyan:
                image.setImageResource(R.drawable.cyan_background);
                break;
            case BlueGrey:
                image.setImageResource(R.drawable.blue_grey_background);
                break;
            case Night:
                image.setImageResource(R.drawable.night_background);
                break;
        }
    }

    private void initView() {
        image = (ImageView) findViewById(R.id.image);
    }
}
