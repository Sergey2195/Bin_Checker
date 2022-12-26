package com.ssv.binchecker.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.ssv.binchecker.R
import com.ssv.binchecker.domain.BinFailure
import com.ssv.binchecker.domain.BinState
import com.ssv.binchecker.domain.entity.BinHistory
import com.ssv.binchecker.domain.entity.BinSource
import com.ssv.binchecker.domain.useCases.ClearInfoFlowUseCase
import com.ssv.binchecker.domain.useCases.GetHistoryOfRequestsUseCase
import com.ssv.binchecker.domain.useCases.GetInfoFlowUseCase
import com.ssv.binchecker.domain.useCases.LoadBinInfoUseCase
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import javax.inject.Inject

/***
 * view model of the starter framgent interacting with the repository
 */

class InitialViewModel @Inject constructor(
    private val loadBinInfoUseCase: LoadBinInfoUseCase,
    private val getInfoFlowUseCase: GetInfoFlowUseCase,
    private val clearInfoFlowUseCase: ClearInfoFlowUseCase,
    private val getHistoryOfRequestsUseCase: GetHistoryOfRequestsUseCase,
) : ViewModel() {

    fun clearFlow() {
        clearInfoFlowUseCase.invoke()
    }

    suspend fun loadHistory(): List<BinHistory> {
        val strings = getHistoryOfRequestsUseCase.invoke()
        val resultList = arrayListOf<BinHistory>()
        for (s in strings) {
            resultList.add(BinHistory(s))
        }
        return resultList
    }

    fun showError(binFailure: BinFailure): Int {
        return when (binFailure.e) {
            is HttpException -> R.string.notFounded
            else -> R.string.connectionError
        }
    }

    fun getInfoFlow(): Flow<BinState> {
        return getInfoFlowUseCase.invoke()
    }

    suspend fun sendBin(bin: String) {
        loadBinInfoUseCase.invoke(BinSource(bin))
    }
}