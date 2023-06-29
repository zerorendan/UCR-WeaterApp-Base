package cr.ac.ucr.weather.data.mapper

import cr.ac.ucr.weather.data.datasource.remote.api.entity.CityDTO
import cr.ac.ucr.weather.data.datasource.remote.api.entity.CloudinessDTO
import cr.ac.ucr.weather.data.datasource.remote.api.entity.CoordinateDTO
import cr.ac.ucr.weather.data.datasource.remote.api.entity.ForecastDTO
import cr.ac.ucr.weather.data.datasource.remote.api.entity.ForecastWeatherDTO
import cr.ac.ucr.weather.data.datasource.remote.api.entity.MainDTO
import cr.ac.ucr.weather.data.datasource.remote.api.entity.WeatherDTO
import cr.ac.ucr.weather.data.datasource.remote.api.entity.WindDTO
import cr.ac.ucr.weather.domain.mapper.InterfaceEntityMapper
import cr.ac.ucr.weather.domain.model.CityModel
import cr.ac.ucr.weather.domain.model.CloudinessModel
import cr.ac.ucr.weather.domain.model.CoordinateModel
import cr.ac.ucr.weather.domain.model.ForecastModel
import cr.ac.ucr.weather.domain.model.ForecastWeatherModel
import cr.ac.ucr.weather.domain.model.MainModel
import cr.ac.ucr.weather.domain.model.WeatherModel
import cr.ac.ucr.weather.domain.model.WindModel
import javax.inject.Inject

class ForecastDataTransferObjectMapper @Inject constructor() :
    InterfaceEntityMapper<ForecastDTO, ForecastModel> {
    override fun mapFromEntity(entity: ForecastDTO): ForecastModel {
        val forecastWeatherList: List<ForecastWeatherModel> = entity.weatherList.map {
            ForecastWeatherModel(
                weatherData = MainModel(
                    it.weatherData.temp,
                    it.weatherData.feelsLike,
                    it.weatherData.pressure,
                    it.weatherData.humidity
                ),
                weatherStatus = listOf(
                    WeatherModel(
                        it.weatherStatus[0].mainDescription,
                        it.weatherStatus[0].description
                    )
                ),
                wind = WindModel(it.wind.speed),
                date = it.date,
                cloudiness = CloudinessModel(it.cloudinessDTO.cloudiness)
            )
        }
        return ForecastModel(
            forecastWeatherList,
            CityModel(
                entity.cityDTO.country,
                entity.cityDTO.timezone,
                entity.cityDTO.sunrise,
                entity.cityDTO.sunset,
                entity.cityDTO.cityName,
                CoordinateModel(
                    entity.cityDTO.coordinate.latitude,
                    entity.cityDTO.coordinate.longitude
                )
            )
        )
    }

    override fun entityFromModel(model: ForecastModel): ForecastDTO {
        val forecastWeatherDTOList: List<ForecastWeatherDTO> = model.weatherList.map {
            ForecastWeatherDTO(
                MainDTO(
                    it.weatherData.temp,
                    it.weatherData.feelsLike,
                    it.weatherData.pressure,
                    it.weatherData.humidity
                ),
                listOf(
                    WeatherDTO(it.weatherStatus[0].mainDescription, it.weatherStatus[0].description)
                ),
                WindDTO(it.wind.speed),
                it.date,
                CloudinessDTO(it.cloudiness.cloudiness)
            )
        }
        return ForecastDTO(
            forecastWeatherDTOList,
            CityDTO(
                model.cityDTO.country,
                model.cityDTO.timezone,
                model.cityDTO.sunrise,
                model.cityDTO.sunset,
                model.cityDTO.cityName,
                CoordinateDTO(
                    model.cityDTO.coordinate.latitude,
                    model.cityDTO.coordinate.longitude
                )
            )
        )
    }

}
