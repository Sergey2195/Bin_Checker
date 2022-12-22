package com.ssv.binchecker.domain.useCases

import com.ssv.binchecker.domain.BinState
import com.ssv.binchecker.domain.interfaces.BinRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInfoFlowUseCase @Inject constructor(
    private val binRepositoryInterface: BinRepositoryInterface
){
    operator fun invoke(): Flow<BinState>{
        return binRepositoryInterface.getInfoFlow()
    }
}