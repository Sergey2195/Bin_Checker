package com.ssv.binchecker.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    var numeric: String? = null,
    var alpha2: String? = null,
    var name: String? = null,
    var emoji: String? = null,
    var currency: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null
): Parcelable