package com.ssv.binchecker.data.localDataSource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Bin_db")
data class BinDb(
    @PrimaryKey
    var id: String,
    var numberLen: Int? = null,
    var numberLuhn: Boolean? = null,
    var scheme: String? = null,
    var type: String? = null,
    var brand: String? = null,
    var prepaid: String? = null,
    var numericCountry: String? = null,
    var alpha2Country: String? = null,
    var nameCountry: String? = null,
    var emojiCountry: String? = null,
    var currencyCountry: String? = null,
    var latitudeCountry: Double? = null,
    var longitudeCountry: Double? = null,
    var nameBank: String? = null,
    var urlBank: String? = null,
    var phoneBank: String? = null,
    var cityBank : String? = null
)