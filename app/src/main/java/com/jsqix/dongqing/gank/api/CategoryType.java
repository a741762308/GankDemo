package com.jsqix.dongqing.gank.api;

/**
 * Created by dongqing on 2016/12/7.
 */

public enum CategoryType {
    TYPE_ANDROID("Android"), TYPE_IOS("iOS"), TYPE_休息视频("休息视频"),
    TYPE_福利("福利"), TYPE_拓展资源("拓展资源"), TYPE_前端("前端"),
    TYPE_App("App"), TYPE_瞎推荐("瞎推荐"), TYPE_ALL("all");
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    CategoryType(String name) {
        this.name = name;
    }
}
