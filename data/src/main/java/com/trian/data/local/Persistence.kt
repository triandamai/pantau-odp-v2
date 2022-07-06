package com.trian.data.local

import android.content.SharedPreferences
import com.google.gson.Gson
/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */
class Persistence(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson = Gson()
) {
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    /**
     * Token
     * */
    private val key_token = "key_token"
    fun setToken(token: String) {
        editor.putString(key_token, token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(key_token, null)
    }

    fun dropToken() {
        editor.remove(key_token)
        editor.apply()
    }
    /**
     * function that can store dynamic data and key
     *
     * */
    fun setItem(key: String, value: Any) {
        when (value) {
            is Boolean -> editor.putBoolean(key, value)
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
        }
        editor.apply()
    }

    fun getItemBoolean(key: String): Boolean = sharedPreferences.getBoolean(key, false)
    fun getItemString(key: String): String? = sharedPreferences.getString(key, null)
    fun getItemInt(key: String): Int = sharedPreferences.getInt(key, 0)
    fun getItemInt(key: String, def: Int): Int = sharedPreferences.getInt(key, def)



    private val key_order_date = "date_order"
    fun setOrderDate(type: String, date: String) {
        editor.putString("$type$key_order_date", date)
        editor.apply()
    }

    fun getOrderDate(type: String): String? {
        return sharedPreferences.getString("$type$key_order_date", "")
    }

    /**
     * SignOut for both User and Nurse
     *
     * */
    fun signOut() {
        editor
            .clear()
            .apply()
    }
}

