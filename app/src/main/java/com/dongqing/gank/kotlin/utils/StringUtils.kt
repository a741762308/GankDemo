package com.dongqing.gank.kotlin.utils

import java.text.DecimalFormat
import java.util.regex.Pattern

object StringUtils {

    internal var df = DecimalFormat("######0.00")

    /***
     * @param obj
     * @return
     */
    fun isNull(obj: Any?): Boolean {
        if (obj == null)
            return true
        if (obj is String) {
            val input = obj.toString() + ""
            return isEmpty(input)
        }
        return true
    }

    fun isEmpty(input: String?): Boolean {
        if (input == null || "" == input)
            return true

        for (i in 0 until input.length) {
            val c = input[i]
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false
            }
        }
        return true
    }

    fun toInt(str: String, defValue: Int): Int {
        try {
            return Integer.parseInt(str)
        } catch (e: Exception) {
        }

        return defValue
    }

    fun toString(obj: Any?): String {
        return obj?.toString() ?: ""
    }

    fun toInt(obj: Any?): Int {
        return if (obj == null) 0 else toInt(obj.toString(), 0)
    }

    fun toNumber(s: String): String {
        val l = toLong(s)
        val a = l / 10000.0
        val b = l % 10000
        return if (a > 10000) {
            toFormat(a / 10000) + "亿"
        } else if (a < 1) {
            b.toString() + ""
        } else {
            toFormat(a) + "万"
        }
    }

    fun isNum(obj: Any?): Boolean {
        if (obj == null)
            return false
        val pattern = Pattern.compile("[0-9]*")
        return pattern.matcher(obj.toString()).matches()
    }

    fun toFormat(obj: Any): String {
        var format = df.format(toFloat(obj).toDouble())
        if (format.length == 3) {
            format = "0$format"
        }
        return format
    }

    fun toDouble(obj: Any?): Double {
        return if (obj == null) 0.0 else toDouble(obj.toString(), 0.0)
    }

    private fun toDouble(str: String, defValue: Double): Double {
        try {
            return java.lang.Double.parseDouble(str)
        } catch (e: Exception) {
        }

        return defValue
    }

    fun toFloat(obj: Any?): Float {
        return if (obj == null) 0f else toFloat(obj.toString(), 0f)
    }

    private fun toFloat(str: String, defValue: Float): Float {
        try {
            return java.lang.Float.parseFloat(str)
        } catch (e: Exception) {
        }

        return defValue
    }

    fun toLong(obj: String): Long {
        try {
            return java.lang.Long.parseLong(obj)
        } catch (e: Exception) {
        }

        return 0
    }

    fun toBool(b: String): Boolean {
        try {
            return java.lang.Boolean.parseBoolean(b)
        } catch (e: Exception) {
        }

        return false
    }

    fun isPhone(paramString: String): Boolean {
        /*          移动                                                   */    /*   电信            */   /*           联通                         */   /*  电信           */ /* 数据号段     */   /* 17号段     */
        val regExp = "^1(3[4-9]|5[012789]|8[23478])\\d{8}$|^18[019]\\d{8}$|^1(3[0-2]|5[56]|8[56])\\d{8}$|^1[35]3\\d{8}$|^14[57]\\d{8}$|^17[0678]\\d{8}$"
        val p = Pattern.compile(regExp)
        val m = p.matcher(paramString)
        return m.matches()
    }

    fun notPhone(paramString: String): Boolean {
        val regExp = "^1\\d{10}$"
        val p = Pattern.compile(regExp)
        val m = p.matcher(paramString)
        return !m.matches()
    }

    fun isEmail(paramString: String): Boolean {
        val regExp = "^([\\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$"
        val p = Pattern.compile(regExp)
        val m = p.matcher(paramString)
        return m.matches()
    }


}
