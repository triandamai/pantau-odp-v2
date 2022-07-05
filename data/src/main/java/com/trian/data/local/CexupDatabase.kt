package com.trian.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.trian.data.local.room.AppSettingDao
import com.trian.data.entities.AppSetting

/**
 * Local Database Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

@Database(
    entities = [
        AppSetting::class
    ],
    version = 44,
    exportSchema = false
)
//@TypeConverters(
//    TypeConverter::class,
//    DataConverter::class
//)
abstract class CexupDatabase : RoomDatabase(){
    abstract fun settingDao(): AppSettingDao


    companion object{
        const val DATABASE_NAME = "cexup_db"
    }
}