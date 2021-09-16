package br.com.gascheck.ui.model

data class GasDataUi(
    val id: Int,
    val name: String = "Desconhecido",
    val typeGas: String,
    val day: String,
    val month: String,
    val time: String,
    var like: Boolean? = null,
    var dislike: Boolean? = null,
    val value: String,
    val dateTime: String,
)