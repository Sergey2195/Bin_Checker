package com.ssv.binchecker.domain.interfaces

import com.ssv.binchecker.domain.BinState
import com.ssv.binchecker.domain.entity.BinSource
import kotlinx.coroutines.flow.Flow

interface BinRepositoryInterface {
    suspend fun loadBinInfo(bin: BinSource)
    fun getInfoFlow(): Flow<BinState>
    fun clearInfoFlow()
}