package kz.tz.domain.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import kz.tz.domain.models.OfferModel
import kz.tz.domain.models.VacancyModel

interface VacancyRepository {

//    val favouriteVacancies: MutableLiveData<List<VacancyModel>>
//        get() = MutableLiveData<List<VacancyModel>>()

    suspend fun getVacancies(): List<VacancyModel>
    suspend fun getOffers(): List<OfferModel>?
    fun getFavouriteVacancies(): Flow<List<VacancyModel>>
    suspend fun toggleFavoriteVacancy(vacancyModel: VacancyModel)
}
