package kz.tz.domain.use_cases

import kz.tz.domain.models.OfferModel

interface GetOffersUseCase {
    suspend fun invoke(): List<OfferModel>?
}