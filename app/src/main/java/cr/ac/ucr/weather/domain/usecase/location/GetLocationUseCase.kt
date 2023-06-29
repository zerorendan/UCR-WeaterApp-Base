package cr.ac.ucr.weather.domain.usecase.location

import cr.ac.ucr.weather.data.location.LocationHandlerImpl
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val locationHandlerImpl: LocationHandlerImpl){
    suspend fun getLocationUseCase() = locationHandlerImpl.getLocation()
}
