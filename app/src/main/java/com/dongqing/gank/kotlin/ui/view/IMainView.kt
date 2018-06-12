package com.dongqing.gank.kotlin.ui.view

import android.view.View
import com.dongqing.gank.kotlin.bean.weather.DailyWeather

interface IMainView:IBaseView {
    fun showSnackbar(view: View, s: String)


    fun getWeather(result: DailyWeather.ResultsBean)
}