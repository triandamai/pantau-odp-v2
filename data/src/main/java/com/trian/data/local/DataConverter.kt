package com.trian.data.local

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object DataConverter {

}

inline fun <reified T> Gson.fromJson(json:String) = fromJson<T>(json,object :TypeToken<T>() {}.type)