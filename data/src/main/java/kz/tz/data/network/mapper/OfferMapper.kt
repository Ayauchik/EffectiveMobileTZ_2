package kz.tz.data.network.mapper

import android.util.Log
import kz.tz.data.network.response.Offer
import kz.tz.domain.models.OfferModel

class OfferMapper(
    private val buttonMapper: ButtonMapper
) {

    fun fromRemoteToDomain(offer: Offer): OfferModel {
        Log.e("mapper", "mapping offer")
        val offerModel = OfferModel(
            id = offer.id,
            title = offer.title,
            link = offer.link,
            button = offer.button?.text
        )
        Log.e("mapper", offerModel.title)
        return offerModel

    }
}