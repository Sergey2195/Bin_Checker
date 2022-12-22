package com.ssv.binchecker.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BinInfo(
    var numberLen: Int? = null,
    var numberLuhn: Boolean? = null,
    var scheme: String? = null,
    var type: String? = null,
    var brand: String? = null,
    var prepaid: String? = null,
    var county: Country? = null,
    var bank: Bank? = null
): Parcelable