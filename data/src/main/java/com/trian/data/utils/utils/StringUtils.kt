package com.trian.data.utils.utils

import java.text.NumberFormat
import java.util.*

fun String.capitalizeWords(): String = split(" ").map { it.replaceFirstChar(Char::titlecase) }.joinToString(" ")

fun Double.formatToRupiah(): String{
    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    return numberFormat.format(this).toString()
}