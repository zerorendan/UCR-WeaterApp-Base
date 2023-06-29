package cr.ac.ucr.weather.core.dependencyinjection

import cr.ac.ucr.weather.data.repository.ForecastRepositoryImpl
import cr.ac.ucr.weather.domain.repository.ForecastRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindForecastRepository(forecastRepositoryImpl: ForecastRepositoryImpl): ForecastRepositoryInterface
}