package kz.tz.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.tz.data.network.mapper.OfferMapper
import kz.tz.data.network.mapper.VacancyMapper
import kz.tz.data.network.mockAPI.MockPlaceholderService
import kz.tz.data.room.dao.FavouriteVacancyDoa
import kz.tz.data.room.entity.VacancyEntity
import kz.tz.domain.models.OfferModel
import kz.tz.domain.models.VacancyModel
import kz.tz.domain.repository.VacancyRepository

class VacancyRepositoryImpl(
    private val placeholderService: MockPlaceholderService,
    private val vacancyMapper: VacancyMapper,
    private val offerMapper: OfferMapper,
    private val vacancyDoa: FavouriteVacancyDoa
) : VacancyRepository {

    override suspend fun getVacancies(): List<VacancyModel> {

        return try {
            val information = placeholderService.getInformation()
            val vacancies = information.vacancies ?: emptyList()  // Ensure it's not null
            Log.e("VacancyRepositoryImpl", "API Response: $information")
            vacancies.map { vacancyMapper.getFromRemoteToDomain(it) }


        } catch (e: Exception) {
            Log.e("VacancyRepositoryImpl", "Error fetching vacancies: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getOffers(): List<OfferModel>? {
        return try {
            val information = placeholderService.getInformation()
            information.offers.map { offerMapper.fromRemoteToDomain(it) }
        } catch (e: Exception) {
            Log.e("VacancyRepositoryImpl", "Error fetching offers: ${e.message}")
            emptyList()
        }
    }

    override fun getFavouriteVacancies(): Flow<List<VacancyModel>> {
        return vacancyDoa.getFavouriteVacancies().map { favVacanciesFromDB ->
            val favVacanciesFromDBToDomain =
                favVacanciesFromDB.map { vacancyMapper.getFromLocalToDomain(it) }

            favVacanciesFromDBToDomain // Emit current favourite vacancies from DB
        }.onEach { favVacanciesFromDBToDomain ->
            try {
                val allVacancies = getVacancies() // Fetch latest vacancies from API
                val favVacanciesFromAPI = allVacancies.filter { it.isFavorite }

                // Check if we need to update the local database
                if (favVacanciesFromAPI.toSet() != favVacanciesFromDBToDomain.toSet()) {
                    val favVacanciesEntity =
                        favVacanciesFromAPI.map { vacancyMapper.getFromDomainToLocal(it) }
                    vacancyDoa.insertVacancies(favVacanciesEntity) // Update DB with new favourite vacancies
                }
            } catch (e: Exception) {
                Log.e("VacancyRepositoryImpl", "Error updating favorite vacancies: ${e.message}")
            }
        }.flowOn(Dispatchers.IO) // Run in the IO dispatcher
    }


    override suspend fun toggleFavoriteVacancy(vacancyModel: VacancyModel) {

        withContext(Dispatchers.IO) {
            val newState = vacancyModel.isFavorite
            vacancyDoa.updateFavoriteStatus(vacancyModel.id, newState)

            if (newState) {
                // Insert only if it's marked as favorite
                vacancyDoa.insertVacancy(vacancyMapper.getFromDomainToLocal(vacancyModel.copy(isFavorite = newState)))
            } else {
                vacancyDoa.deleteVacancy(vacancyMapper.getFromDomainToLocal(vacancyModel.copy(isFavorite = newState)))

            }
        }
    }

}
