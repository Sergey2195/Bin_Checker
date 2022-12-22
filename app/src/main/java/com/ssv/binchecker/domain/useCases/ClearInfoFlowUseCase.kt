package com.ssv.binchecker.domain.useCases

import com.ssv.binchecker.domain.interfaces.BinRepositoryInterface
import javax.inject.Inject

class ClearInfoFlowUseCase @Inject constructor(
    private val binRepositoryInterface: BinRepositoryInterface
) {
    operator fun invoke(){
        binRepositoryInterface.clearInfoFlow()
    }
}