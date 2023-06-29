package cr.ac.ucr.weather.data.datasource.local.database.room

import androidx.room.Dao

import androidx.room.Query
import cr.ac.ucr.weather.core.utils.constant.Database
import cr.ac.ucr.weather.data.datasource.local.database.entity.CityEntity

@Dao
interface CityDAO {
    //TODO: Add create city

    @Query(Database.READ_ALL_CITY)
    fun readCity(): CityEntity

    //TODO: Add update city
}
