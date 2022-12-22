package com.ssv.binchecker.data.remoteDataSource

import com.ssv.binchecker.domain.BinState
import com.ssv.binchecker.domain.entity.BinInfo

interface RemoteDataSourceInterface {
    suspend fun loadBinInfo(bin: String): BinState
}