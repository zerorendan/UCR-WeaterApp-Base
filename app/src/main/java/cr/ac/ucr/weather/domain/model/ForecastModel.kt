package cr.ac.ucr.weather.domain.model

data class ForecastModel(
    val weatherList: List<ForecastWeatherModel>,
    val cityDTO: CityModel
)
