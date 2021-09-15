package br.com.gascheck.ui.mapper

import android.text.format.DateFormat
import br.com.gascheck.domain.model.GasData
import br.com.gascheck.ui.model.GasDataUi
import java.text.SimpleDateFormat
import java.util.*


fun List<GasData>.toListGasDataUi() = this.map { it.toGasDataUi() }

fun GasData.toGasDataUi(): GasDataUi {
    val format = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
    val data = format.parse(this.date)
    val day = DateFormat.format("dd", data) as String
    val month = DateFormat.format("MMM", data) as String
    val hour = DateFormat.format("HH:mm:ss", data) as String

    return GasDataUi(
        this.id ?: 0,
        this.name,
        this.typeGas,
        day,
        month,
        hour,
        like,
        dislike,
        this.value.toString()
    )
}