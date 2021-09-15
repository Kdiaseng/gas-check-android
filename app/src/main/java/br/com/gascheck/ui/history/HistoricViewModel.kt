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
import java.text.SimpleDateFormat
import java.util.*

class HistoricViewModel(private val useCase: IGetGasDataByMonthUseCase) : ViewModel() {

    companion object {
        const val TAG = "HistoricViewModel"
        const val DEZ_INDEX = 11
    }

    private val monthsMap = mapOf(
        "01" to "JAN",
        "02" to "FER",
        "03" to "MAR",
        "04" to "ABR",
        "05" to "MAI",
        "06" to "JUN",
        "07" to "JUL",
        "08" to "AGO",
        "09" to "SET",
        "10" to "OUT",
        "11" to "NOV",
        "12" to "DEZ",
    )

    private val _gasDataList = MutableLiveData<List<GasDataUi>>()
    val gasDataList: LiveData<List<GasDataUi>> = _gasDataList

    private val _monthLiveData = MutableLiveData<String>()
    val monthLiveData: LiveData<String> = _monthLiveData

    private var monthString = getMonthCurrentString()

    fun getDateGasList() {
        viewModelScope.launch {
            _monthLiveData.value = monthsMap.getValue(monthString)
            _gasDataList.value = useCase(monthString).toListGasDataUi()
        }
    }

    private fun getMonthCurrentString(): String {
        val simpleDateFormat = SimpleDateFormat("MM", Locale.getDefault())
        return simpleDateFormat.format(Date())
    }

    fun nextMonth() {
        val keys = monthsMap.keys
        val indexCurrentMonth = keys.indexOf(monthString)
        val indexNextMonth = indexCurrentMonth + 1

        if (indexNextMonth <= DEZ_INDEX) {
            val nextMonth = keys.elementAt(indexNextMonth)
            monthString = nextMonth
            Log.e(TAG, nextMonth)
            getDateGasList()
        }

    }

    fun previousMonth() {
        val keys = monthsMap.keys
        val indexCurrentMonth = keys.indexOf(monthString)
        val indexPreviousMonth = indexCurrentMonth - 1

        if (indexPreviousMonth >= 0) {
            val previousMonth = keys.elementAt(indexPreviousMonth)
            Log.e(TAG, previousMonth)
            monthString = previousMonth
            getDateGasList()
        }

    }

}