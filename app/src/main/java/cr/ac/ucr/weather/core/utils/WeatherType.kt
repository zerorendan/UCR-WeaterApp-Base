package cr.ac.ucr.weather.core.utils

import cr.ac.ucr.weather.R
import cr.ac.ucr.weather.core.utils.constant.WeatherConditions

sealed class WeatherType(val weatherDescription: String, val id: Int) {
    object ClearSkyDay : WeatherType(
        weatherDescription = R.string.clear_sky_condition.toString(),
        id = R.drawable.clear_day
    )

    object ClearSkyNight : WeatherType(
        weatherDescription = R.string.clear_sky_condition.toString(),
        id = R.drawable.clear_night
    )

    object FewCloudsDay : WeatherType(
        weatherDescription = R.string.few_cloud_condition.toString(),
        id = R.drawable.few_cloud_day
    )

    object FewCloudsNight : WeatherType(
        weatherDescription = R.string.few_cloud_condition.toString(),
        id = R.drawable.few_cloud_night
    )

    object ScatteredClouds : WeatherType(
        weatherDescription = R.string.scattered_cloud_condition.toString(),
        id = R.drawable.scattered_cloud
    )

    object BrokenCloudsDay : WeatherType(
        weatherDescription = R.string.broken_clouds_condition.toString(),
        id = R.drawable.few_cloud_day
    )

    object BrokenCloudsNight : WeatherType(
        weatherDescription = R.string.broken_clouds_condition.toString(),
        id = R.drawable.few_cloud_night
    )

    object ShowerRain : WeatherType(
        weatherDescription = R.string.shower_rain.toString(),
        id = R.drawable.shower_rain
    )

    object Rain : WeatherType(
        weatherDescription = R.string.rain.toString(),
        id = R.drawable.rain
    )

    object Thunderstorm : WeatherType(
        weatherDescription = R.string.thunderstorm.toString(),
        id = R.drawable.thunderstorm
    )

    object Snow : WeatherType(
        weatherDescription = R.string.snow.toString(),
        id = R.drawable.snow
    )

    object Mist : WeatherType(
        weatherDescription = R.string.snow.toString(),
        id = R.drawable.snow
    )

    companion object {
        fun setWeatherType(
            mainDescription: String,
            weatherDescription: String,
            hour: String
        ): Int {
            when (mainDescription) {
                WeatherConditions.CLEAR -> {
                    return if (checkTime(hour)) {
                        ClearSkyNight.id
                    } else {
                        ClearSkyDay.id
                    }
                }

                WeatherConditions.CLOUDS -> {
                    return if (weatherDescription == ScatteredClouds.weatherDescription) {
                        ScatteredClouds.id
                    } else if (weatherDescription == FewCloudsDay.weatherDescription) {
                        if (checkTime(hour)) {
                            FewCloudsNight.id
                        } else {
                            FewCloudsDay.id
                        }
                    } else {
                        if (checkTime(hour)) {
                            BrokenCloudsNight.id
                        } else {
                            BrokenCloudsDay.id
                        }
                    }
                }

                WeatherConditions.RAIN -> {
                    return if (weatherDescription == ShowerRain.weatherDescription) {
                        ShowerRain.id
                    } else {
                        Rain.id
                    }
                }

                WeatherConditions.SNOW -> {
                    return Snow.id
                }

                WeatherConditions.THUNDERSTORM -> {
                    return Thunderstorm.id
                }

                else -> {
                    return Mist.id
                }
            }
        }

        private fun checkTime(hour: String): Boolean {
            return if ((hour.substring(0, 2) == "00" || hour.substring(
                    0,
                    2
                ) == "03" || hour.substring(0, 2) == "06") && hour.substring(3, 5) == "AM"
            ) {
                true
            } else if (hour.substring(0, 2) == "12" && hour.substring(3, 5) == "AM") {
                true
            } else (hour.substring(0, 2) == "09") && hour.substring(3, 5) == "PM"
        }
    }
}


