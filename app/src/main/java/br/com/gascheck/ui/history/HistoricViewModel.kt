package br.com.gascheck.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gascheck.domain.usecases.IGetGasDataByMonthUseCase
import br.com.gascheck.ui.mapper.toListGasDataUi
import br.com.gascheck.ui.model.GasDataUi
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HistoricViewModel(private val useCase: IGetGasDataByMonthUseCase) : ViewModel() {

    private val _gasDataList = MutableLiveData<List<GasDataUi>>()
    val gasDataList: LiveData<List<GasDataUi>> = _gasDataList

    fun getDateGasList() {
        viewModelScope.launch {
            val month = getMonthCurrentString()
            _gasDataList.value = useCase(month).toListGasDataUi()
        }
    }

    private fun getMonthCurrentString(): String {
        val simpleDateFormat = SimpleDateFormat("MM", Locale.getDefault())
        return simpleDateFormat.format(Date())
    }

}