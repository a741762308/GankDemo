package com.jsqix.dongqing.gank.bean.youku;

import java.util.List;

/**
 * Created by dongqing on 2017/1/16.
 */

public class YKProgram {

    /**
     * total : 2802
     * shows : [{"id":"b869e6568e9311e6b32f","name":"奇星记之鲜衣怒马少年时","link":"http://www.youku.com/show_page/id_zb869e6568e9311e6b32f.html","play_link":"http://v.youku.com/v_show/id_XMTg5MTYyOTQ5Mg==.html","last_play_link":"http://v.youku.com/v_show/id_XMjI3NjYyMDcyOA==.html","poster":"https://r1.ykimg.com/050D0000586C8B0467BC3C06EA08F29F","thumbnail":"https://r1.ykimg.com/050B0000586C8AF367BC3C78A70B21BC","bigthumbnail":"https://r1.ykimg.com/050C0000586C8AF367BC3C78A70B21BC","streamtypes":["hd2","flv","hd","3gphd","hd3","mp5hd","mp5hd2"],"hasvideotype":["正片","预告片","花絮","MV","首映式"],"completed":0,"episode_count":"51","episode_updated":"28","category":"电视剧","view_count":"597924546","score":"9.781","paid":1,"published":"2017-01-03","released":"2016-00-00","comment_count":"19513","favorite_count":"39680","lastupdate":"2017-01-16 16:01:11"}]
     */

    private String total;
    private List<ShowsBean> shows;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ShowsBean> getShows() {
        return shows;
    }

    public void setShows(List<ShowsBean> shows) {
        this.shows = shows;
    }

    public static class ShowsBean {
        /**
         * id : b869e6568e9311e6b32f
         * name : 奇星记之鲜衣怒马少年时
         * link : http://www.youku.com/show_page/id_zb869e6568e9311e6b32f.html
         * play_link : http://v.youku.com/v_show/id_XMTg5MTYyOTQ5Mg==.html
         * last_play_link : http://v.youku.com/v_show/id_XMjI3NjYyMDcyOA==.html
         * poster : https://r1.ykimg.com/050D0000586C8B0467BC3C06EA08F29F
         * thumbnail : https://r1.ykimg.com/050B0000586C8AF367BC3C78A70B21BC
         * bigthumbnail : https://r1.ykimg.com/050C0000586C8AF367BC3C78A70B21BC
         * streamtypes : ["hd2","flv","hd","3gphd","hd3","mp5hd","mp5hd2"]
         * hasvideotype : ["正片","预告片","花絮","MV","首映式"]
         * completed : 0
         * episode_count : 51
         * episode_updated : 28
         * category : 电视剧
         * view_count : 597924546
         * score : 9.781
         * paid : 1
         * published : 2017-01-03
         * released : 2016-00-00
         * comment_count : 19513
         * favorite_count : 39680
         * lastupdate : 2017-01-16 16:01:11
         */

        private String id;
        private String name;
        private String link;
        private String play_link;
        private String last_play_link;
        private String poster;
        private String thumbnail;
        private String bigthumbnail;
        private int completed;
        private String episode_count;
        private String episode_updated;
        private String category;
        private String view_count;
        private String score;
        private int paid;
        private String published;
        private String released;
        private String comment_count;
        private String favorite_count;
        private String lastupdate;
        private List<String> streamtypes;
        private List<String> hasvideotype;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getPlay_link() {
            return play_link;
        }

        public void setPlay_link(String play_link) {
            this.play_link = play_link;
        }

        public String getLast_play_link() {
            return last_play_link;
        }

        public void setLast_play_link(String last_play_link) {
            this.last_play_link = last_play_link;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getBigthumbnail() {
            return bigthumbnail;
        }

        public void setBigthumbnail(String bigthumbnail) {
            this.bigthumbnail = bigthumbnail;
        }

        public int getCompleted() {
            return completed;
        }

        public void setCompleted(int completed) {
            this.completed = completed;
        }

        public String getEpisode_count() {
            return episode_count;
        }

        public void setEpisode_count(String episode_count) {
            this.episode_count = episode_count;
        }

        public String getEpisode_updated() {
            return episode_updated;
        }

        public void setEpisode_updated(String episode_updated) {
            this.episode_updated = episode_updated;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getView_count() {
            return view_count;
        }

        public void setView_count(String view_count) {
            this.view_count = view_count;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public int getPaid() {
            return paid;
        }

        public void setPaid(int paid) {
            this.paid = paid;
        }

        public String getPublished() {
            return published;
        }

        public void setPublished(String published) {
            this.published = published;
        }

        public String getReleased() {
            return released;
        }

        public void setReleased(String released) {
            this.released = released;
        }

        public String getComment_count() {
            return comment_count;
        }

        public void setComment_count(String comment_count) {
            this.comment_count = comment_count;
        }

        public String getFavorite_count() {
            return favorite_count;
        }

        public void setFavorite_count(String favorite_count) {
            this.favorite_count = favorite_count;
        }

        public String getLastupdate() {
            return lastupdate;
        }

        public void setLastupdate(String lastupdate) {
            this.lastupdate = lastupdate;
        }

        public List<String> getStreamtypes() {
            return streamtypes;
        }

        public void setStreamtypes(List<String> streamtypes) {
            this.streamtypes = streamtypes;
        }

        public List<String> getHasvideotype() {
            return hasvideotype;
        }

        public void setHasvideotype(List<String> hasvideotype) {
            this.hasvideotype = hasvideotype;
        }
    }
}
