package kz.tz.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class FavouriteEntity(
    @PrimaryKey val id: String,
    val vacancyId: String
)
