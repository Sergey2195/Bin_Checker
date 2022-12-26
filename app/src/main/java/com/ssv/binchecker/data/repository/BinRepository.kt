package com.ssv.binchecker.data.repository

import com.ssv.binchecker.data.localDataSource.dao.BinDao
import com.ssv.binchecker.data.localDataSource.entity.BinDb
import com.ssv.binchecker.data.mappers.Mapper
import com.ssv.binchecker.data.remoteDataSource.RemoteDataSourceInterface
import com.ssv.binchecker.domain.BinInitial
import com.ssv.binchecker.domain.BinLoading
import com.ssv.binchecker.domain.BinState
import com.ssv.binchecker.domain.BinSuccess
import com.ssv.binchecker.domain.entity.BinSource
import com.ssv.binchecker.domain.interfaces.BinRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class BinRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSourceInterface,
    private val binDao: BinDao,
    private val mapper: Mapper,
) : BinRepositoryInterface {

    private val infoMutableStateFlow: MutableStateFlow<BinState> = MutableStateFlow(BinInitial)

    //when a BIN arrives, it is checked if there is one in the database, if not, then a request is sent, if it is, then information from the database is sent
    override suspend fun loadBinInfo(bin: BinSource) {
        val checkFromDb = checkBin(bin)
        if (checkFromDb != null) {
            infoMutableStateFlow.value = BinSuccess(mapper.dbEntityToBinInfo(checkFromDb))
        } else {
            infoMutableStateFlow.value = BinLoading
            val result = remoteDataSource.loadBinInfo(bin.bin)
            writeToDb(result, bin.bin)
            infoMutableStateFlow.value = result
        }
    }

    private suspend fun checkBin(bin: BinSource): BinDb? {
        return binDao.getBinFromId(bin.bin)
    }

    private suspend fun writeToDb(binState: BinState, binNumber: String) {
        if (binState !is BinSuccess) {
            return
        }
        binDao.addNewBin(mapper.binInfoToBinDb(binState.binInfo, binNumber))
    }

    override suspend fun getHistoryOfRequests(): List<String> {
        val dbResult = binDao.getAllFromDb()
        return mapper.listFromDbToListBinInfo(dbResult)
    }

    override fun getInfoFlow(): Flow<BinState> {
        return infoMutableStateFlow.asStateFlow()
    }

    override fun clearInfoFlow() {
        infoMutableStateFlow.value = BinInitial
    }
}