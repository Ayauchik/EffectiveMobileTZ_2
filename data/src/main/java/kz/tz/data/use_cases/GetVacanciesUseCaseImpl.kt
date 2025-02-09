package kz.tz.data.use_cases

import android.util.Log
import kz.tz.domain.models.VacancyModel
import kz.tz.domain.repository.VacancyRepository
import kz.tz.domain.use_cases.GetVacanciesUseCase

class GetVacanciesUseCaseImpl(
    private val vacancyRepository: VacancyRepository
):GetVacanciesUseCase {
    override suspend fun invoke(): List<VacancyModel> {
        Log.e("use case", "getting vacancy model")
        val vacancies = vacancyRepository.getVacancies()
        Log.e("use case", vacancies[0].title)
        return vacancies
    }
}