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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class InitialViewModel @Inject constructor(
    private val loadBinInfoUseCase: LoadBinInfoUseCase,
    private val appScope: CoroutineScope,
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
        for (s in strings){
            resultList.add(BinHistory(s))
        }
        return resultList
    }

    fun showError(binFailure: BinFailure): Int {
        when (binFailure.e) {
            is HttpException -> return R.string.notFounded
            else -> return R.string.connectionError
        }
    }

    fun getInfoFlow(): Flow<BinState> {
        return getInfoFlowUseCase.invoke()
    }

    fun sendBin(bin: String) {
        appScope.launch {
            loadBinInfoUseCase.invoke(BinSource(bin))
        }
    }
}