package cr.ac.ucr.weather.data.location

import android.app.Application
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import cr.ac.ucr.weather.core.utils.constant.ErrorMessage
import cr.ac.ucr.weather.domain.location.LocationHandlerInterface
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class LocationHandlerImpl @Inject constructor(
    private val locationProviderClient: FusedLocationProviderClient,
    private val application: Application
) : LocationHandlerInterface {

    override suspend fun getLocation(): Location? {
        val isAccessFineLocationPermissionEnable = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val isAccessCoarseLocationPermissionEnabled = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val isGPSEnabled =
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER
            )

        if (!isAccessCoarseLocationPermissionEnabled || !isAccessFineLocationPermissionEnable) {
            throw Exception(ErrorMessage.NO_PERMISSION)
        } else if (!isGPSEnabled) {
            throw Exception(ErrorMessage.GPS_DISABLED)
        }

        return suspendCancellableCoroutine { cancellableContinuation ->
            locationProviderClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        cancellableContinuation.resume(result)
                    } else {
                        cancellableContinuation.resume(null)
                    }
                    return@suspendCancellableCoroutine
                }

                addOnSuccessListener {
                    cancellableContinuation.resume(it)
                }
                addOnFailureListener {
                    cancellableContinuation.cancel(it.cause)
                }
                addOnCanceledListener {
                    cancellableContinuation.cancel()
                }
            }
        }
    }

}
