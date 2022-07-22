package com.trian.data.models.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DistrictResponse(
    @SerializedName("id")
    var id:String="",
    @SerializedName("kecamatan")
    var dsitrict:String=""
)
