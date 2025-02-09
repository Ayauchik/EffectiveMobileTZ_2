package kz.tz.data.network.response

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("house")
    val house: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("town")
    val town: String
)