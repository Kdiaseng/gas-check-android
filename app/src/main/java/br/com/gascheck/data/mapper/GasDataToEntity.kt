package br.com.gascheck.data.mapper

import br.com.gascheck.data.model.GasDataEntity
import br.com.gascheck.domain.model.GasData
import org.koin.core.component.getScopeId

fun GasData.toEntity(): GasDataEntity {
    val entity =
        GasDataEntity(this.name, this.value, this.date, this.typeGas, this.like, this.dislike)

    this.id?.let {
        entity.uid = it
    }

    return entity
}


fun List<GasData>.toEntity() = this.map { it.toEntity() }

fun GasDataEntity.toGasData() = GasData(name, value, date, typeGas, uid, like, dislike)

fun List<GasDataEntity>.toListGasData() = this.map { it.toGasData() }