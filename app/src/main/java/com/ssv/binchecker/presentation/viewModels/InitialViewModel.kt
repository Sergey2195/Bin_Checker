package com.ssv.binchecker.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.ssv.binchecker.domain.BinState
import com.ssv.binchecker.domain.entity.BinSource
import com.ssv.binchecker.domain.useCases.ClearInfoFlowUseCase
import com.ssv.binchecker.domain.useCases.GetInfoFlowUseCase
import com.ssv.binchecker.domain.useCases.LoadBinInfoUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class InitialViewModel @Inject constructor(
    private val loadBinInfoUseCase: LoadBinInfoUseCase,
    private val appScope: CoroutineScope,
    private val getInfoFlowUseCase: GetInfoFlowUseCase,
    private val clearInfoFlowUseCase: ClearInfoFlowUseCase
) : ViewModel() {

    fun clearFlow() {
        clearInfoFlowUseCase.invoke()
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