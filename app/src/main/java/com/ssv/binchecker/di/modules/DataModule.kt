package com.ssv.binchecker.di.modules

import com.ssv.binchecker.data.remoteDataSource.RemoteDataSource
import com.ssv.binchecker.data.remoteDataSource.RemoteDataSourceInterface
import com.ssv.binchecker.data.mappers.Mapper
import com.ssv.binchecker.data.repository.BinRepository
import com.ssv.binchecker.di.ApplicationScope
import com.ssv.binchecker.domain.interfaces.BinRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @ApplicationScope
    @Binds
    fun bindBinRepository(impl: BinRepository): BinRepositoryInterface

    @ApplicationScope
    @Binds
    fun bindRemoteDataSource(impl: RemoteDataSource): RemoteDataSourceInterface

    companion object{
        @ApplicationScope
        @Provides
        fun provideMapper(): Mapper {
            return Mapper()
        }
    }
}