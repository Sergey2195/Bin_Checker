package com.ssv.binchecker.data.remoteDataSource.entity

import com.google.gson.annotations.SerializedName

data class BinNumber(
    @SerializedName("length")
    var len: Int? = null,

    @SerializedName("luhn")
    var luhn: Boolean? = null

)