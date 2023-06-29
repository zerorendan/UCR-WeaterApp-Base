package cr.ac.ucr.weather.data.datasource.local.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import cr.ac.ucr.weather.data.datasource.local.database.entity.CityEntity
import cr.ac.ucr.weather.data.datasource.local.database.entity.ForecastEntity

@Database(entities = [CityEntity::class, ForecastEntity::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    //TODO: Add by city
    abstract fun forecastDAO(): ForecastDAO

    abstract fun cityDAO(): CityDAO

}