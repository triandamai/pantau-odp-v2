package com.trian.data.utils.utils


import android.annotation.SuppressLint
import kotlin.streams.asSequence

@SuppressLint("NewApi")
fun getRandomPassword():String{
    val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    return java.util.Random().ints(8, 0, source.length)
        .asSequence()
        .map(source::get)
        .joinToString("")
}