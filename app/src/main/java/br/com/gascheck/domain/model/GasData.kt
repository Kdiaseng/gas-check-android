package br.com.gascheck.domain.model

data class GasData(
    val name: String,
    val value: Double,
    val date: String,
    val id: Int? = null,
    val like: Boolean = false,
    val dislike: Boolean = false
)