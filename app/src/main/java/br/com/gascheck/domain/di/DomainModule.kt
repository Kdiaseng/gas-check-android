package br.com.gascheck.domain.di

import br.com.gascheck.domain.usecases.IInsertGasDataUserCase
import br.com.gascheck.domain.usecases.InsertGasDataUserCase
import org.koin.dsl.module

val domainModule = module {
    factory<IInsertGasDataUserCase> { InsertGasDataUserCase(get()) }
}