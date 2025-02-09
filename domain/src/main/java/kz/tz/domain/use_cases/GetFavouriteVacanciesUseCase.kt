package kz.tz.domain.use_cases

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import kz.tz.domain.models.VacancyModel

interface GetFavouriteVacanciesUseCase {
    fun invoke(): Flow<List<VacancyModel>>
}