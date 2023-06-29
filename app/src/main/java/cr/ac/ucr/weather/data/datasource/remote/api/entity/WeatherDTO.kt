package cr.ac.ucr.weather.data.datasource.remote.api.entity

import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    @SerializedName("main") val mainDescription: String,
    @SerializedName("description") val description: String
)


