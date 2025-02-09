package kz.tz.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancies")
data class VacancyEntity(
    @PrimaryKey() val id: String,
    val lookingNumber: Int,
    val isFavorite: Boolean,
    val title: String,
    val town: String,
    val company: String,
    val experiencePreviewText: String,
    val publishedDate: String,
)
