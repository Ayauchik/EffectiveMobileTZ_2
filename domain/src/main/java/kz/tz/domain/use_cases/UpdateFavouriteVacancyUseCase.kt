package kz.tz.domain.use_cases

import kz.tz.domain.models.VacancyModel

interface UpdateFavouriteVacancyUseCase {
    suspend fun invoke(vacancyModel: VacancyModel)
}