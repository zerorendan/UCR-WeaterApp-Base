package cr.ac.ucr.weather.domain.model

data class CityModel(
    val country: String,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long,
    val cityName: String,
    val coordinate: CoordinateModel
)
