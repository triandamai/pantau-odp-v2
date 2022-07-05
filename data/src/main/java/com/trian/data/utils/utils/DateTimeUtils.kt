package com.trian.data.utils.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import org.joda.time.DateTime
import org.joda.time.Days
import org.joda.time.Period
import org.joda.time.format.DateTimeFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit
import kotlin.math.floor

/**
 * Main Activity
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 28/09/2021
 **/


fun getTodayTimeStamp(): Long {
    return DateTime().millis
}

fun Long.getPreviousDate(): Long {
    return DateTime(this).minusDays(1).withTimeAtStartOfDay().millis
}

fun Long.getNextDate(): Long {
    return DateTime(this).plusDays(1).withTimeAtStartOfDay().millis
}

fun getLastDayTimeStamp(): Long {
    return DateTime().withTimeAtStartOfDay().millis
}

//https://stackoverflow.com/questions/14053079/simpledateformat-returns-24-hour-date-how-to-get-12-hour-date
@SuppressLint("SimpleDateFormat")
fun Long.formatDate(pattern: String = ""): String {
    if (pattern.isBlank()) {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this)
    }
    return SimpleDateFormat(pattern).format(this)

}

@SuppressLint("NewApi")
fun LocalDate?.formatDate(pattern: String = ""): String {
    if (this == null) return ""
    if (pattern.isBlank()) return this.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    return this.format(DateTimeFormatter.ofPattern(pattern))
}

@SuppressLint("NewApi")
fun LocalDateTime.formatDate(pattern: String = ""): String {

    if (pattern.isBlank()) return this.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    return this.format(DateTimeFormatter.ofPattern(pattern))
}

@SuppressLint("SimpleDateFormat")
fun Long.formatHoursMinute(): String {
    return SimpleDateFormat("HH:mm").format(this)
}

//https://stackoverflow.com/questions/20331163/how-to-format-joda-time-datetime-to-only-mm-dd-yyyy
fun Long.formatReadableDate(): String {
    return DateTime(this).toString(DateTimeFormat.longDate())
}

fun String.toMillis(): Long {
    return DateTime(this).millis
}

fun getTimeStampFromDate(year: Int, month: Int, day: Int): Long {
    return DateTime(year, month, day, 0, 0).plus(1).millis
}

fun Long.durationWith(date: Long): Long {
    val from = DateTime(this)
    val to = DateTime(date)
    val duration = Period(from, to)
    return duration.minutes.toLong()
}

fun Long.getAgePregnancy(current: Long): String {
    val from = DateTime(this)
    val to = DateTime(current)

    val days = Days.daysBetween(from.toLocalDateTime(), to.toLocalDateTime())

    val week = floor((days.days / 7).toDouble())
    val sisa = floor((days.days % 7).toDouble())

    return "${week.toInt()} Week ${sisa.toInt()} Day"

}

fun Long.indexDecisionObgyn(hpht: Long): Int {
    val from = DateTime(hpht)
    val to = DateTime(this)

    val days = Days.daysBetween(from.toLocalDateTime(), to.toLocalDateTime())

    val week = floor((days.days / 7).toDouble())

    return week.toInt()
}

@SuppressLint("SimpleDateFormat")
fun Long.isDateBeforeToday(): Boolean {
    val currentDate = DateTime(getTodayTimeStamp())
    val orderDate = DateTime(this)
    if (orderDate.isBefore(currentDate)) {
        return false
    }
    return true
}

fun Long.getHpl(): Long {
    val date = DateTime(this)
    if (date.monthOfYear > 3) {
        //maret keatas
        val day = (date.dayOfMonth + 7)
        val month = if (date.monthOfYear > 3) (date.monthOfYear - 3) else date.monthOfYear
        val year = (date.year + 1)

        val finalDate = DateTime(year, month, day, 0, 0)
        return finalDate.millis
    } else {
        //maret kebawah
        val day = (date.dayOfMonth + 7)
        val month = (date.monthOfYear + 9)
        val year = date.year
        val finalDate = DateTime(year, month, day, 0, 0)
        return finalDate.millis
    }


}


//convert time to milli seconds
fun Long.formatTime(): String {
    //time to countdown - 1hr - 60secs
    // val TIME_COUNTDOWN: Long = 60000L
    val TIME_FORMAT = "%02d:%02d"
    return String.format(
        TIME_FORMAT,
        TimeUnit.MILLISECONDS.toMinutes(this),
        TimeUnit.MILLISECONDS.toSeconds(this) % 60
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.toLocalDateTime(): LocalDate? {
    return LocalDate.parse(this)
}

@RequiresApi(Build.VERSION_CODES.O)
fun getMonth(initialDate: LocalDate): List<LocalDate> {
    val month = mutableListOf<LocalDate>()
    for (i in 0..11) {
        month += initialDate.plusMonths(i.toLong())
    }
    return month
}

@RequiresApi(Build.VERSION_CODES.O)
fun getYears(initialDate: LocalDate): List<LocalDate> {
    val years = mutableListOf<LocalDate>()
    for (i in 0..200) {
        years += initialDate.minusYears(i.toLong())
    }
    return years
}

//get 30 days for reschedule
@SuppressLint("NewApi")
fun getDays(initialDate: LocalDate): List<LocalDate> {
    val days = mutableListOf<LocalDate>()
    for (i in 0..30) {
        days += initialDate.plusDays(i.toLong())
    }
    return days
}

@RequiresApi(Build.VERSION_CODES.O)
fun List<LocalDate>.parseDateToInt(): List<Int> {
    val dates = mutableListOf<Int>()
    this.forEach {
        dates += it.dayOfMonth
    }
    return dates
}