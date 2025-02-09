package kz.tz.data.di

import kz.tz.data.use_cases.GetFavouriteVacanciesUseCaseImpl
import kz.tz.data.use_cases.GetOffersUseCaseImpl
import kz.tz.data.use_cases.GetVacanciesUseCaseImpl
import kz.tz.data.use_cases.UpdateFavouriteVacancyUseCaseImpl
import kz.tz.domain.use_cases.GetFavouriteVacanciesUseCase
import kz.tz.domain.use_cases.GetOffersUseCase
import kz.tz.domain.use_cases.GetVacanciesUseCase
import kz.tz.domain.use_cases.UpdateFavouriteVacancyUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetVacanciesUseCase> { GetVacanciesUseCaseImpl(get()) }
    factory<GetOffersUseCase> { GetOffersUseCaseImpl(get()) }
    factory<GetFavouriteVacanciesUseCase> { GetFavouriteVacanciesUseCaseImpl(get()) }
    factory<UpdateFavouriteVacancyUseCase> { UpdateFavouriteVacancyUseCaseImpl(get()) }
}