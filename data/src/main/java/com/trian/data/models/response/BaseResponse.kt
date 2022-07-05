package com.trian.data.models.response

data class BaseResponse<T>(
    var code: Int,
    var data: T,
    var message: String
)
