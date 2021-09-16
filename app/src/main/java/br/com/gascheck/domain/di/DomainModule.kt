package br.com.gascheck.domain.di

import br.com.gascheck.domain.usecases.*
import org.koin.dsl.module

val domainModule = module {
    factory<IInsertGasDataUserCase> { InsertGasDataUserCase(get()) }
    factory<IUpdateGasDataUseCase> { UpdateGasDataUseCase(get()) }
    factory<IGetGasDataByMonthUseCase> { GetGasDataByMonthUseCase(get()) }
}