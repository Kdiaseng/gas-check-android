package br.com.gascheck.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gascheck.domain.model.GasData
import br.com.gascheck.domain.usecases.IGetGasDataByMonthUseCase
import br.com.gascheck.ui.model.GasPercentage
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(val getGasDataByMonthUseCase: IGetGasDataByMonthUseCase) : ViewModel() {

    private var monthString = getMonthCurrentString()
    private var yearString = getYearCurrentString()

    private val _totalLiveData = MutableLiveData<String>()
    val totalLiveData: LiveData<String> = _totalLiveData

    private val _gasPercentageList = MutableLiveData<List<GasPercentage>>()
    val gasPercentageList: LiveData<List<GasPercentage>> = _gasPercentageList


    fun updateScreen() {
        viewModelScope.launch {
            val gasList = getGasDataByMonthUseCase(monthString, yearString)
            if (gasList.isNotEmpty()) {
                updateTotal(gasList)
                _gasPercentageList.value = gasList.map { it -> GasPercentage(it.typeGas, it.value) }
            }

        }
    }

    private fun updateTotal(gasList: List<GasData>) {
        val listValues = gasList.map { it.value }
        val sum = listValues.reduce { previous, next -> previous + next }
        _totalLiveData.value = sum.toString()
    }

    private fun getMonthCurrentString(): String {
        val simpleDateFormat = SimpleDateFormat("MM", Locale.getDefault())
        return simpleDateFormat.format(Date())
    }

    private fun getYearCurrentString() =
        Calendar.getInstance().get(Calendar.YEAR).toString()


}