package com.ssv.binchecker.data.remoteDataSource

import com.ssv.binchecker.data.mappers.Mapper
import com.ssv.binchecker.data.remoteDataSource.apiCall.BinApiCall
import com.ssv.binchecker.domain.BinFailure
import com.ssv.binchecker.domain.BinState
import com.ssv.binchecker.domain.BinSuccess
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiCall: BinApiCall,
    private val mapper: Mapper
) : RemoteDataSourceInterface {

    override suspend fun loadBinInfo(bin: String): BinState {
        return try {
            val response = apiCall.loadInfo(bin)
            val binInfo = mapper.binResponseToBinInfo(response)
            BinSuccess(binInfo)
        } catch (e: Exception) {
            BinFailure(e)
        }
    }
}