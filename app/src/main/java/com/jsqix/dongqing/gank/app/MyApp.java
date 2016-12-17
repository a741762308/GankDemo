package com.jsqix.dongqing.gank.app;

import com.jsqix.utils.FrameApplication;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by dongqing on 2016/12/13.
 */

public class MyApp extends FrameApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(this);
    }
}
