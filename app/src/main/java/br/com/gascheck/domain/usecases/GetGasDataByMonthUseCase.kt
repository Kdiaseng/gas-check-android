package br.com.gascheck.domain.usecases

import br.com.gascheck.domain.model.GasData
import br.com.gascheck.domain.repository.IGasDataRepository
import br.com.gascheck.domain.utils.Result
import br.com.gascheck.ui.model.GasDataUi


interface IGetGasDataByMonthUseCase {
    suspend operator fun invoke(): List<GasDataUi>
}

class GetGasDataByMonthUseCase(private val repository: IGasDataRepository) :
    IGetGasDataByMonthUseCase {
    override suspend fun invoke(): List<GasDataUi> {
        return when (val response = repository.getGasDataByDyMonth(9)) {
            is Result.Success -> buildListGasDataUi(response.data)
            is Result.Error -> listOf()
        }
    }

    private fun buildListGasDataUi(data: List<GasData>): List<GasDataUi> {
        return listOf()
    }
}