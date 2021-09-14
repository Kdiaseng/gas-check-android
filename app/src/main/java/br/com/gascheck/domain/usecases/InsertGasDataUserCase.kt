package br.com.gascheck.domain.usecases

import br.com.gascheck.domain.model.GasData
import br.com.gascheck.domain.repository.IGasDataRepository

interface IInsertGasDataUserCase {
    suspend operator fun invoke(gasData: GasData)
}

class InsertGasDataUserCase(private val repository: IGasDataRepository) : IInsertGasDataUserCase {
    override suspend fun invoke(gasData: GasData) {
        repository.insert(gasData)
    }
}