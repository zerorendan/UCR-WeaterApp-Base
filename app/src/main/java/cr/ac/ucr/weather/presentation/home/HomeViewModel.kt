package cr.ac.ucr.weather.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cr.ac.ucr.weather.core.common.Resource
import cr.ac.ucr.weather.core.utils.constant.ErrorMessage
import cr.ac.ucr.weather.domain.model.CityModel
import cr.ac.ucr.weather.domain.model.ForecastModel
import cr.ac.ucr.weather.domain.usecase.forecast.AddForecastToDatabaseUseCase
import cr.ac.ucr.weather.domain.usecase.forecast.GetForecastFromDatabaseUseCase
import cr.ac.ucr.weather.domain.usecase.forecast.GetForecastFromRemoteUseCase
import cr.ac.ucr.weather.domain.usecase.location.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addForecastDatabase: AddForecastToDatabaseUseCase,
    private val getCurrentLocation: GetLocationUseCase,
    private val getForecastRemote: GetForecastFromRemoteUseCase,
    private val getForecastDatabase: GetForecastFromDatabaseUseCase
) : ViewModel() {
    private val _homeForecastState = MutableStateFlow<HomeForecastState>(HomeForecastState.Loading)
    val homeForecastState = _homeForecastState.asStateFlow()

    //NOTE: Location related logic

    fun loadLocation() {
        _homeForecastState.value = HomeForecastState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val location = getCurrentLocation.getLocationUseCase()
                location?.let {
                    fetchForecast(location.latitude, location.longitude)
                    return@launch
                }

                if (isForecastSaved()) {
                    getForecastSaved()
                    return@launch
                }

                _homeForecastState.value =
                    HomeForecastState.Error(ErrorMessage.NO_INTERNET_CONNECTION)
            } catch (exception: Exception) {
                if (isForecastSaved()) {
                    getForecastSaved()
                } else {
                    _homeForecastState.value = HomeForecastState.Error(exception.message)
                }
            }
        }
    }

    private suspend fun fetchForecast(latitude: Double, longitude: Double) {
        when (val result = getForecastRemote.getForecastUseCase(latitude, longitude)) {
            is Resource.Success -> {
                _homeForecastState.value = HomeForecastState.Success(result.data)
                result.data?.let {
                    if (!isForecastSaved()) {
                        saveForecast(result.data, result.data.cityDTO)
                    } else {
                        // TODO: add update method
                    }
                }
            }

            is Resource.Error -> {
                _homeForecastState.value = HomeForecastState.Error(result.message)
            }
        }
    }

    private fun isForecastSaved(): Boolean {
        return getForecastDatabase.getForecastFromDatabaseUseCase() != null
    }

    private fun getForecastSaved() {
        _homeForecastState.value =
            HomeForecastState.Success(getForecastDatabase.getForecastFromDatabaseUseCase())
    }

    private suspend fun saveForecast(forecastModel: ForecastModel, cityModel: CityModel) {
        addForecastDatabase.addForecastToDatabaseUseCase(
            forecastModel,
            forecastModel.weatherList.size
        )
        // TODO: Save city to db
    }
}