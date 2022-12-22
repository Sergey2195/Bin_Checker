package com.ssv.binchecker.data.mappers

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
}