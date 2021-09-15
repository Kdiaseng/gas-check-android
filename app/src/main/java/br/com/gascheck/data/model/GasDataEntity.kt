package br.com.gascheck.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GasDataEntity(
    val name: String,
    val value: Double,
    val date: String,
    @ColumnInfo(name = "type_gas")
    val typeGas: String,
    val like: Boolean = false,
    val dislike: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}
