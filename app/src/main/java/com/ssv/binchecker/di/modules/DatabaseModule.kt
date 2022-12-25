package com.ssv.binchecker.di.modules

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ssv.binchecker.data.localDataSource.BinDatabase
import com.ssv.binchecker.data.localDataSource.dao.BinDao
import com.ssv.binchecker.data.localDataSource.entity.BinDb
import com.ssv.binchecker.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
interface DatabaseModule {
    companion object{
        @ApplicationScope
        @Provides
        fun provideBinDatabase(
            application: Application
        ): BinDatabase{
            return Room.databaseBuilder(
                application.applicationContext,
                BinDatabase::class.java,
                "Bin_db"
            ).build()
        }

        @ApplicationScope
        @Provides
        fun provideDao(room: BinDatabase): BinDao{
            return room.getBinDao()
        }
    }
}