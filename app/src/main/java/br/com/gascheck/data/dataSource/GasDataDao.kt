package br.com.gascheck.data.dataSource

import androidx.room.*
import br.com.gascheck.data.model.GasDataEntity

@Dao
interface GasDataDao {

    @Query("SELECT * FROM GasDataEntity")
    fun getAll(): List<GasDataEntity>

    fun getGasDataByMonth(): List<GasDataEntity>

    @Insert
    fun insert(gasData: GasDataEntity)

    @Delete
    fun delete(gasData: GasDataEntity)

    @Update
    fun update(gasData: GasDataEntity)

}