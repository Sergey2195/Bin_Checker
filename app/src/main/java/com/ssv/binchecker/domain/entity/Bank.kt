package com.ssv.binchecker.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bank(
    var name: String? = null,
    var url: String? = null,
    var phone: String? = null,
    var city : String? = null
): Parcelable