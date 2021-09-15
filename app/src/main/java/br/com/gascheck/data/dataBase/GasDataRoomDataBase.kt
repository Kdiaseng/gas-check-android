package br.com.gascheck.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.gascheck.data.dataSource.GasDataDao
import br.com.gascheck.data.model.GasDataEntity

@Database(entities = [GasDataEntity::class], version = 2, exportSchema = false)
abstract class GasDataRoomDataBase : RoomDatabase() {
    abstract fun gasDataDao(): GasDataDao
}