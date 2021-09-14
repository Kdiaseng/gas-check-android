package br.com.gascheck

import android.app.Application
import br.com.gascheck.data.di.dataModule
import br.com.gascheck.domain.di.domainModule
import br.com.gascheck.ui.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(presentationModule, dataModule, domainModule))
        }
    }
}