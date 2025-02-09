package kz.tz.effectivemobile

import android.app.Application
import kz.tz.data.di.mapperModule
import kz.tz.data.di.networkModule
import kz.tz.data.di.repositoryModule
import kz.tz.data.di.roomDatabaseModule
import kz.tz.data.di.useCaseModule
import kz.tz.effectivemobile.di.viewModelModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication : Application() {
    private val modulesToUse = listOf(
        networkModule,
        repositoryModule,
        useCaseModule,
        viewModelModule,
        mapperModule,
        roomDatabaseModule
    )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CustomApplication)
            modules(modulesToUse)
        }
    }
}