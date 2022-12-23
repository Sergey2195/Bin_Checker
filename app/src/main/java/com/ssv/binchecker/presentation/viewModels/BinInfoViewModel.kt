package com.ssv.binchecker.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ssv.binchecker.domain.entity.BinInfo
import com.ssv.binchecker.domain.entity.DescriptionContent
import javax.inject.Inject

class BinInfoViewModel @Inject constructor() : ViewModel() {
    fun prepareListItems(binInfo: BinInfo): List<DescriptionContent>{
        var result = arrayListOf<DescriptionContent>()
        Log.i("TAG View model", binInfo.toString())
        binInfo.numberLen?.let { result.add(
            DescriptionContent("Card number length:", it.toString(), false)
        ) }
        binInfo.numberLuhn?.let { result.add(
            DescriptionContent("Luhn algorithm:", it.toString(), false)
        ) }
        binInfo.scheme?.let { result.add (DescriptionContent("Scheme:", it, false)) }
        binInfo.type?.let { result.add(DescriptionContent("Type:", it,false)) }
        binInfo.brand?.let { result.add(DescriptionContent("Brand:", it,false)) }
        binInfo.prepaid?.let { result.add(DescriptionContent("Prepaid:", it, false)) }
        binInfo.county?.numeric?.let { result.add(DescriptionContent("Numeric of country:", it, false)) }
        binInfo.county?.alpha2?.let { result.add(DescriptionContent("Alpha:", it, false)) }
        binInfo.county?.name?.let { result.add(DescriptionContent("Name of country:", it, true)) }
        binInfo.county?.emoji?.let { result.add(DescriptionContent("Emoji:", it, false)) }
        binInfo.county?.currency?.let { result.add(DescriptionContent("Currency:", it,false))}
        binInfo.county?.latitude?.let { result.add(DescriptionContent("Latitude:", it.toString(), true)) }
        binInfo.county?.longitude?.let { result.add(DescriptionContent("Longitude", it.toString(), true)) }
        binInfo.bank?.name?.let { result.add(DescriptionContent("Bank name:", it,false)) }
        binInfo.bank?.url?.let { result.add(DescriptionContent("Website:", it, true)) }
        binInfo.bank?.phone?.let { result.add(DescriptionContent("Phone:", it, true)) }
        binInfo.bank?.city?.let { result.add(DescriptionContent("City:", it, false)) }
        return result
    }
}