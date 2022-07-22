package com.trian.data.models.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class VillageResponse(
    @SerializedName("id")
    var id:String="",
    @SerializedName("kelurahan")
    var village:String=""
)
