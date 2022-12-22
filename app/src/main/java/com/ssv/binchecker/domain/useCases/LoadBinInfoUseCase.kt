package com.ssv.binchecker.domain.useCases

import com.ssv.binchecker.domain.entity.BinSource
import com.ssv.binchecker.domain.interfaces.BinRepositoryInterface
import javax.inject.Inject

class LoadBinInfoUseCase @Inject constructor(
    private val binRepositoryInterface: BinRepositoryInterface
) {
    suspend operator fun invoke(bin: BinSource) {
        binRepositoryInterface.loadBinInfo(bin)
    }
}