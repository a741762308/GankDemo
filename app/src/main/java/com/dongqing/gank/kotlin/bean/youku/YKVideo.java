package com.dongqing.gank.kotlin.bean.youku;

import java.util.List;

/**
 * Created by dongqing on 2017/1/16.
 */

public class YKVideo {

    /**
     * total : 1917
     * page : 1
     * count : 20
     * videos : [{"id":"XMjQ0NDAzNTgyMA==","title":"cf穿越火线cf陈子豪cf系列精彩解说：平民装照样超神！","link":"http://v.youku.com/v_show/id_XMjQ0NDAzNTgyMA==.html","thumbnail":"http://r4.ykimg.com/05420408587BC29A6A0A3F04CF6A6405","bigThumbnail":"http://r4.ykimg.com/05410408587BC29A6A0A3F04CF6A6405","thumbnail_v2":"http://r4.ykimg.com/05420408587BC29A6A0A3F04CF6A6405","duration":443,"category":"电影","state":"normal","view_count":9225113,"favorite_count":0,"comment_count":0,"up_count":0,"down_count":0,"published":"2017-01-16 02:13:19","user":{"id":1080821339,"name":null,"link":"http://i.youku.com/u/UNDMyMzI4NTM1Ng=="},"operation_limit":[],"streamtypes":["3gphd","flvhd","hd","hd2","hd3"],"public_type":"all","tags":"","day_vv":0}]
     */

    private int total;
    private int page;
    private int count;
    private List<VideosBean> videos;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<VideosBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideosBean> videos) {
        this.videos = videos;
    }

    public static class VideosBean {
        /**
         * id : XMjQ0NDAzNTgyMA==
         * title : cf穿越火线cf陈子豪cf系列精彩解说：平民装照样超神！
         * link : http://v.youku.com/v_show/id_XMjQ0NDAzNTgyMA==.html
         * thumbnail : http://r4.ykimg.com/05420408587BC29A6A0A3F04CF6A6405
         * bigThumbnail : http://r4.ykimg.com/05410408587BC29A6A0A3F04CF6A6405
         * thumbnail_v2 : http://r4.ykimg.com/05420408587BC29A6A0A3F04CF6A6405
         * duration : 443
         * category : 电影
         * state : normal
         * view_count : 9225113
         * favorite_count : 0
         * comment_count : 0
         * up_count : 0
         * down_count : 0
         * published : 2017-01-16 02:13:19
         * user : {"id":1080821339,"name":null,"link":"http://i.youku.com/u/UNDMyMzI4NTM1Ng=="}
         * operation_limit : []
         * streamtypes : ["3gphd","flvhd","hd","hd2","hd3"]
         * public_type : all
         * tags :
         * day_vv : 0
         */

        private String id;
        private String title;
        private String link;
        private String thumbnail;
        private String bigThumbnail;
        private String thumbnail_v2;
        private int duration;
        private String category;
        private String state;
        private int view_count;
        private int favorite_count;
        private int comment_count;
        private int up_count;
        private int down_count;
        private String published;
        private UserBean user;
        private String public_type;
        private String tags;
        private int day_vv;
        private List<?> operation_limit;
        private List<String> streamtypes;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getBigThumbnail() {
            return bigThumbnail;
        }

        public void setBigThumbnail(String bigThumbnail) {
            this.bigThumbnail = bigThumbnail;
        }

        public String getThumbnail_v2() {
            return thumbnail_v2;
        }

        public void setThumbnail_v2(String thumbnail_v2) {
            this.thumbnail_v2 = thumbnail_v2;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getView_count() {
            return view_count;
        }

        public void setView_count(int view_count) {
            this.view_count = view_count;
        }

        public int getFavorite_count() {
            return favorite_count;
        }

        public void setFavorite_count(int favorite_count) {
            this.favorite_count = favorite_count;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getUp_count() {
            return up_count;
        }

        public void setUp_count(int up_count) {
            this.up_count = up_count;
        }

        public int getDown_count() {
            return down_count;
        }

        public void setDown_count(int down_count) {
            this.down_count = down_count;
        }

        public String getPublished() {
            return published;
        }

        public void setPublished(String published) {
            this.published = published;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getPublic_type() {
            return public_type;
        }

        public void setPublic_type(String public_type) {
            this.public_type = public_type;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public int getDay_vv() {
            return day_vv;
        }

        public void setDay_vv(int day_vv) {
            this.day_vv = day_vv;
        }

        public List<?> getOperation_limit() {
            return operation_limit;
        }

        public void setOperation_limit(List<?> operation_limit) {
            this.operation_limit = operation_limit;
        }

        public List<String> getStreamtypes() {
            return streamtypes;
        }

        public void setStreamtypes(List<String> streamtypes) {
            this.streamtypes = streamtypes;
        }

        public static class UserBean {
            /**
             * id : 1080821339
             * name : null
             * link : http://i.youku.com/u/UNDMyMzI4NTM1Ng==
             */

            private int id;
            private Object name;
            private String link;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }
    }
}
