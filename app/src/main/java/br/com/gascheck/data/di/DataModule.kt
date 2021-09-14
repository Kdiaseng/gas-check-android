package br.com.gascheck.data.di

import android.app.Application
import androidx.room.Room
import br.com.gascheck.data.dataBase.GasDataRoomDataBase
import br.com.gascheck.data.dataSource.GasDataDao
import br.com.gascheck.data.repository.GasDataRepository
import br.com.gascheck.domain.repository.IGasDataRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

    fun provideDataBase(application: Application): GasDataRoomDataBase {
        return Room.databaseBuilder(application, GasDataRoomDataBase::class.java, "gas_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun providerGasDataDao(dataBase: GasDataRoomDataBase): GasDataDao {
        return dataBase.gasDataDao()
    }

    single { provideDataBase(androidApplication()) }
    single { providerGasDataDao(get()) }

    factory<IGasDataRepository> { GasDataRepository(get()) }


}