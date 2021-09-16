package br.com.gascheck.domain.usecases

import br.com.gascheck.domain.model.GasData
import br.com.gascheck.domain.repository.IGasDataRepository

interface IUpdateGasDataUseCase {
    suspend operator fun invoke(gasData: GasData)
}

class UpdateGasDataUseCase(private val repository: IGasDataRepository) : IUpdateGasDataUseCase {
    override suspend fun invoke(gasData: GasData) {
       repository.update(gasData)
    }
}