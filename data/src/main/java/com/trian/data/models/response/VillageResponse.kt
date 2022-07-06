package com.trian.data.models.response

import com.google.gson.annotations.SerializedName

data class VillageResponse(
    @SerializedName("id")
    var id:String="",
    @SerializedName("kelurahan")
    var village:String=""
)
