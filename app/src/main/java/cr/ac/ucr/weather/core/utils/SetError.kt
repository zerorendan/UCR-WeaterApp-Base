package cr.ac.ucr.weather.core.utils

import cr.ac.ucr.weather.core.utils.constant.ErrorDescription
import cr.ac.ucr.weather.core.utils.constant.ErrorMessage

object SetError {
    fun errorCard(title: String): String {
        return when (title) {
            ErrorMessage.GPS_DISABLED -> ErrorDescription.GPS_DISABLED
            ErrorMessage.NO_INTERNET_CONNECTION -> ErrorDescription.NO_INTERNET_CONNECTION
            ErrorMessage.NO_PERMISSION -> ErrorDescription.NO_PERMISSION
            else -> ErrorDescription.UNKNOWN_ERROR
        }
    }
}