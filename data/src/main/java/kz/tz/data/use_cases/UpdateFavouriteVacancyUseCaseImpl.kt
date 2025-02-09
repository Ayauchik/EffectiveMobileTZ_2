package kz.tz.data.use_cases

import kz.tz.domain.models.VacancyModel
import kz.tz.domain.repository.VacancyRepository
import kz.tz.domain.use_cases.UpdateFavouriteVacancyUseCase

class UpdateFavouriteVacancyUseCaseImpl(
    private val vacancyRepository: VacancyRepository
): UpdateFavouriteVacancyUseCase {
    override suspend fun invoke(vacancyModel: VacancyModel) {
        vacancyRepository.toggleFavoriteVacancy(vacancyModel)
    }
}