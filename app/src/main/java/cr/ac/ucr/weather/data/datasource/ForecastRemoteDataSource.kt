package cr.ac.ucr.weather.data.datasource

import cr.ac.ucr.weather.data.datasource.remote.api.WeatherApiInterface
import javax.inject.Inject

class ForecastRemoteDataSource @Inject constructor(private val api: WeatherApiInterface) {
    suspend fun getForecast(latitude: Double, longitude: Double) =
        api.getForecastData(latitude, longitude)

    //TODO: Add by city
}
