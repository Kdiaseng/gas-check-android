package br.com.gascheck.ui.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gascheck.domain.usecases.IGetGasDataByMonthUseCase
import br.com.gascheck.ui.mapper.toListGasDataUi
import br.com.gascheck.ui.model.GasDataUi
import kotlinx.coroutines.launch

class HistoricViewModel(private val useCase: IGetGasDataByMonthUseCase) : ViewModel() {

    private val _gasDataList = MutableLiveData<List<GasDataUi>>()
    val gasDataList: LiveData<List<GasDataUi>> = _gasDataList

    fun getDateGasList() {
        viewModelScope.launch {
            _gasDataList.value = useCase().toListGasDataUi()
           Log.e("FDSFF",_gasDataList.value.toString())
        }
    }

}