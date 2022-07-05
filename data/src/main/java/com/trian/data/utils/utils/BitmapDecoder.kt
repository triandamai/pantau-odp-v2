package com.trian.data.utils.utils

import android.app.Activity
import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.net.Uri
import android.util.DisplayMetrics
import android.view.View
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

/**
 * Main Activity
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 28/09/2021
 **/

fun Uri.getBitmap(c: ContentResolver): Bitmap? {
    return try {
        val input = c.openInputStream(this)
        BitmapFactory.decodeStream(input)
    } catch (e: Exception) {
        null
    }
}

fun Bitmap.reduceSize(): Bitmap {
    val byteArrayOutputStream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
    var options = 90
    while (byteArrayOutputStream.toByteArray().size / 1024 > 400) { //Loop if compressed picture is greater than 400kb, than to compression
        byteArrayOutputStream.reset() //Reset byteArrayOutputStream is empty byteArrayOutputStream
        this.compress(
            Bitmap.CompressFormat.JPEG,
            options,
            byteArrayOutputStream
        ) //The compression options%, storing the compressed data to the byteArrayOutputStream
        options -= 10 //Every time reduced by 10
    }
    val bitmapInputStream = ByteArrayInputStream(byteArrayOutputStream.toByteArray())
    return BitmapFactory.decodeStream(bitmapInputStream)
}

fun getwindowwidth(activity: Activity): Int {
    val displayMetrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}

fun getwindowheight(activity: Activity): Int {
    val displayMetrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.heightPixels
}

fun loadBitmapFromView(view: View): Bitmap? {
    val width: Int = view.width
    val height: Int = view.height
    if (height == 0 || width == 0) {
        return null
    }
    val createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(createBitmap)
    view.layout(0, 0, width, height)
    view.draw(canvas)
    return createBitmap
}
