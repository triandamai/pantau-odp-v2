package com.trian.data.models.dto

data class Assessment(
    var citizenUid:String="",
    var tripHistory:String="",
    var placeOfTrip:String="",
    var isolation:Boolean=false,
    var safetyNet:Boolean=false,
    var behaviour:Boolean=false,
    var condition:String,
    var createdAt:Long=0,
    var updateAt:Long=0,
)
