package kz.tz.effectivemobile.ui.favourite

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kz.tz.domain.models.VacancyModel
import kz.tz.domain.use_cases.GetFavouriteVacanciesUseCase
import kz.tz.domain.use_cases.UpdateFavouriteVacancyUseCase

class FavouriteScreenViewModel(
    private val getFavouriteVacanciesUseCase: GetFavouriteVacanciesUseCase,
    private val updateFavouriteVacancyUseCase: UpdateFavouriteVacancyUseCase,
) : ViewModel() {

    private val _favVacancies = MutableStateFlow<List<VacancyModel>>(emptyList())
    val favVacancies: StateFlow<List<VacancyModel>> = _favVacancies

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> get() = _isLoading

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            getFavouriteVacanciesUseCase.invoke()
                .onStart { _isLoading.value = true } // Set loading to true before fetching
                .catch { e ->
                    Log.e("ViewModel", "Error fetching favourite vacancies: ${e.message}")
                    _isLoading.value = false
                }
                .collect { vacancies ->
                    _favVacancies.value = vacancies
                    _isLoading.value = false
                }
        }
    }

    fun toggleFavoriteVacancy(vacancyModel: VacancyModel) {
        viewModelScope.launch {

            _isLoading.value = true // Show loading state

            updateFavouriteVacancyUseCase.invoke(vacancyModel.copy(isFavorite = !vacancyModel.isFavorite))

            delay(2000) // Ensure DB update before fetching

            fetchData() // Refresh list

            _isLoading.value = false // Hide loading state


        }
    }


}