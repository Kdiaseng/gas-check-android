package br.com.gascheck.data.mapper

import br.com.gascheck.data.model.GasDataEntity
import br.com.gascheck.domain.model.GasData

fun GasData.toEntity() =
    GasDataEntity(this.name, this.value, this.date, this.like, this.dislike)

fun List<GasData>.toEntity() = this.map { it.toEntity() }

fun GasDataEntity.toGasData() = GasData(name, value, date, uid, like, dislike)

fun List<GasDataEntity>.toListGasData() = this.map { it.toGasData() }