package com.dongqing.gank.kotlin.theme

import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView

object ViewAttributeUtil {

    fun getAttributeValue(attr: AttributeSet, paramInt: Int): Int {
        var value = -1
        val count = attr.attributeCount
        for (i in 0 until count) {
            if (attr.getAttributeNameResource(i) == paramInt) {
                val str = attr.getAttributeValue(i)
                if (null != str && str.startsWith("?")) {
                    value = Integer.valueOf(str.substring(1, str.length)).toInt()
                    return value
                }
            }
        }
        return value
    }

    fun getBackgroundAttibute(attr: AttributeSet): Int {
        return getAttributeValue(attr, android.R.attr.background)
    }

    fun getCheckMarkAttribute(attr: AttributeSet): Int {
        return getAttributeValue(attr, android.R.attr.checkMark)
    }

    fun getSrcAttribute(attr: AttributeSet): Int {
        return getAttributeValue(attr, android.R.attr.src)
    }

    fun getTextApperanceAttribute(attr: AttributeSet): Int {
        return getAttributeValue(attr, android.R.attr.textAppearance)
    }

    fun getDividerAttribute(attr: AttributeSet): Int {
        return getAttributeValue(attr, android.R.attr.divider)
    }

    fun getTextColorAttribute(attr: AttributeSet): Int {
        return getAttributeValue(attr, android.R.attr.textColor)
    }

    fun getTextLinkColorAttribute(attr: AttributeSet): Int {
        return getAttributeValue(attr, android.R.attr.textColorLink)
    }

    fun applyBackgroundDrawable(ci: ColorUiInterface?, theme: Resources.Theme, paramInt: Int) {
        val ta = theme.obtainStyledAttributes(intArrayOf(paramInt))
        val drawable = ta.getDrawable(0)
        ci?.view?.setBackgroundDrawable(drawable)
        ta.recycle()
    }

    fun applyImageDrawable(ci: ColorUiInterface?, theme: Resources.Theme, paramInt: Int) {
        val ta = theme.obtainStyledAttributes(intArrayOf(paramInt))
        val drawable = ta.getDrawable(0)
        if (null != ci && ci is ImageView) {
            (ci.view as ImageView).setImageDrawable(drawable)
        }
        ta.recycle()
    }

    fun applyTextAppearance(ci: ColorUiInterface?, theme: Resources.Theme, paramInt: Int) {
        val ta = theme.obtainStyledAttributes(intArrayOf(paramInt))
        val resourceId = ta.getResourceId(0, 0)
        if (null != ci && ci is TextView) {
            (ci.view as TextView).setTextAppearance(ci.view.context, resourceId)
        }
        ta.recycle()
    }

    fun applyTextColor(ci: ColorUiInterface?, theme: Resources.Theme, paramInt: Int) {
        val ta = theme.obtainStyledAttributes(intArrayOf(paramInt))
        val resourceId = ta.getColor(0, 0)
        if (null != ci && ci is TextView) {
            (ci.view as TextView).setTextColor(resourceId)
        }
        ta.recycle()
    }

    fun applyTextLinkColor(ci: ColorUiInterface?, theme: Resources.Theme, paramInt: Int) {
        val ta = theme.obtainStyledAttributes(intArrayOf(paramInt))
        val resourceId = ta.getColor(0, 0)
        if (null != ci && ci is TextView) {
            (ci.view as TextView).setLinkTextColor(resourceId)
        }
        ta.recycle()
    }

}