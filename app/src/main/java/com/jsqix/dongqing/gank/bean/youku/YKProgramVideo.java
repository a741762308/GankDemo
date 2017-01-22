package com.jsqix.dongqing.gank.bean.youku;

import java.util.List;

/**
 * Created by dongqing on 2017/1/20.
 */

public class YKProgramVideo {

    /**
     * total : 60
     * index_cost : 0.025
     * error : 0
     * results : [{"thumburl":"https://r1.ykimg.com/05420408577264656A0A4F132DECD0A9","pk_video":"404943324","username":"终极一班","title":"终极一班4 01","userid":913179348,"videoid":"XMTYxOTc3MzI5Ng=="},{"thumburl":"https://r1.ykimg.com/05420408576F6D7C6A0A45045C78DB5B","pk_video":"404943918","username":"终极一班","title":"终极一班4 02","userid":913179348,"videoid":"XMTYxOTc3NTY3Mg=="},{"thumburl":"https://r1.ykimg.com/054204085774C3A16A0A4004E36A76C2","pk_video":"404944429","username":"终极一班","title":"终极一班4 03","userid":913179348,"videoid":"XMTYxOTc3NzcxNg=="},{"thumburl":"https://r1.ykimg.com/05420408576D83CC6A0A4C0455B6E7F7","pk_video":"404944917","username":"终极一班","title":"终极一班4 04","userid":913179348,"videoid":"XMTYxOTc3OTY2OA=="},{"thumburl":"https://r1.ykimg.com/05420408577263406A0A4104780F9690","pk_video":"404945465","username":"终极一班","title":"终极一班4 05","userid":913179348,"videoid":"XMTYxOTc4MTg2MA=="},{"thumburl":"https://r1.ykimg.com/054204085773C08C6A0A4B046E11599A","pk_video":"404945778","username":"终极一班","title":"终极一班4 06","userid":913179348,"videoid":"XMTYxOTc4MzExMg=="},{"thumburl":"https://r1.ykimg.com/05420408576D89756A0A4F133170F043","pk_video":"404946255","username":"终极一班","title":"终极一班4 07","userid":913179348,"videoid":"XMTYxOTc4NTAyMA=="},{"thumburl":"https://r1.ykimg.com/05420408576D88F56A0A3F045FD0C3A2","pk_video":"404946778","username":"终极一班","title":"终极一班4 08","userid":913179348,"videoid":"XMTYxOTc4NzExMg=="},{"thumburl":"https://r1.ykimg.com/05420408576D8B376A0A460459B489F9","pk_video":"404947259","username":"终极一班","title":"终极一班4 09","userid":913179348,"videoid":"XMTYxOTc4OTAzNg=="},{"thumburl":"https://r1.ykimg.com/05420408577A12876A0A4404AD0BD100","pk_video":"406915130","username":"终极一班","title":"终极一班4 10","userid":913179348,"videoid":"XMTYyNzY2MDUyMA=="}]
     * total_cost : 0.03676700592041
     * tokens : show_id:1800388c336c11e6b432 show_videotype:正片
     * orderby :
     * offset : 1
     */

    private String total;
    private String index_cost;
    private int error;
    private double total_cost;
    private String tokens;
    private String orderby;
    private int offset;
    private List<ResultsBean> results;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getIndex_cost() {
        return index_cost;
    }

    public void setIndex_cost(String index_cost) {
        this.index_cost = index_cost;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * thumburl : https://r1.ykimg.com/05420408577264656A0A4F132DECD0A9
         * pk_video : 404943324
         * username : 终极一班
         * title : 终极一班4 01
         * userid : 913179348
         * videoid : XMTYxOTc3MzI5Ng==
         */

        private String thumburl;
        private String pk_video;
        private String username;
        private String title;
        private int userid;
        private String videoid;

        public String getThumburl() {
            return thumburl;
        }

        public void setThumburl(String thumburl) {
            this.thumburl = thumburl;
        }

        public String getPk_video() {
            return pk_video;
        }

        public void setPk_video(String pk_video) {
            this.pk_video = pk_video;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getVideoid() {
            return videoid;
        }

        public void setVideoid(String videoid) {
            this.videoid = videoid;
        }
    }
}
