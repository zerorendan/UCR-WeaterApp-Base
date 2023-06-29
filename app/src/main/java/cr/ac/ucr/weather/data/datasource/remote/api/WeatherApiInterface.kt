package cr.ac.ucr.weather.data.datasource.remote.api

import cr.ac.ucr.weather.core.utils.constant.NetworkService
import cr.ac.ucr.weather.data.datasource.remote.api.entity.ForecastDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {

    @GET(NetworkService.FORECAST)
    suspend fun getForecastData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("APPID") apiKey: String = NetworkService.API_KEY,
        @Query("units") units: String = NetworkService.UNITS,
    ): ForecastDTO

    //TODO: Add by city
}
