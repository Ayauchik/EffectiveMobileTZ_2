package kz.tz.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.tz.data.room.dao.FavouriteVacancyDoa
import kz.tz.data.room.entity.FavouriteEntity
import kz.tz.data.room.entity.VacancyEntity


@Database(
    entities =[VacancyEntity::class, FavouriteEntity::class],
    version = 2,
)
abstract class VacancyDatabase : RoomDatabase(){
    abstract fun favouriteVacancyDoa(): FavouriteVacancyDoa
    //abstract fun vacancyDao(): VacancyDao
}