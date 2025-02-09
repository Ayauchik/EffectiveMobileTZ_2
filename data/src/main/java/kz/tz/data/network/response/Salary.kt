package kz.tz.data.network.response

import com.google.gson.annotations.SerializedName

data class Salary(
    @SerializedName("full")
    val full: String,
    @SerializedName("short")
    val short: String
)