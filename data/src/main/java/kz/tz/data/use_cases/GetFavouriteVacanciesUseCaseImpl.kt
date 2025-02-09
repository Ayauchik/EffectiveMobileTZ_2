package kz.tz.data.use_cases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import kz.tz.data.repository.VacancyRepositoryImpl
import kz.tz.domain.models.VacancyModel
import kz.tz.domain.repository.VacancyRepository
import kz.tz.domain.use_cases.GetFavouriteVacanciesUseCase

class GetFavouriteVacanciesUseCaseImpl(
    private val vacancyRepository: VacancyRepository
): GetFavouriteVacanciesUseCase {
    override fun invoke(): Flow<List<VacancyModel>> {
        return vacancyRepository.getFavouriteVacancies()
    }
}