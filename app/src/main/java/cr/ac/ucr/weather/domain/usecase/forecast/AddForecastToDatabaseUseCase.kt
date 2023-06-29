package cr.ac.ucr.weather.domain.usecase.forecast

import cr.ac.ucr.weather.data.repository.ForecastRepositoryImpl
import cr.ac.ucr.weather.domain.model.ForecastModel
import cr.ac.ucr.weather.domain.model.ForecastWeatherModel
import javax.inject.Inject

class AddForecastToDatabaseUseCase @Inject constructor(private val forecastRepository: ForecastRepositoryImpl) {
    suspend fun addForecastToDatabaseUseCase(forecast: ForecastModel, forecastSize: Int) {
        for (index in 1..forecastSize) {
            forecastRepository.createForecastWeather(
                ForecastModel(
                    listOf(
                        ForecastWeatherModel(
                            index,
                            forecast.weatherList[index - 1].weatherData,
                            forecast.weatherList[index - 1].weatherStatus,
                            forecast.weatherList[index - 1].wind,
                            forecast.weatherList[index - 1].date,
                            forecast.weatherList[index - 1].cloudiness
                        )
                    ),
                    forecast.cityDTO
                )
            )
        }
    }
}
