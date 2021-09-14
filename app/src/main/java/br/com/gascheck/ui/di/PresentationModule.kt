package br.com.gascheck.ui.di

import br.com.gascheck.ui.dialog.inputDataGas.InputDataGasViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { InputDataGasViewModel(get()) }
}