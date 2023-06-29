package cr.ac.ucr.weather.data.mapper

import cr.ac.ucr.weather.data.datasource.local.database.entity.CityEntity
import cr.ac.ucr.weather.data.datasource.local.database.entity.ForecastEntity
import cr.ac.ucr.weather.domain.model.CityModel
import cr.ac.ucr.weather.domain.model.CloudinessModel
import cr.ac.ucr.weather.domain.model.CoordinateModel
import cr.ac.ucr.weather.domain.model.ForecastModel
import cr.ac.ucr.weather.domain.model.ForecastWeatherModel
import cr.ac.ucr.weather.domain.model.MainModel
import cr.ac.ucr.weather.domain.model.WeatherModel
import cr.ac.ucr.weather.domain.model.WindModel
import javax.inject.Inject

class ForecastEntityMapper @Inject constructor() {
    fun mapFromEntity(
        forecastEntities: List<ForecastEntity>,
        cityEntity: CityEntity
    ): ForecastModel {
        return ForecastModel(
            forecastEntities.map {
                ForecastWeatherModel(
                    it.id,
                    MainModel(
                        it.temp,
                        it.feelsLike,
                        it.pressure,
                        it.humidity
                    ),
                    listOf(
                        WeatherModel(
                            it.mainDescription,
                            it.description
                        )
                    ),
                    WindModel(
                        it.windSpeed
                    ),
                    it.date,
                    CloudinessModel(it.cloudiness)
                )
            }, CityModel(
                cityEntity.country,
                cityEntity.timezone,
                cityEntity.sunrise,
                cityEntity.sunset,
                cityEntity.cityName,
                CoordinateModel(
                    cityEntity.longitude,
                    cityEntity.latitude
                )
            )
        )
    }

    fun entityFromModel(model: ForecastModel): ForecastEntity {
        return ForecastEntity(
            id = model.weatherList[0].id,
            temp = model.weatherList[0].weatherData.temp,
            feelsLike = model.weatherList[0].weatherData.feelsLike,
            pressure = model.weatherList[0].weatherData.pressure,
            humidity = model.weatherList[0].weatherData.humidity,
            windSpeed = model.weatherList[0].wind.speed,
            description = model.weatherList[0].weatherStatus[0].description,
            mainDescription = model.weatherList[0].weatherStatus[0].mainDescription,
            date = model.weatherList[0].date,
            cloudiness = model.weatherList[0].cloudiness.cloudiness
        )
    }
}
