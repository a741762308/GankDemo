package com.jsqix.dongqing.gank.api;

import com.jsqix.dongqing.gank.bean.douban.DoubanFilm;
import com.jsqix.dongqing.gank.bean.douban.FilmInfo;
import com.jsqix.dongqing.gank.bean.gank.CategorylData;
import com.jsqix.dongqing.gank.bean.gank.GankData;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiService {

    //gank api

    /**
     * 每日数据
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    @GET("day/{year}/{month}/{day}")
    Observable<GankData> getDayData(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    /**
     * 分类 数据
     *
     * @param type
     * @param page
     * @return
     */
    @GET("data/{category}/" + Api.PAGE_SIZE + "/{page_num}")
    Observable<CategorylData> getCategoryData(@Path("category") String type, @Path("page_num") int page);

    //豆瓣 api

    /**
     * 正在热映的电影
     *
     * @return
     */
    @GET("in_theaters")
    Observable<DoubanFilm> getFilmWell();

    /**
     * 即将上线的电影
     *
     * @return
     */
    @GET("coming_soon")
    Observable<DoubanFilm> getFilmCome();

    /**
     * 电影条目信息
     *
     * @return
     */
    @GET("subject/{id}")
    Observable<FilmInfo> getFilmInfo(@Path("id") String id);

    /**
     * 下载图片
     *
     * @param fileUrl
     * @return
     */
    @GET
    Observable<ResponseBody> downloadPicFromNet(@Url String fileUrl);


}