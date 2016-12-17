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
    public final static MediaType TYPE_IMAGE = MediaType.parse("image/*");
    public final static String UTF_8 = "UTF-8";
    public final static int PAGE_SIZE=20;
    public static final int DEFAULT_TIMEOUT = 30;

    public Retrofit retrofit;
    public ApiService service;


    //构造方法私有
    private Api() {


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
                .baseUrl(BASE_URL)
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