package br.com.gascheck.domain.usecases

import br.com.gascheck.domain.model.GasData
import br.com.gascheck.domain.repository.IGasDataRepository
import br.com.gascheck.domain.utils.Result


interface IGetGasDataByMonthUseCase {
    suspend operator fun invoke(): List<GasData>
}

class GetGasDataByMonthUseCase(private val repository: IGasDataRepository) :
    IGetGasDataByMonthUseCase {
    override suspend fun invoke(): List<GasData> {
        return when (val response = repository.getGasDataByDyMonth(9)) {
            is Result.Success -> response.data
            is Result.Error -> listOf()
        }
    }
}