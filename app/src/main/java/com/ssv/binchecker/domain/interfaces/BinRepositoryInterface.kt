package com.ssv.binchecker.domain.interfaces

import com.ssv.binchecker.domain.BinState
import com.ssv.binchecker.domain.entity.BinInfo
import com.ssv.binchecker.domain.entity.BinSource
import kotlinx.coroutines.flow.Flow

interface BinRepositoryInterface {
    suspend fun loadBinInfo(bin: BinSource)
    suspend fun getHistoryOfRequests(): List<String>
    fun getInfoFlow(): Flow<BinState>
    fun clearInfoFlow()
}