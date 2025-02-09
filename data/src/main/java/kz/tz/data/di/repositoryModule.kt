package kz.tz.data.di

import kz.tz.data.repository.VacancyRepositoryImpl
import kz.tz.domain.repository.VacancyRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<VacancyRepository> { VacancyRepositoryImpl(get(), get(), get(), get()) }
}