package kz.tz.data.network.response

import com.google.gson.annotations.SerializedName


data class GetInformationResponse(
    @SerializedName("offers")
    val offers: List<Offer>,
    @SerializedName("vacancies")
    val vacancies: List<Vacancy>
)