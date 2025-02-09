package kz.tz.effectivemobile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kz.tz.domain.use_cases.GetFavouriteVacanciesUseCase

class MainViewModel(
    getFavouriteVacanciesUseCase: GetFavouriteVacanciesUseCase,
) : ViewModel() {
    val favoriteVacanciesCount: StateFlow<Int> = getFavouriteVacanciesUseCase.invoke()
        .map { it.count() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
}
