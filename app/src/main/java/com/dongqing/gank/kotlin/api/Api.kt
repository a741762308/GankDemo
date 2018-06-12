package com.dongqing.gank.kotlin.api

import com.google.gson.GsonBuilder
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class Api {

    private var retrofit: Retrofit
    var service: ApiService

    //构造方法私有
    private constructor() {
        val okHttpClient = RetrofitUrlManager.getInstance().with(OkHttpClient.Builder())
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(netInterceptor)
                .build()

        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create()

        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()

        RetrofitUrlManager.getInstance().putDomain("gank", GNAK_URL)
        RetrofitUrlManager.getInstance().putDomain("douban", DOUBAN_URL)
        RetrofitUrlManager.getInstance().putDomain("youku", YOUKU_URL)
        RetrofitUrlManager.getInstance().putDomain("weather", WEATHER_URL)

        service = retrofit.create(ApiService::class.java)
    }

   private var netInterceptor: Interceptor = Interceptor { chain ->
        val request = chain.request()
        val response = chain.proceed(request)
        response.newBuilder()
                .build()
    }

    //在访问HttpMethods时创建单例
    private object SingletonHolder {
        val INSTANCE = Api()
    }

    companion object {

        const val BASE_URL = "http://gank.io/"
        const val GNAK_URL = "http://gank.io/"
        const val DOUBAN_URL = "http://api.douban.com/"
        const val YOUKU_URL = "https://openapi.youku.com/"
        const val WEATHER_URL = "https://api.thinkpage.cn/"
        val TYPE_IMAGE = MediaType.parse("image/*")
        const val UTF_8 = "UTF-8"
        const val PAGE_SIZE = 20
        const val DEFAULT_TIMEOUT = 30L

        //获取单例
        val instance: Api
            get() = SingletonHolder.INSTANCE
    }

}