package br.com.gascheck.domain.model

data class GasData(
    val name: String,
    val value: Double,
    val date: String,
    val typeGas: String,
    val id: Int? = null,
    var like: Boolean = false,
    var dislike: Boolean = false
)