package br.com.gascheck.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GasDataEntity(
    @PrimaryKey
    val uid: Int,
    val name: String,
    val value: Double,
    val date: String,
    val like: Boolean = false,
    val dislike: Boolean = false
)
