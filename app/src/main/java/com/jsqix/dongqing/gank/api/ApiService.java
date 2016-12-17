package com.jsqix.dongqing.gank.api;

import com.jsqix.dongqing.gank.bean.CategorylData;
import com.jsqix.dongqing.gank.bean.GankData;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiService {
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

    /**
     * 下载图片
     *
     * @param fileUrl
     * @return
     */
    @GET
    Observable<ResponseBody> downloadPicFromNet(@Url String fileUrl);
}