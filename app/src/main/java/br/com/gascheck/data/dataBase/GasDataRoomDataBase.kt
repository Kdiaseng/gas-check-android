package br.com.gascheck.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.gascheck.data.dataSource.GasDataDao
import br.com.gascheck.data.model.GasDataEntity
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [GasDataEntity::class], version = 1, exportSchema = false)
abstract class GasDataRoomDataBase : RoomDatabase() {
    abstract fun gasDataDao(): GasDataDao

    companion object {
        @Volatile
        private var INSTANCE: GasDataRoomDataBase? = null

        @InternalCoroutinesApi
        fun getDatabase(context: Context): GasDataRoomDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GasDataRoomDataBase::class.java,"gas_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}