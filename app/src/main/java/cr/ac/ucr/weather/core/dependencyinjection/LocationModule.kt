package cr.ac.ucr.weather.core.dependencyinjection

import cr.ac.ucr.weather.data.location.LocationHandlerImpl
import cr.ac.ucr.weather.domain.location.LocationHandlerInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {
    @Binds
    @Singleton
    abstract fun bindLocationHandler(locationHandlerImpl: LocationHandlerImpl): LocationHandlerInterface
}