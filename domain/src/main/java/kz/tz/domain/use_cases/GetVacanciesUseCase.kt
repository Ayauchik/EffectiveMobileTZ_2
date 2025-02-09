package kz.tz.domain.use_cases

import kz.tz.domain.models.VacancyModel

interface GetVacanciesUseCase {
    suspend fun invoke(): List<VacancyModel>
}