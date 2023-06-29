package cr.ac.ucr.weather.data.datasource.remote.api.entity

import com.google.gson.annotations.SerializedName

data class WindDTO(
    @SerializedName("speed") val speed: Double,
)
