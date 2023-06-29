package cr.ac.ucr.weather.data.datasource.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cr.ac.ucr.weather.core.utils.constant.Database

@Entity(tableName = Database.CITY_TABLE)
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var identifier: Int = 0,

    @ColumnInfo(name = "country")
    var country: String,

    @ColumnInfo(name = "timezone")
    var timezone: Int,

    @ColumnInfo(name = "sunrise")
    var sunrise: Long,

    @ColumnInfo(name = "sunset")
    var sunset: Long,

    @ColumnInfo(name = "city_name")
    var cityName: String,

    @ColumnInfo(name = "latitude")
    var latitude: Double,

    @ColumnInfo(name = "longitude")
    var longitude: Double
)
