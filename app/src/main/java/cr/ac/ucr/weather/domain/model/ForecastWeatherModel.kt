package cr.ac.ucr.weather.domain.model

data class ForecastWeatherModel(
    val id: Int = 1,
    val weatherData: MainModel,
    val weatherStatus: List<WeatherModel>,
    val wind: WindModel,
    val date: String,
    val cloudiness: CloudinessModel
)
