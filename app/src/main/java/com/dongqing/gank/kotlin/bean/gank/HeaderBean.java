package com.dongqing.gank.kotlin.bean.gank;

public class HeaderBean {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public HeaderBean(String title) {
        this.title = title;
    }
}