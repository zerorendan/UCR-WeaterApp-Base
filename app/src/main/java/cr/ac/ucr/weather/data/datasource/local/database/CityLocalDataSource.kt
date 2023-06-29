package cr.ac.ucr.weather.data.datasource.local.database

import cr.ac.ucr.weather.data.datasource.local.database.entity.CityEntity
import cr.ac.ucr.weather.data.datasource.local.database.room.CityDAO
import javax.inject.Inject

class CityLocalDataSource @Inject constructor(
    private val cityDAO: CityDAO,
) {
    //TODO: Add create city

    fun readCity() = cityDAO.readCity()

    //TODO: Add update city
}
