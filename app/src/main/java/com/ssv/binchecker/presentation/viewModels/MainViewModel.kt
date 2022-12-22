package com.ssv.binchecker.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssv.binchecker.domain.BinState
import com.ssv.binchecker.domain.useCases.ClearInfoFlowUseCase
import com.ssv.binchecker.domain.useCases.GetInfoFlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val clearInfoFlowUseCase: ClearInfoFlowUseCase,
    private val getInfoFlowUseCase: GetInfoFlowUseCase
) : ViewModel() {

    fun clearFlow() {
        clearInfoFlowUseCase.invoke()
    }

    fun getInfoFlow(): Flow<BinState>{
        return getInfoFlowUseCase.invoke()
    }
}