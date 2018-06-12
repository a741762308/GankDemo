package com.dongqing.gank.kotlin.api

import com.dongqing.gank.kotlin.bean.douban.DoubanFilm
import com.dongqing.gank.kotlin.bean.douban.FilmInfo
import com.dongqing.gank.kotlin.bean.gank.CategorylData
import com.dongqing.gank.kotlin.bean.gank.GankData
import com.dongqing.gank.kotlin.bean.weather.DailyWeather
import com.dongqing.gank.kotlin.bean.weather.NowWeather
import com.dongqing.gank.kotlin.bean.youku.*
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiService {

    //豆瓣 api

    /**
     * 正在热映的电影
     *
     * @return
     */
    @Headers("Domain-Name: douban")
    @GET("v2/movie/in_theaters")
    fun filmWell(@Query("start") start: Int, @Query("count") count: Int): Observable<DoubanFilm>

    /**
     * 即将上线的电影
     *
     * @return
     */
    @Headers("Domain-Name: douban")
    @GET("v2/movie/coming_soon")
    fun filmCome(@Query("start") start: Int, @Query("count") count: Int): Observable<DoubanFilm>

    /**
     * 电影条目信息
     *
     * @return
     */
    @Headers("Domain-Name: douban")
    @GET("v2/movie/subject/{id}")
    fun getFilmInfo(@Path("id") id: String): Observable<FilmInfo>

    //优酷 api

    /**
     * 优酷视频分类
     *
     * @return
     */
    @Headers("Domain-Name: youku")
    @GET("v2/schemas/video/category.json")
    fun ykVideoCategoryData(): Observable<YKVideoCategory>

    /**
     * 优酷节目分类
     *
     * @return
     */
    @Headers("Domain-Name: youku")
    @GET("v2/schemas/show/category.json")
    fun ykProgramCategoryData(): Observable<YKProgramCategory>


    /**
     * 根据分类获取视频列表
     *
     * @param client_id
     * @param category
     * @param page
     * @param count
     * @return
     */
    @Headers("Domain-Name: youku")
    @GET("v2/videos/by_category.json")
    fun getYKViedoByCategory(@Query("client_id") client_id: String, @Query("category") category: String, @Query("page") page: Int, @Query("count") count: Int): Observable<YKVideo>

    /**
     * 根据分类获取节目列表
     *
     * @param client_id
     * @param category
     * @param page
     * @param count
     * @return
     */
    @Headers("Domain-Name: youku")
    @GET("v2/shows/by_category.json")
    fun getYkProgrammeByCategory(@Query("client_id") client_id: String, @Query("category") category: String, @Query("page") page: Int, @Query("count") count: Int): Observable<YKProgram>

    /**
     * 根据节目查询视频信息
     *
     * @param paras
     * @return
     */
    @Headers("Domain-Name: youku")
    @GET("router/rest.json")
    fun getYKVideoByProgram(@QueryMap paras: Map<String, Any>): Observable<YKProgramVideo>


    //gank api

    /**
     * 每日数据
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    @Headers("Domain-Name: gank")
    @GET("api/day/{year}/{month}/{day}")
    fun getDayData(@Path("year") year: Int, @Path("month") month: Int, @Path("day") day: Int): Observable<GankData>

    /**
     * 分类 数据
     *
     * @param type
     * @param page
     * @return
     */
    @Headers("Domain-Name: gank")
    @GET("api/data/{category}/" + Api.PAGE_SIZE + "/{page_num}")
    fun getCategoryData(@Path("category") type: String, @Path("page_num") page: Int): Observable<CategorylData>


    //天气 api

    /**
     * 实时天气
     *
     * @param key
     * @param location
     * @return
     */
    @Headers("Domain-Name: weather")
    @GET("v3/weather/now.json")
    fun getNowWeather(@Query("key") key: String, @Query("location") location: String): Observable<NowWeather>

    /**
     * 天气预报
     *
     * @param key
     * @param location
     * @param start
     * @param days
     * @return
     */
    @Headers("Domain-Name: weather")
    @GET("v3/weather/daily.json")
    fun getDailyWeather(@Query("key") key: String, @Query("location") location: String, @Query("start") start: Int, @Query("days") days: Int): Observable<DailyWeather>

    /**
     * 下载图片
     *
     * @param fileUrl
     * @return
     */
    @GET
    fun downloadPicFromNet(@Url fileUrl: String): Observable<ResponseBody>


}