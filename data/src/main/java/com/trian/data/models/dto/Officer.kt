package com.trian.data.models.dto

import androidx.annotation.Keep

@Keep
data class Officer(
    var uid:String="",
    var email:String="",

    var districtId:String="",
    var districtName:String="",

    var villageId:String="",
    var villageName:String="",

    var name:String="",
    var nip:String="",
    var opd:String="",
    var level:String="",
    var createdAt:Long=0,
    var updatedAt:Long=0
)
