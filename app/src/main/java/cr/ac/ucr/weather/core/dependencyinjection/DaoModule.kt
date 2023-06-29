package cr.ac.ucr.weather.core.dependencyinjection

import cr.ac.ucr.weather.data.datasource.local.database.room.CityDAO
import cr.ac.ucr.weather.data.datasource.local.database.room.ForecastDAO
import cr.ac.ucr.weather.data.datasource.local.database.room.WeatherDatabase
import cr.ac.ucr.weather.data.datasource.local.database.room.WeatherDatabase_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    @Singleton
    fun bindCityDAO(weatherDatabase: WeatherDatabase): CityDAO = weatherDatabase.cityDAO()

    @Provides
    @Singleton
    fun bindForecastDAO(weatherDatabase: WeatherDatabase): ForecastDAO = weatherDatabase.forecastDAO()

    //TODO: Add city dao
}