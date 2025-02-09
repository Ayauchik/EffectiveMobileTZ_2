package kz.tz.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kz.tz.data.room.entity.VacancyEntity

@Dao
interface FavouriteVacancyDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancyEntity: VacancyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancies(vacancyEntities: List<VacancyEntity>)

    @Delete
    suspend fun deleteVacancy(vacancyEntity: VacancyEntity)

    @Query("SELECT * FROM vacancies")
    fun getFavouriteVacancies(): Flow<List<VacancyEntity>>

    @Query("UPDATE vacancies SET isFavorite = :isFav WHERE id = :vacancyId")
    suspend fun updateFavoriteStatus(vacancyId: String, isFav: Boolean)

}