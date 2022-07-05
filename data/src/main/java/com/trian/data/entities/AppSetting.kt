package com.trian.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

@Entity(tableName = "tb_app_setting")
data class AppSetting(
    @PrimaryKey(autoGenerate = true)
    var id: Int=1,
    var theme:Int =0,
    var interval:Int=0,
    var goalSleepHour:Int=6,
    var goalSleepMinute:Int=20,
    var goalTargetStep:Int=100,
    var goalTargetCalorie:Int=0,
    var goalTargetDistance:Int=0,
    var SBP:Int=111,
    var DBP:Int=75,
    var devices: String="",

    )