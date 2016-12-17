package com.jsqix.dongqing.gank.bean;

import java.util.List;

/**
 * Created by dongqing on 2016/12/7.
 * 带图片的 返回数据
 */

public class ImgDataBean extends DataBean {
    /**
     * _id : 584507cb421aa939b8353628
     * createdAt : 2016-12-05T14:23:07.517Z
     * desc : 自定义SeekBar，进度变化由可视化气泡样式呈现，定制化程度较高，适合大部分需求
     * images : ["http://img.gank.io/d67113ac-1d4c-4291-b0ab-c0ca32834f58"]
     * publishedAt : 2016-12-06T11:33:36.433Z
     * source : web
     * type : Android
     * url : https://github.com/woxingxiao/BubbleSeekBar
     * used : true
     * who : Xiao
     */
    private List<String> images;


    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
