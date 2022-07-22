package com.trian.data.models.response

import androidx.annotation.Keep

@Keep
data class CreateOfficerResponse(
    var success:Boolean=false,
    var data:String="",
    var message:String=""
)
