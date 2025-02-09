package kz.tz.data.network.mapper

import kz.tz.data.network.response.Vacancy
import kz.tz.data.room.entity.VacancyEntity
import kz.tz.domain.models.VacancyModel

class VacancyMapper(
    private val addressMapper: AddressMapper,
    private val experienceMapper: ExperienceMapper
) {

    fun getFromRemoteToDomain(vacancy: Vacancy): VacancyModel {
        return VacancyModel(
            lookingNumber = vacancy.lookingNumber,
            isFavorite = vacancy.isFavorite,
            title = vacancy.title,
            town = addressMapper.fromRemoteToDomain(vacancy.address).town,
            company = vacancy.company,
            experiencePreviewText = experienceMapper.fromRemoteToDomain(vacancy.experience).previewText,
            publishedDate = vacancy.publishedDate,
            id = vacancy.id
        )
    }

    fun getFromLocalToDomain(vacancyEntity: VacancyEntity): VacancyModel {
        return VacancyModel(
            id = vacancyEntity.id,
            lookingNumber = vacancyEntity.lookingNumber,
            isFavorite = vacancyEntity.isFavorite,
            title = vacancyEntity.title,
            town = vacancyEntity.town,
            company = vacancyEntity.company,
            experiencePreviewText = vacancyEntity.experiencePreviewText,
            publishedDate = vacancyEntity.publishedDate
        )
    }

    fun getFromDomainToLocal(vacancy: VacancyModel): VacancyEntity {
        return VacancyEntity(
            lookingNumber = vacancy.lookingNumber,
            isFavorite = vacancy.isFavorite,
            title = vacancy.title,
            town = vacancy.town,
            company = vacancy.company,
            experiencePreviewText = vacancy.experiencePreviewText,
            publishedDate = vacancy.publishedDate,
            id = vacancy.id,
        )
    }

}