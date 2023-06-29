package cr.ac.ucr.weather.domain.repository

import cr.ac.ucr.weather.core.common.Resource
import cr.ac.ucr.weather.domain.model.ForecastModel

interface ForecastRepositoryInterface {
    suspend fun readForecastBy(latitude: Double, longitude: Double): Resource<ForecastModel>
    suspend fun createForecastWeather(forecast: ForecastModel)
    suspend fun updateForecastWeather(forecast: ForecastModel)

    fun readForecastWeather(): ForecastModel?
}
