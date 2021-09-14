package br.com.gascheck.domain.model

data class GasData(
    val id: Int,
    val name: String,
    val value: Double,
    val date: String,
    val like: Boolean = false,
    val dislike: Boolean = false
)