package br.com.gascheck.domain.repository

import br.com.gascheck.domain.model.GasData
import br.com.gascheck.domain.utils.Result


interface IGasDataRepository {

    suspend fun delete(gasData: GasData)

    suspend fun insert(gasData: GasData)

    suspend fun update(gasData: GasData)

    suspend fun getGasDataByDyMonth(day: Int): Result<List<GasData>>
}