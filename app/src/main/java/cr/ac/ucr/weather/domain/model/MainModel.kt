package cr.ac.ucr.weather.domain.model

data class MainModel(
    val temp: Double,
    val feelsLike: Double,
    val pressure: Double,
    val humidity: Int,
)
