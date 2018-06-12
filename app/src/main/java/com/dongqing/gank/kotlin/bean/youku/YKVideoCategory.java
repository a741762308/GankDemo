package com.dongqing.gank.kotlin.bean.youku;

import java.util.List;

/**
 * Created by dongqing on 2017/1/16.
 */

public class YKVideoCategory {

    private List<CategoriesBean> categories;

    public List<CategoriesBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesBean> categories) {
        this.categories = categories;
    }

    public static class CategoriesBean {
        /**
         * id : 97
         * term : TV
         * label : 电视剧
         * lang : zh_CN
         * genres : []
         */

        private int id;
        private String term;
        private String label;
        private String lang;
        private List<?> genres;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public List<?> getGenres() {
            return genres;
        }

        public void setGenres(List<?> genres) {
            this.genres = genres;
        }
    }
}
