package cr.ac.ucr.weather.domain.usecase.forecast

import cr.ac.ucr.weather.core.common.Resource
import cr.ac.ucr.weather.data.repository.ForecastRepositoryImpl
import cr.ac.ucr.weather.domain.model.ForecastModel
import javax.inject.Inject

class GetForecastFromRemoteUseCase @Inject constructor(private val forecastRepository: ForecastRepositoryImpl) {
    suspend fun getForecastUseCase(latitude: Double, longitude: Double): Resource<ForecastModel> =
        forecastRepository.readForecastBy(latitude, longitude)
}
