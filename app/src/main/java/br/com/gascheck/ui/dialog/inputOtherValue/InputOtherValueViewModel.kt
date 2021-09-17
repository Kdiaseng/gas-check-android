package br.com.gascheck.ui.dialog.inputOtherValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gascheck.domain.model.GasData
import br.com.gascheck.domain.usecases.IInsertGasDataUserCase
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


class InputOtherValueViewModel(private val insetUseCase: IInsertGasDataUserCase) : ViewModel() {

    fun insertGasData(value: Double, address: String, typeGas: String) {
        viewModelScope.launch {
            try {
                insetUseCase(buildDataGas(value, address, typeGas))

            } catch (e: Exception) {

            }
        }
    }

    private fun buildDataGas(value: Double, address: String, typeGas: String): GasData {
        val currentDate: String =
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        return GasData(address, value.toDouble(), currentDate, typeGas)
    }
}