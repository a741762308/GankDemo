package com.dongqing.gank.kotlin.ui.view

interface IDataView<T> : IBaseView {
    fun onDataReceive(t: T)

    fun onDataError()
}