package br.com.gascheck.ui.dialog.inputDataGas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gascheck.domain.model.GasData
import br.com.gascheck.domain.usecases.IInsertGasDataUserCase
import br.com.gascheck.util.TypeGas
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class InputDataGasViewModel(private val userCase: IInsertGasDataUserCase) : ViewModel() {

    private val _insertSuccess = MutableLiveData(false)
    val insertSuccess: LiveData<Boolean> = _insertSuccess

    var typeGas: TypeGas = TypeGas.GASOLINE

    fun insertGasData(value: Int, address: String) {
        viewModelScope.launch {
            try {
                userCase(buildDataGas(value, address))
                _insertSuccess.value = true
            } catch (e: Exception) {
                _insertSuccess.value = false
            }
        }
    }

    private fun buildDataGas(value: Int, address: String): GasData {
        val currentDate: String =
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        return GasData(address, value.toDouble(), currentDate, typeGas.value)
    }

}