package com.jsqix.dongqing.gank.bean.douban;

import java.util.List;

/**
 * Created by dongqing on 2017/1/12.
 */

public class FilmInfo {

    /**
     * rating : {"max":10,"average":7.4,"stars":"40","min":0}
     * reviews_count : 586
     * wish_count : 11922
     * douban_site :
     * year : 2016
     * images : {"small":"http://img6.douban.com/view/movie_poster_cover/ipst/public/p2403049086.jpg","large":"http://img6.douban.com/view/movie_poster_cover/lpst/public/p2403049086.jpg","medium":"http://img6.douban.com/view/movie_poster_cover/spst/public/p2403049086.jpg"}
     * alt : https://movie.douban.com/subject/25894431/
     * id : 25894431
     * mobile_url : https://movie.douban.com/subject/25894431/mobile
     * title : 星球大战外传：侠盗一号
     * do_count : null
     * share_url : http://m.douban.com/movie/subject/25894431
     * seasons_count : null
     * schedule_url : https://movie.douban.com/subject/25894431/cinema/
     * episodes_count : null
     * countries : ["美国"]
     * genres : ["动作","科幻","冒险"]
     * collect_count : 53052
     * casts : [{"alt":"https://movie.douban.com/celebrity/1013981/","avatars":{"small":"http://img6.douban.com/img/celebrity/small/42373.jpg","large":"http://img6.douban.com/img/celebrity/large/42373.jpg","medium":"http://img6.douban.com/img/celebrity/medium/42373.jpg"},"name":"菲丽希缇·琼斯","id":"1013981"},{"alt":"https://movie.douban.com/celebrity/1013867/","avatars":{"small":"http://img6.douban.com/img/celebrity/small/36123.jpg","large":"http://img6.douban.com/img/celebrity/large/36123.jpg","medium":"http://img6.douban.com/img/celebrity/medium/36123.jpg"},"name":"迭戈·鲁纳","id":"1013867"},{"alt":"https://movie.douban.com/celebrity/1025194/","avatars":{"small":"http://img6.douban.com/img/celebrity/small/10695.jpg","large":"http://img6.douban.com/img/celebrity/large/10695.jpg","medium":"http://img6.douban.com/img/celebrity/medium/10695.jpg"},"name":"甄子丹","id":"1025194"},{"alt":"https://movie.douban.com/celebrity/1000248/","avatars":{"small":"http://img6.douban.com/img/celebrity/small/5681.jpg","large":"http://img6.douban.com/img/celebrity/large/5681.jpg","medium":"http://img6.douban.com/img/celebrity/medium/5681.jpg"},"name":"本·门德尔森","id":"1000248"}]
     * current_season : null
     * original_title : Rogue One: A Star Wars Story
     * summary : 这是一个战火频燃、纷争不断的动荡时代，一群有志之士集结在一起，计划盗走帝国大规模杀伤性武器“死星”的设计图。这个在《星球大战》系列里非常著名的重点事件 ，让一群平凡普通人结成了同盟，决定为世界的改变做出贡献；而在绝密行动的进行中，他们也逐渐成长为顶天立地的英雄。
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1310557/","avatars":{"small":"http://img6.douban.com/img/celebrity/small/1351661374.56.jpg","large":"http://img6.douban.com/img/celebrity/large/1351661374.56.jpg","medium":"http://img6.douban.com/img/celebrity/medium/1351661374.56.jpg"},"name":"加里斯·爱德华斯","id":"1310557"}]
     * comments_count : 25155
     * ratings_count : 49482
     * aka : ["侠盗一号","星战外传1","侠盗一号：星球大战外传","星球大战：侠盗一号","罗格一号","Star Wars Anthology: Rogue One","Star Wars: Rogue One","Rogue One"]
     */

    private RatingBean rating;
    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private Object do_count;
    private String share_url;
    private Object seasons_count;
    private String schedule_url;
    private Object episodes_count;
    private int collect_count;
    private Object current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<String> countries;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<DirectorsBean> directors;
    private List<String> aka;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 7.4
         * stars : 40
         * min : 0
         */

        private int max;
        private double average;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : http://img6.douban.com/view/movie_poster_cover/ipst/public/p2403049086.jpg
         * large : http://img6.douban.com/view/movie_poster_cover/lpst/public/p2403049086.jpg
         * medium : http://img6.douban.com/view/movie_poster_cover/spst/public/p2403049086.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class CastsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1013981/
         * avatars : {"small":"http://img6.douban.com/img/celebrity/small/42373.jpg","large":"http://img6.douban.com/img/celebrity/large/42373.jpg","medium":"http://img6.douban.com/img/celebrity/medium/42373.jpg"}
         * name : 菲丽希缇·琼斯
         * id : 1013981
         */

        private String alt;
        private AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean {
            /**
             * small : http://img6.douban.com/img/celebrity/small/42373.jpg
             * large : http://img6.douban.com/img/celebrity/large/42373.jpg
             * medium : http://img6.douban.com/img/celebrity/medium/42373.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class DirectorsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1310557/
         * avatars : {"small":"http://img6.douban.com/img/celebrity/small/1351661374.56.jpg","large":"http://img6.douban.com/img/celebrity/large/1351661374.56.jpg","medium":"http://img6.douban.com/img/celebrity/medium/1351661374.56.jpg"}
         * name : 加里斯·爱德华斯
         * id : 1310557
         */

        private String alt;
        private AvatarsBeanX avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBeanX {
            /**
             * small : http://img6.douban.com/img/celebrity/small/1351661374.56.jpg
             * large : http://img6.douban.com/img/celebrity/large/1351661374.56.jpg
             * medium : http://img6.douban.com/img/celebrity/medium/1351661374.56.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }
}
