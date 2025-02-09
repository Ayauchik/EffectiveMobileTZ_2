package kz.tz.data.use_cases

import android.util.Log
import kz.tz.domain.models.OfferModel
import kz.tz.domain.repository.VacancyRepository
import kz.tz.domain.use_cases.GetOffersUseCase

class GetOffersUseCaseImpl(
    private val vacancyRepository: VacancyRepository
):GetOffersUseCase {
    override suspend fun invoke(): List<OfferModel>? {
        Log.e("use case", "getting offer model")
        val offers = vacancyRepository.getOffers()
        //Log.e("use case", offers[0].title)
        return offers
    }
}