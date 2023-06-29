package cr.ac.ucr.weather.data.datasource.remote.api.entity

import com.google.gson.annotations.SerializedName

class CoordinateDTO(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double
)
