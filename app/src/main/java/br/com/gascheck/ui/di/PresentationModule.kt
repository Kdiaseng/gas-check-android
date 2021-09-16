package br.com.gascheck.ui.di

import br.com.gascheck.ui.dialog.inputDataGas.InputDataGasViewModel
import br.com.gascheck.ui.history.HistoricViewModel
import br.com.gascheck.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { InputDataGasViewModel(get()) }
    viewModel { HistoricViewModel(get(), get()) }
    viewModel { HomeViewModel(get()) }
}