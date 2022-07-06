package com.trian.data.models.response

import com.google.gson.annotations.SerializedName

data class DistrictResponse(
    @SerializedName("id")
    var id:String="",
    @SerializedName("kecamatan")
    var dsitrict:String=""
)
