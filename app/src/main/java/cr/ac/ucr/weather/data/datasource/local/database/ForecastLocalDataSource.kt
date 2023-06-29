package cr.ac.ucr.weather.data.datasource.local.database

import cr.ac.ucr.weather.data.datasource.local.database.entity.ForecastEntity
import cr.ac.ucr.weather.data.datasource.local.database.room.ForecastDAO
import javax.inject.Inject

class ForecastLocalDataSource @Inject constructor(
    private val forecastDAO: ForecastDAO,
) {
    suspend fun addForecastWeather(forecastEntity: ForecastEntity) =
        forecastDAO.createForecastWeather(forecastEntity)

    fun readForecastWeather() = forecastDAO.readForecastWeather()

    suspend fun updateForecastWeather(forecastEntity: ForecastEntity) =
        forecastDAO.updateForecastWeather(forecastEntity)

    //TODO: Add by city
}
