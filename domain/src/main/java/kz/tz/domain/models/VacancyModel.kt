package kz.tz.domain.models

data class VacancyModel(
    val id: String,
    val lookingNumber: Int,
    val isFavorite: Boolean,
    val title: String,
    val town: String,
    val company: String,
    val experiencePreviewText: String,
    val publishedDate: String,
)
