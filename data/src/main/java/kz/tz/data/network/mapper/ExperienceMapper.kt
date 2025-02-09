package kz.tz.data.network.mapper

import kz.tz.data.network.response.Experience
import kz.tz.domain.models.ExperienceModel

class ExperienceMapper {

    fun fromRemoteToDomain(experience: Experience): ExperienceModel{
        return ExperienceModel(
            previewText = experience.previewText,
            text = experience.text,
        )
    }
}