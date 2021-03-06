package com.trian.data.models.dto

import androidx.annotation.Keep

@Keep
data class Citizen(
    var uid:String="",
    var name:String="",
    var placeOfBirth:String="",
    var dateOfBirth:String="",
    var address:String="",
    var religion:String="",
    var districtId:String="",
    var districtName:String="",
    var villageId:String="",
    var villageName:String="",
    var proffesion:String="",
    var gender:String="",
    var identityNumber:String="",
    var phoneNumber:String="",
    var officerNip:String="",
    var officerName:String="",
    var bloodType:String="",
    var createdAt:Long=0,
    var updatedAt:Long=0,
)


