package com.ssv.binchecker.domain.useCases

import com.ssv.binchecker.domain.interfaces.BinRepositoryInterface
import javax.inject.Inject

class GetHistoryOfRequestsUseCase @Inject constructor(
    private val binRepositoryInterface: BinRepositoryInterface
) {
    suspend operator fun invoke(): List<String>{
        return binRepositoryInterface.getHistoryOfRequests()
    }
}