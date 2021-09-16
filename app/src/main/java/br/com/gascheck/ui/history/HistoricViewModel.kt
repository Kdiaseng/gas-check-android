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
        const val JAN_NUMBER = "01"
        const val DEZ_NUMBER = "12"
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

    private val _valueTotalLiveData = MutableLiveData<String>()
    val valueTotalLiveData: LiveData<String> = _valueTotalLiveData

    private var monthString = getMonthCurrentString()
    private var yearString = getYearCurrentString()


    fun getDateGasList() {
        viewModelScope.launch {
            _monthLiveData.value = monthsMap.getValue(monthString) + " " + yearString
            _gasDataList.value = useCase(monthString, yearString).toListGasDataUi()

            _gasDataList.value?.let { list ->
                if (list.isNotEmpty()) {
                    val listValues = list.map { it.value.toDouble() }
                    val sum = listValues.reduce { acc, d -> acc + d }
                    _valueTotalLiveData.value = sum.toString()
                } else {
                    _valueTotalLiveData.value = "00,00"
                }

            }
        }
    }

    private fun getYearCurrentString() =
        Calendar.getInstance().get(Calendar.YEAR).toString()


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
        } else {
            monthString = JAN_NUMBER
            Log.e(TAG, monthString)
            val nextYear = yearString.toInt() + 1
            yearString = nextYear.toString()
            _monthLiveData.value = monthsMap.getValue(monthString) + " " + yearString
        }

    }

    fun previousMonth() {
        val keys = monthsMap.keys
        val indexCurrentMonth = keys.indexOf(monthString)
        val indexPreviousMonth = indexCurrentMonth - 1
        Log.e(TAG, yearString)
        if (indexPreviousMonth >= 0) {
            val previousMonth = keys.elementAt(indexPreviousMonth)
            Log.e(TAG, previousMonth)
            monthString = previousMonth
            getDateGasList()
        } else {
            monthString = DEZ_NUMBER
            yearString = (yearString.toInt() - 1).toString()
            Log.e(TAG, yearString)
            _monthLiveData.value = monthsMap.getValue(monthString) + " " + yearString

        }

    }

}