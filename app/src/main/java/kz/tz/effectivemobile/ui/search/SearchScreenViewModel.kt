package kz.tz.effectivemobile.ui.search

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kz.tz.domain.models.OfferModel
import kz.tz.domain.models.VacancyModel
import kz.tz.domain.use_cases.GetOffersUseCase
import kz.tz.domain.use_cases.GetVacanciesUseCase
import kz.tz.domain.use_cases.UpdateFavouriteVacancyUseCase

class SearchScreenViewModel(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val getOffersUseCase: GetOffersUseCase,
    private val updateFavouriteVacancyUseCase: UpdateFavouriteVacancyUseCase,
) : ViewModel() {
    private val _vacancies = MutableStateFlow<List<VacancyModel>>(emptyList())
    val vacancies: MutableStateFlow<List<VacancyModel>> = _vacancies

    private val _offers = MutableStateFlow<List<OfferModel>>(emptyList())
    val offers: MutableStateFlow<List<OfferModel>> = _offers

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> get() = _isLoading

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                Log.e("ViewModel", "fetching data")
                val vacancies = getVacanciesUseCase.invoke()
                Log.e("ViewModel", "after use case")
                val offers = getOffersUseCase.invoke()
                _vacancies.value = vacancies
                _offers.value = offers ?: emptyList()
                _isLoading.value = false
            } catch (e: Exception) {
                e.printStackTrace()
                _isLoading.value = false
            }
        }
    }


    fun toggleFavoriteVacancy(vacancyModel: VacancyModel) {
        viewModelScope.launch {
            val updatedVacancy = vacancyModel.copy(isFavorite = !vacancyModel.isFavorite) // Flip favorite status
            updateFavouriteVacancyUseCase.invoke(updatedVacancy)
        }
    }

}