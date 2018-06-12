package com.dongqing.gank.kotlin.bean.gank;

import java.util.List;

public class RecentBean {
    private HeaderBean headerBean;
    private List<ImgDataBean> list;

    public HeaderBean getHeaderBean() {
        return headerBean;
    }

    public void setHeaderBean(HeaderBean headerBean) {
        this.headerBean = headerBean;
    }

    public List<ImgDataBean> getList() {
        return list;
    }

    public void setList(List<ImgDataBean> list) {
        this.list = list;
    }
}