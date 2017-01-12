package com.jsqix.dongqing.gank.bean.gank;

import java.util.List;

/**
 * gank 每日数据
 * Created by dongqing on 2016/12/7.
 */

public class GankData extends BaseData {

    /**
     * category : ["休息视频","Android","iOS","福利","前端"]
     * results : {"Android":[{"_id":"584507cb421aa939b8353628","createdAt":"2016-12-05T14:23:07.517Z","desc":"自定义SeekBar，进度变化由可视化气泡样式呈现，定制化程度较高，适合大部分需求","images":["http://img.gank.io/d67113ac-1d4c-4291-b0ab-c0ca32834f58"],"publishedAt":"2016-12-06T11:33:36.433Z","source":"web","type":"Android","url":"https://github.com/woxingxiao/BubbleSeekBar","used":true,"who":"Xiao"},{"_id":"58452aea421aa939befafb09","createdAt":"2016-12-05T16:52:58.22Z","desc":"《你的名字》同款日记APP。作者不是我~","images":["http://img.gank.io/ceb8a9c3-aa52-4e11-8610-44ca7ce5f753"],"publishedAt":"2016-12-06T11:33:36.433Z","source":"web","type":"Android","url":"https://github.com/erttyy8821/MyDiary","used":true,"who":"galois"},{"_id":"58461d71421aa939b58d31e4","createdAt":"2016-12-06T10:07:45.342Z","desc":"Android 翻页效果库","publishedAt":"2016-12-06T11:33:36.433Z","source":"chrome","type":"Android","url":"https://github.com/eschao/android-PageFlip","used":true,"who":"代码家"},{"_id":"58461f4f421aa939bb4637fe","createdAt":"2016-12-06T10:15:43.517Z","desc":"Java 实现的高性能布隆过滤器！","publishedAt":"2016-12-06T11:33:36.433Z","source":"chrome","type":"Android","url":"https://github.com/jparkie/PDD","used":true,"who":"代码家"}],"iOS":[{"_id":"58457b67421aa939b835362c","createdAt":"2016-12-05T22:36:23.86Z","desc":"动手实现一个具有语法高亮功能的iOS Markdown编辑器","images":["http://img.gank.io/0e8ec5eb-bb66-4a89-9a0a-cc4211310df0"],"publishedAt":"2016-12-06T11:33:36.433Z","source":"web","type":"iOS","url":"http://www.jianshu.com/p/beb0078ce341","used":true,"who":null},{"_id":"58457bc4421aa939b835362d","createdAt":"2016-12-05T22:37:56.916Z","desc":"对KVO机制的分析","publishedAt":"2016-12-06T11:33:36.433Z","source":"web","type":"iOS","url":"http://www.jianshu.com/p/348e98825df0","used":true,"who":null},{"_id":"58461da8421aa939b8353631","createdAt":"2016-12-06T10:08:40.839Z","desc":"iOS 权限认证辅助库","publishedAt":"2016-12-06T11:33:36.433Z","source":"chrome","type":"iOS","url":"https://github.com/ennioma/arek","used":true,"who":"代码家"}],"休息视频":[{"_id":"5842a53c421aa939b835361e","createdAt":"2016-12-03T18:58:04.7Z","desc":"【C菌】老司机激情碰♂撞! 那些最奇葩智障的游戏集合!【第21期】","publishedAt":"2016-12-06T11:33:36.433Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av7357496/","used":true,"who":"LHF"}],"前端":[{"_id":"58461c62421aa939befafb13","createdAt":"2016-12-06T10:03:14.743Z","desc":"基于 Vue.js 实现的高质量 UI 组件","publishedAt":"2016-12-06T11:33:36.433Z","source":"chrome","type":"前端","url":"https://www.iviewui.com/","used":true,"who":"代码家"},{"_id":"58461d42421aa939bb4637fa","createdAt":"2016-12-06T10:06:58.449Z","desc":"格式化用户文本输入格式。","publishedAt":"2016-12-06T11:33:36.433Z","source":"chrome","type":"前端","url":"http://nosir.github.io/cleave.js/","used":true,"who":"代码家"},{"_id":"58461de9421aa939bb4637fd","createdAt":"2016-12-06T10:09:45.565Z","desc":"CSS 实现的 Loading 效果","publishedAt":"2016-12-06T11:33:36.433Z","source":"chrome","type":"前端","url":"https://github.com/webkul/csspin","used":true,"who":"代码家"}],"福利":[{"_id":"58460694421aa939b58d31e3","createdAt":"2016-12-06T08:30:12.824Z","desc":"12-6","publishedAt":"2016-12-06T11:33:36.433Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1fagrnmiqm1j20u011hanr.jpg","used":true,"who":"daimajia "}]}
     */

    private ResultsBean results;
    private List<String> category;

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public static class ResultsBean {
        private List<ImgDataBean> Android;
        private List<ImgDataBean> iOS;
        private List<ImgDataBean> 休息视频;
        private List<ImgDataBean> 前端;
        private List<ImgDataBean> 福利;
        private List<ImgDataBean> 拓展资源;
        private List<ImgDataBean> 瞎推荐;
        private List<ImgDataBean> App;

        public List<ImgDataBean> getAndroid() {
            return Android;
        }

        public void setAndroid(List<ImgDataBean> android) {
            Android = android;
        }

        public List<ImgDataBean> getiOS() {
            return iOS;
        }

        public void setiOS(List<ImgDataBean> iOS) {
            this.iOS = iOS;
        }

        public List<ImgDataBean> get休息视频() {
            return 休息视频;
        }

        public void set休息视频(List<ImgDataBean> 休息视频) {
            this.休息视频 = 休息视频;
        }

        public List<ImgDataBean> get前端() {
            return 前端;
        }

        public void set前端(List<ImgDataBean> 前端) {
            this.前端 = 前端;
        }

        public List<ImgDataBean> get福利() {
            return 福利;
        }

        public void set福利(List<ImgDataBean> 福利) {
            this.福利 = 福利;
        }

        public List<ImgDataBean> get拓展资源() {
            return 拓展资源;
        }

        public void set拓展资源(List<ImgDataBean> 拓展资源) {
            this.拓展资源 = 拓展资源;
        }

        public List<ImgDataBean> get瞎推荐() {
            return 瞎推荐;
        }

        public void set瞎推荐(List<ImgDataBean> 瞎推荐) {
            this.瞎推荐 = 瞎推荐;
        }

        public List<ImgDataBean> getApp() {
            return App;
        }

        public void setApp(List<ImgDataBean> app) {
            App = app;
        }
    }
}
