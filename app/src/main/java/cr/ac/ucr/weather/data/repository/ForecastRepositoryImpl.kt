package cr.ac.ucr.weather.data.repository

import cr.ac.ucr.weather.core.common.Resource
import cr.ac.ucr.weather.core.utils.constant.ErrorMessage
import cr.ac.ucr.weather.data.datasource.ForecastRemoteDataSource
import cr.ac.ucr.weather.data.datasource.local.database.CityLocalDataSource
import cr.ac.ucr.weather.data.datasource.local.database.ForecastLocalDataSource
import cr.ac.ucr.weather.data.mapper.ForecastDataTransferObjectMapper
import cr.ac.ucr.weather.data.mapper.ForecastEntityMapper
import cr.ac.ucr.weather.domain.model.ForecastModel
import cr.ac.ucr.weather.domain.repository.ForecastRepositoryInterface
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val forecastRemoteDataSource: ForecastRemoteDataSource,
    private val forecastLocalDataSource: ForecastLocalDataSource,
    private val forecastDTOMapper: ForecastDataTransferObjectMapper,
    private val forecastEntityMapper: ForecastEntityMapper,
    private val cityLocalDataSource: CityLocalDataSource
) : ForecastRepositoryInterface {

    override suspend fun readForecastBy(
        latitude: Double,
        longitude: Double
    ): Resource<ForecastModel> {
        return try {
            Resource.Success(
                forecastDTOMapper.mapFromEntity(
                    forecastRemoteDataSource.getForecast(
                        latitude,
                        longitude
                    )
                )
            )
        } catch (error: Exception) {
            Resource.Error(error.message ?: ErrorMessage.UNKNOWN_ERROR)
        }
    }

    override suspend fun createForecastWeather(forecast: ForecastModel) {
        forecastLocalDataSource.addForecastWeather(
            forecastEntityMapper.entityFromModel(forecast)
        )
    }

    override suspend fun updateForecastWeather(forecast: ForecastModel) {
        forecastLocalDataSource.updateForecastWeather(
            forecastEntityMapper.entityFromModel(forecast)
        )
    }

    override fun readForecastWeather(): ForecastModel? {
        return if (forecastLocalDataSource.readForecastWeather().isNullOrEmpty()) {
            null
        } else {
            forecastEntityMapper.mapFromEntity(
                forecastLocalDataSource.readForecastWeather(),
                cityLocalDataSource.readCity()
            )
        }
    }
}
