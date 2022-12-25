package com.ssv.binchecker.data.mappers

import com.ssv.binchecker.data.localDataSource.entity.BinDb
import com.ssv.binchecker.data.remoteDataSource.entity.BinBank
import com.ssv.binchecker.data.remoteDataSource.entity.BinCountry
import com.ssv.binchecker.data.remoteDataSource.entity.BinResponse
import com.ssv.binchecker.domain.entity.Bank
import com.ssv.binchecker.domain.entity.BinInfo
import com.ssv.binchecker.domain.entity.Country

class Mapper {
    fun binResponseToBinInfo(binResponse: BinResponse): BinInfo{
        return BinInfo(
            numberLen = binResponse.binNumber?.len,
            numberLuhn = binResponse.binNumber?.luhn,
            scheme = binResponse.scheme,
            type = binResponse.type,
            brand = binResponse.brand,
            prepaid = binResponse.prepaid,
            county = binCountryToCountry(binResponse.country),
            bank = binBankToBank(binResponse.bank)
        )
    }

    private fun binCountryToCountry(binCountry: BinCountry?):Country{
        return Country(
            numeric = binCountry?.numeric,
            alpha2 = binCountry?.alpha2,
            name = binCountry?.name,
            emoji = binCountry?.emoji,
            currency = binCountry?.currency,
            latitude = binCountry?.latitude,
            longitude = binCountry?.longitude
        )
    }

    private fun binBankToBank(binBank: BinBank?):Bank{
        return Bank(
            name = binBank?.name,
            url = binBank?.url,
            phone = binBank?.phone,
            city = binBank?.city
        )
    }

    fun listFromDbToListBinInfo(listRes: List<BinDb>): List<String>{
        val res = arrayListOf<String>()
        for (bin in listRes){
            res.add(bin.id)
        }
        return res
    }

    fun dbEntityToBinInfo(db: BinDb): BinInfo{
        val country = Country(
            numeric = db.numericCountry,
            alpha2 = db.alpha2Country,
            name = db.nameCountry,
            emoji = db.emojiCountry,
            currency = db.currencyCountry,
            latitude = db.latitudeCountry,
            longitude = db.longitudeCountry
        )
        val bank = Bank(
            name = db.nameBank,
            url = db.urlBank,
            phone = db.phoneBank,
            city = db.cityBank
        )
        return BinInfo(
            numberLen = db.numberLen,
            numberLuhn = db.numberLuhn,
            scheme = db.scheme,
            type = db.type,
            brand = db.brand,
            prepaid = db.prepaid,
            county = country,
            bank = bank
        )
    }
    fun binInfoToBinDb(binInfo: BinInfo, binNumber: String): BinDb{
        return BinDb(
            id = binNumber,
            numberLen = binInfo.numberLen,
            numberLuhn = binInfo.numberLuhn,
            scheme = binInfo.scheme,
            type = binInfo.type,
            brand = binInfo.brand,
            prepaid = binInfo.prepaid,
            numericCountry = binInfo.county?.numeric,
            alpha2Country = binInfo.county?.alpha2,
            nameCountry = binInfo.county?.name,
            emojiCountry = binInfo.county?.emoji,
            currencyCountry = binInfo.county?.currency,
            latitudeCountry = binInfo.county?.latitude,
            longitudeCountry = binInfo.county?.longitude,
            nameBank = binInfo.bank?.name,
            urlBank = binInfo.bank?.url,
            phoneBank = binInfo.bank?.phone,
            cityBank = binInfo.bank?.city
        )
    }
}