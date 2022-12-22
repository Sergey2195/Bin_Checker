package com.ssv.binchecker.data.remoteDataSource.apiCall

import com.ssv.binchecker.data.remoteDataSource.entity.BinResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApiCall {

    @GET("{$BIN}")
    suspend fun loadInfo(
        @Path(BIN) bin: String
    ): BinResponse

    companion object {
        private const val BIN = "bin"
    }
}