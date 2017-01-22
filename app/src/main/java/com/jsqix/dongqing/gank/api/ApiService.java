package com.jsqix.dongqing.gank.api;

import com.jsqix.dongqing.gank.bean.douban.DoubanFilm;
import com.jsqix.dongqing.gank.bean.douban.FilmInfo;
import com.jsqix.dongqing.gank.bean.gank.CategorylData;
import com.jsqix.dongqing.gank.bean.gank.GankData;
import com.jsqix.dongqing.gank.bean.weather.DailyWeather;
import com.jsqix.dongqing.gank.bean.weather.NowWeather;
import com.jsqix.dongqing.gank.bean.youku.YKProgram;
import com.jsqix.dongqing.gank.bean.youku.YKProgramCategory;
import com.jsqix.dongqing.gank.bean.youku.YKProgramVideo;
import com.jsqix.dongqing.gank.bean.youku.YKVideo;
import com.jsqix.dongqing.gank.bean.youku.YKVideoCategory;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
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
    @GET("v2/movie/in_theaters")
    Observable<DoubanFilm> getFilmWell();

    /**
     * 即将上线的电影
     *
     * @return
     */
    @GET("v2/movie/coming_soon")
    Observable<DoubanFilm> getFilmCome();

    /**
     * 电影条目信息
     *
     * @return
     */
    @GET("v2/movie/subject/{id}")
    Observable<FilmInfo> getFilmInfo(@Path("id") String id);

    //优酷 api

    /**
     * 优酷视频分类
     *
     * @return
     */
    @GET("v2/schemas/video/category.json")
    Observable<YKVideoCategory> getYKVideoCategoryData();


    /**
     * 根据分类获取视频列表
     *
     * @param client_id
     * @param category
     * @param page
     * @param count
     * @return
     */
    @GET("v2/videos/by_category.json")
    Observable<YKVideo> getYKViedoByCategory(@Query("client_id") String client_id, @Query("category") String category, @Query("page") int page, @Query("count") int count);

    /**
     * 优酷节目分类
     *
     * @return
     */
    @GET("v2/schemas/show/category.json")
    Observable<YKProgramCategory> getYKProgramCategoryData();

    /**
     * 根据分类获取节目列表
     *
     * @param client_id
     * @param category
     * @param page
     * @param count
     * @return
     */
    @GET("v2/shows/by_category.json")
    Observable<YKProgram> getYkProgrammeByCategory(@Query("client_id") String client_id, @Query("category") String category, @Query("page") int page, @Query("count") int count);

    /**
     * 根据节目查询视频信息
     *
     * @param paras
     * @return
     */
    @GET("router/rest.json")
    Observable<YKProgramVideo> getYKVideoByProgram(@QueryMap Map<String, Object> paras);

    //天气 api

    /**
     * 实时天气
     *
     * @param key
     * @param location
     * @return
     */
    @GET("v3/weather/now.json")
    Observable<NowWeather> getNowWeather(@Query("key") String key, @Query("location") String location);

    /**
     * 天气预报
     *
     * @param key
     * @param location
     * @param start
     * @param days
     * @return
     */
    @GET("v3/weather/daily.json")
    Observable<DailyWeather> getDailyWeather(@Query("key") String key, @Query("location") String location, @Query("start") int start, @Query("days") int days);

    /**
     * 下载图片
     *
     * @param fileUrl
     * @return
     */
    @GET
    Observable<ResponseBody> downloadPicFromNet(@Url String fileUrl);


}