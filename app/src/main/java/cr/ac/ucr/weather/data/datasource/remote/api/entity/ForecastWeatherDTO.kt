package cr.ac.ucr.weather.data.datasource.remote.api.entity

import com.google.gson.annotations.SerializedName

data class ForecastWeatherDTO(
    @SerializedName("main") val weatherData: MainDTO,
    @SerializedName("weather") val weatherStatus: List<WeatherDTO>,
    @SerializedName("wind") val wind: WindDTO,
    @SerializedName("dt_txt") val date: String,
    @SerializedName("clouds") val cloudinessDTO: CloudinessDTO
)
