package kz.tz.data.network.response

import com.google.gson.annotations.SerializedName

data class Button(
    @SerializedName("text")
    val text: String
)