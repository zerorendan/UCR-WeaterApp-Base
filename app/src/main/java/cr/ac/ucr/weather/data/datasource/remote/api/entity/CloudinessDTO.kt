package cr.ac.ucr.weather.data.datasource.remote.api.entity

import com.google.gson.annotations.SerializedName

data class CloudinessDTO(
    @SerializedName("all") val cloudiness: Int
)
