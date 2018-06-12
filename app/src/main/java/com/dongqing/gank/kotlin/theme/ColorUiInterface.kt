package com.dongqing.gank.kotlin.theme

import android.content.res.Resources
import android.view.View

/**
 * 换肤接口
 */
interface ColorUiInterface {


    val view: View

    fun setTheme(themeId: Resources.Theme)
}