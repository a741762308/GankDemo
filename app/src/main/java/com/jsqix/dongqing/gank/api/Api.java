package com.jsqix.dongqing.gank.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public static final String BASE_URL = "http://gank.io/api/";
    public static final String GNAK_URL = "http://gank.io/api/";
    public static final String DOUBAN_URL = "http://api.douban.com/v2/movie/";
    public final static MediaType TYPE_IMAGE = MediaType.parse("image/*");
    public final static String UTF_8 = "UTF-8";
    public final static int PAGE_SIZE = 20;
    public static final int DEFAULT_TIMEOUT = 30;

    public Retrofit retrofit;
    public ApiService service;

    public static final Api GANK = new Api(GNAK_URL);
    public static final Api DOUBAN = new Api(DOUBAN_URL);

    public Api() {
    }

    //构造方法私有
    private Api(String baseUrl) {


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();


        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
//                .addConverterFactory(JsonConverterFactory.create(gson))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        service = retrofit.create(ApiService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();

    }

    //获取单例
    public static Api getInstance() {
        return SingletonHolder.INSTANCE;
    }

}