package com.dongqing.gank.kotlin.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    val DATA_FORMAT_ZH = "yyyy年MM月dd日"
    val DATE_FORMAT_DEFAUlt = "yyyy-MM-dd"
    val TIME_FORMAT_DEFAUlt = "HH:mm:ss"
    val DATETIME_FORMAT_DEFAUlt = (DATE_FORMAT_DEFAUlt
            + " " + TIME_FORMAT_DEFAUlt)
    val DATE_FORMAT_DISPLAY = "dd/MM/yyyy"
    val DATE_FORMAT_DISPLAY2 = "yyyy/MM/dd"
    val TIME_FORMAT_DISPLAY = "HH:mm"
    val DATETIME_FORMAT_DISPLAY = (DATE_FORMAT_DISPLAY
            + " " + TIME_FORMAT_DISPLAY)

    val DATE_FORMAT_MYSQL = "yyyy-MM-dd"
    val TIME_FORMAT_MYSQL = "HH:mm:ss"
    val DATETIME_FORMAT_MYSQL = (DATE_FORMAT_MYSQL + " "
            + TIME_FORMAT_MYSQL)

    val currentDatetimeString: String?
        get() = dateToString(Date(), DATETIME_FORMAT_DEFAUlt)

    val currentDateString: String?
        get() = dateToString(Date(), DATE_FORMAT_DEFAUlt)

    val currentTimeString: String?
        get() = dateToString(Date(), TIME_FORMAT_DEFAUlt)

    val currentDateTimeStatus: Int
        get() {
            val time = currentTimeString
            val hour = StringUtils.toInt(time!!.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0])
            return if (hour > 5 && hour < 12) {
                TIME.AM
            } else if (hour > 12 && hour < 17) {
                TIME.NOON
            } else {
                TIME.PM
            }
        }

    fun dateToString(date: Date?, pattern: String): String? {
        if (date == null)
            return null
        val dateFormat = SimpleDateFormat(pattern)
        return dateFormat.format(date)
    }

    fun getCurrentDateString(type: String): String? {
        return dateToString(Date(), type)
    }

    fun getCurrentDateTimeAddByDay(day: Int): String? {
        val rightNow = Calendar.getInstance()
        rightNow.time = Date()
        rightNow.add(Calendar.DAY_OF_YEAR, day)// 日期加60天
        return dateToString(rightNow.time, DATETIME_FORMAT_DEFAUlt)
    }

    fun getCurrentDateAddByDay(day: Int): String? {
        val rightNow = Calendar.getInstance()
        rightNow.time = Date()
        rightNow.add(Calendar.DAY_OF_YEAR, day)// 日期加60天
        return dateToString(rightNow.time, DATE_FORMAT_DEFAUlt)
    }

    fun getCurrentDateTimeAddByDay(date: Date, day: Int): String? {
        val rightNow = Calendar.getInstance()
        rightNow.time = date
        rightNow.add(Calendar.DAY_OF_YEAR, day)// 日期加60天
        return dateToString(rightNow.time, DATETIME_FORMAT_DEFAUlt)
    }

    fun getCurrentDateAddByDay(date: Date, day: Int): String? {
        val rightNow = Calendar.getInstance()
        rightNow.time = date
        rightNow.add(Calendar.DAY_OF_YEAR, day)// 日期加60天
        return dateToString(rightNow.time, DATE_FORMAT_DEFAUlt)
    }

    /* 时间戳转换成字符窜 */
    fun getLongDateToString(time: Long, pattern: String): String? {
        return dateToString(Date(time), pattern)
    }

    /* 将字符串转为时间 */
    fun getStringToDate(time: String, pattern: String): Date {
        val sdf = SimpleDateFormat(pattern)
        var date = Date()
        try {
            date = sdf.parse(time)
        } catch (e: ParseException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        return date
    }

    /* 将字符串转为时间戳 */
    fun getStringToLongDate(time: String, pattern: String): Long {
        val sdf = SimpleDateFormat(pattern)
        var date = Date()
        try {
            date = sdf.parse(time)
        } catch (e: ParseException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        return date.time
    }

    fun isDate(time: String, pattern: String): Boolean {
        val sdf = SimpleDateFormat(pattern)
        try {
            sdf.parse(time)
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

        return true
    }

    fun stringToDate(date: String?, pattern: String): Date? {
        var date = date
        var pattern = pattern
        if (date == null)
            return null

        if (pattern === TIME_FORMAT_MYSQL) {
            pattern = DATETIME_FORMAT_MYSQL
            date = "2012-01-01 $date"
        }

        if (pattern === TIME_FORMAT_DISPLAY) {
            pattern = DATETIME_FORMAT_DISPLAY
            date = "01/01/2012 $date"
        }

        val dateFormat = getDateFormat(pattern)

        try {
            return dateFormat.parse(date)
        } catch (e: ParseException) {
        }

        return null
    }

    private fun getDateFormat(pattern: String): SimpleDateFormat {
        val simpleDateFormat = SimpleDateFormat(pattern,
                Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getDefault() // Use UTC timezone
        // throughout
        return simpleDateFormat
    }

    fun sqlDatetimeStringToDate(sqlDatetimeString: String): Date? {
        return stringToDate(sqlDatetimeString, DATETIME_FORMAT_MYSQL)
    }

    interface TIME {
        companion object {
            val AM = 1// 上午
            val NOON = 2// 下午
            val PM = 3// 晚上
        }

    }
}
