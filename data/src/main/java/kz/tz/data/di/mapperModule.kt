package kz.tz.data.di

import kz.tz.data.network.mapper.AddressMapper
import kz.tz.data.network.mapper.ButtonMapper
import kz.tz.data.network.mapper.ExperienceMapper
import kz.tz.data.network.mapper.OfferMapper
import kz.tz.data.network.mapper.VacancyMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { AddressMapper() }
    factory { ExperienceMapper() }
    factory { VacancyMapper(get(), get()) }
    factory { ButtonMapper() }
    factory { OfferMapper(get()) }
}