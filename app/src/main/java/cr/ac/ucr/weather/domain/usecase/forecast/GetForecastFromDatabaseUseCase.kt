package cr.ac.ucr.weather.domain.usecase.forecast

import cr.ac.ucr.weather.data.repository.ForecastRepositoryImpl
import cr.ac.ucr.weather.domain.model.ForecastModel
import javax.inject.Inject

class GetForecastFromDatabaseUseCase @Inject constructor(private val forecastRepository: ForecastRepositoryImpl) {
    fun getForecastFromDatabaseUseCase(): ForecastModel? = forecastRepository.readForecastWeather()
}
