package cr.ac.ucr.weather.domain.location

import android.location.Location

interface LocationHandlerInterface {
    suspend fun getLocation(): Location?
}
