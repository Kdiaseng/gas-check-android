package br.com.gascheck.data.repository

import android.util.Log
import br.com.gascheck.data.dataSource.GasDataDao
import br.com.gascheck.data.mapper.toEntity
import br.com.gascheck.data.mapper.toListGasData
import br.com.gascheck.domain.model.GasData
import br.com.gascheck.domain.repository.IGasDataRepository
import br.com.gascheck.domain.utils.Result
import java.lang.Exception

class GasDataRepository(private val dao: GasDataDao) : IGasDataRepository {

    companion object {
        const val TAG = "GasDataRepository"
    }


    override suspend fun delete(gasData: GasData) {
        try {
            dao.delete(gasData.toEntity())
        } catch (e: Exception) {
            Log.e(TAG, "delete ${e.message}")
        }
    }

    override suspend fun insert(gasData: GasData) {
        try {
            dao.insert(gasData.toEntity())
        } catch (e: Exception) {
            Log.e(TAG, "insert ${e.message}")
        }
    }

    override suspend fun update(gasData: GasData) {
        try {
            dao.update(gasData.toEntity())
        } catch (e: Exception) {
            Log.e(TAG, "update ${e.message}")
        }
    }

    override suspend fun getGasDataByDyMonth(day: Int): Result<List<GasData>> {
        return try {
            Result.Success(dao.getGasDataByMonth().toListGasData())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}