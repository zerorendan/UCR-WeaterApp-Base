package cr.ac.ucr.weather.presentation.home

import cr.ac.ucr.weather.domain.model.ForecastModel

sealed interface HomeForecastState {
    data class Success(val forecast: ForecastModel?) : HomeForecastState
    data class Error(val errorMessage: String?) : HomeForecastState
    object Loading: HomeForecastState
}