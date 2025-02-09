package kz.tz.effectivemobile.di

import kz.tz.effectivemobile.MainViewModel
import kz.tz.effectivemobile.ui.favourite.FavouriteScreenViewModel
import kz.tz.effectivemobile.ui.search.SearchScreenViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { SearchScreenViewModel(get(), get(), get()) }
    factory { FavouriteScreenViewModel(get(), get())}
    factory { MainViewModel(get()) }
}