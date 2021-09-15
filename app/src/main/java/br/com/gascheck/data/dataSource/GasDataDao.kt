package br.com.gascheck.data.dataSource

import androidx.room.*
import br.com.gascheck.data.model.GasDataEntity
import java.time.YearMonth

@Dao
interface GasDataDao {

    @Query("SELECT * FROM GasDataEntity")
    fun getAll(): List<GasDataEntity>

    @Query("SELECT * FROM GasDataEntity" +
            " WHERE  strftime('%m',date) =:month AND strftime('%Y',date) =:year")
    fun getGasDataByMonth(month: String, year: String): List<GasDataEntity>

    @Insert
    fun insert(gasData: GasDataEntity)

    @Delete
    fun delete(gasData: GasDataEntity)

    @Update
    fun update(gasData: GasDataEntity)

}