package kz.tz.data.di

import androidx.room.Room
import kz.tz.data.room.VacancyDatabase
import kz.tz.data.room.dao.FavouriteVacancyDoa
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val roomDatabaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), VacancyDatabase::class.java, name = ROOM_DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    factory<FavouriteVacancyDoa> {
        val appRoomDatabase: VacancyDatabase = get()
        appRoomDatabase.favouriteVacancyDoa()
    }
}

private const val ROOM_DB_NAME = "AppRoomDatabase"