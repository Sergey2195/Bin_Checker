package com.ssv.binchecker.data.remoteDataSource.entity

import com.google.gson.annotations.SerializedName
import com.ssv.binchecker.data.remoteDataSource.entity.BinBank
import com.ssv.binchecker.data.remoteDataSource.entity.BinCountry
import com.ssv.binchecker.data.remoteDataSource.entity.BinNumber

data class BinResponse(
    @SerializedName("number")
    var binNumber: BinNumber? = null,

    @SerializedName("scheme")
    var scheme: String? = null,

    @SerializedName("type")
    var type: String? = null,

    @SerializedName("brand")
    var brand: String? = null,

    @SerializedName("prepaid")
    var prepaid: String? = null,

    @SerializedName("country")
    var country: BinCountry? = null,

    @SerializedName("bank")
    var bank: BinBank? = null
)