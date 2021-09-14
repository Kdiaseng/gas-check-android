package br.com.gascheck.ui.dialog.inputDataGas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gascheck.domain.model.GasData
import br.com.gascheck.domain.usecases.IInsertGasDataUserCase
import kotlinx.coroutines.launch
import java.lang.Exception

class InputDataGasViewModel(private val userCase: IInsertGasDataUserCase) : ViewModel() {

    private val _insertSuccess = MutableLiveData(false)
    val insertSuccess: LiveData<Boolean> = _insertSuccess

    fun insertGasData(gasData: GasData) {
        viewModelScope.launch {
            try {
                userCase(gasData)
                _insertSuccess.value = true
            } catch (e: Exception) {
                _insertSuccess.value = false
            }
        }
    }

}