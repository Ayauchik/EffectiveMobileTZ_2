package kz.tz.data.network.response

import com.google.gson.annotations.SerializedName

data class Offer(
    @SerializedName("button")
    val button: Button? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("link")
    val link: String,
    @SerializedName("title")
    val title: String
)