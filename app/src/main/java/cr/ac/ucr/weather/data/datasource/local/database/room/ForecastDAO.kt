package cr.ac.ucr.weather.data.datasource.local.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cr.ac.ucr.weather.core.utils.constant.Database
import cr.ac.ucr.weather.data.datasource.local.database.entity.ForecastEntity

@Dao
interface ForecastDAO {
    @Insert
    suspend fun createForecastWeather(forecastEntity: ForecastEntity)

    @Query(Database.READ_ALL_FORECAST)
    fun readForecastWeather(): List<ForecastEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateForecastWeather(forecastEntity: ForecastEntity)
}
