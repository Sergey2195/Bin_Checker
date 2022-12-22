package com.ssv.binchecker.data.repository

import com.ssv.binchecker.data.remoteDataSource.RemoteDataSourceInterface
import com.ssv.binchecker.domain.BinInitial
import com.ssv.binchecker.domain.BinLoading
import com.ssv.binchecker.domain.BinState
import com.ssv.binchecker.domain.BinSuccess
import com.ssv.binchecker.domain.entity.BinInfo
import com.ssv.binchecker.domain.entity.BinSource
import com.ssv.binchecker.domain.interfaces.BinRepositoryInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BinRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSourceInterface
) : BinRepositoryInterface {

    private val infoMutableStateFlow : MutableStateFlow<BinState> = MutableStateFlow(BinInitial)

    override suspend fun loadBinInfo(bin: BinSource) {
        infoMutableStateFlow.value = BinLoading
        val result = remoteDataSource.loadBinInfo(bin.bin)
        infoMutableStateFlow.value = result
    }

    override fun getInfoFlow(): Flow<BinState> {
        return infoMutableStateFlow.asStateFlow()
    }

    override fun clearInfoFlow() {
        infoMutableStateFlow.value = BinInitial
    }
}