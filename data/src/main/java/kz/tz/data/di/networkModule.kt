package kz.tz.data.di

import kz.tz.data.network.mockAPI.MockPlaceholderService
import kz.tz.data.network.mockAPI.PlaceholderServiceImpl
import org.koin.dsl.module


val networkModule = module {
    single<MockPlaceholderService> { PlaceholderServiceImpl(get()) }
}