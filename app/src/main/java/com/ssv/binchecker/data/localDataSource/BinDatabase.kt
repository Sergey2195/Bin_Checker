package com.ssv.binchecker.data.localDataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ssv.binchecker.data.localDataSource.dao.BinDao
import com.ssv.binchecker.data.localDataSource.entity.BinDb

@Database(entities = [BinDb::class], version = 1, exportSchema = false)
abstract class BinDatabase : RoomDatabase() {
    abstract fun getBinDao(): BinDao
}