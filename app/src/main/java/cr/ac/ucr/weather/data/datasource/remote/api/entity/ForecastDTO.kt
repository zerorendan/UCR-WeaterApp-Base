package cr.ac.ucr.weather.data.datasource.remote.api.entity

import com.google.gson.annotations.SerializedName

data class ForecastDTO(
    @SerializedName("list") val weatherList: List<ForecastWeatherDTO>,
    @SerializedName("city") val cityDTO: CityDTO
)
