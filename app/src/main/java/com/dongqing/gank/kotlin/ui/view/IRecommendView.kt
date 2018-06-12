package com.dongqing.gank.kotlin.ui.view

import com.dongqing.gank.kotlin.bean.gank.GankData

interface IRecommendView : IDataView<GankData.ResultsBean> {
    fun onUpdateDate()
}