package com.dongqing.gank.kotlin.theme

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView

import com.dongqing.gank.kotlin.utils.ACache
import com.dongqing.gank.kotlin.utils.StringUtils

import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

object ThemeUtils {
    /**
     * 获取当主题颜色
     * @param context
     * @param attrRes
     * @return
     */
    fun getThemeColor(context: Context, attrRes: Int): Int {
        val typedArray = context.obtainStyledAttributes(intArrayOf(attrRes))
        val color = typedArray.getColor(0, 0xffffff)
        typedArray.recycle()
        return color
    }

    /**
     * 从cache中读取当前主题
     * @param context
     * @return
     */
    fun getCurrentTheme(context: Context): Theme {
        var themeName = ACache.get(context).getAsString("app_theme")
        themeName = if (StringUtils.isEmpty(themeName)) Theme.Default.name else themeName
        return Theme.valueOf(themeName)
    }

    /**
     * 设置当前主题到cache
     * @param context
     * @param currentTheme
     */
    fun setCurrentTheme(context: Context, currentTheme: Theme) {
        ACache.get(context).put("app_theme", currentTheme.name)
    }

    /**
     * 切换应用主题
     *
     * @param rootView
     */
    fun changeTheme(rootView: View, theme: Resources.Theme) {
        if (rootView is ColorUiInterface) {
            (rootView as ColorUiInterface).setTheme(theme)
            if (rootView is ViewGroup) {
                val count = (rootView as ViewGroup).childCount
                for (i in 0 until count) {
                    changeTheme((rootView as ViewGroup).getChildAt(i), theme)
                }
            }
            if (rootView is AbsListView) {
                try {
                    val localField = AbsListView::class.java.getDeclaredField("mRecycler")
                    localField.isAccessible = true
                    val localMethod = Class.forName("android.widget.AbsListView\$RecycleBin").getDeclaredMethod("clear")
                    localMethod.isAccessible = true
                    localMethod.invoke(localField.get(rootView))
                } catch (e1: NoSuchFieldException) {
                    e1.printStackTrace()
                } catch (e2: ClassNotFoundException) {
                    e2.printStackTrace()
                } catch (e3: NoSuchMethodException) {
                    e3.printStackTrace()
                } catch (e4: IllegalAccessException) {
                    e4.printStackTrace()
                } catch (e5: InvocationTargetException) {
                    e5.printStackTrace()
                }

            }
        } else {
            if (rootView is ViewGroup) {
                val count = rootView.childCount
                for (i in 0 until count) {
                    changeTheme(rootView.getChildAt(i), theme)
                }
            }
            if (rootView is AbsListView) {
                try {
                    val localField = AbsListView::class.java.getDeclaredField("mRecycler")
                    localField.isAccessible = true
                    val localMethod = Class.forName("android.widget.AbsListView\$RecycleBin").getDeclaredMethod("clear")
                    localMethod.isAccessible = true
                    localMethod.invoke(localField.get(rootView))
                } catch (e1: NoSuchFieldException) {
                    e1.printStackTrace()
                } catch (e2: ClassNotFoundException) {
                    e2.printStackTrace()
                } catch (e3: NoSuchMethodException) {
                    e3.printStackTrace()
                } catch (e4: IllegalAccessException) {
                    e4.printStackTrace()
                } catch (e5: InvocationTargetException) {
                    e5.printStackTrace()
                }

            }
        }
    }

}