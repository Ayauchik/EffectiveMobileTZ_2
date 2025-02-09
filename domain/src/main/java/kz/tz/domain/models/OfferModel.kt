package kz.tz.domain.models

data class OfferModel(
    val id: String?,
    val title: String,
    val link: String,
    val button: String? = null
)
