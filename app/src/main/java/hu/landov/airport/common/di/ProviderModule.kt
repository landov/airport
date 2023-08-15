package hu.landov.airport.common.di

import android.content.Context
import android.location.LocationManager
import androidx.core.content.getSystemService
import dagger.Binds
import dagger.Module
import dagger.Provides
import hu.landov.airport.common.domain.location.LocationStateProvider
import hu.landov.airport.common.domain.wind.WindStateProvider
import hu.landov.airport.common.location.GeoLocationPermissionChecker
import hu.landov.airport.common.location.GeoLocationPermissionCheckerImpl
import hu.landov.airport.common.providers.GpsLocationStateProvider
import hu.landov.airport.common.providers.IdokepWindStateProvider

@Module(includes = [ProviderModule.Bindings::class])
class ProviderModule() {

    @Provides
    fun provideLocationManager(context: Context) : LocationManager {
        return context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @Provides
    fun provideGeoLocationPermissionChecker(context: Context) : GeoLocationPermissionChecker{
        return GeoLocationPermissionCheckerImpl(context)
    }

    @Provides
    fun provideGpsLocationStateProwider(context: Context) : LocationStateProvider{
        return GpsLocationStateProvider(
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        )
    }

    @Module
    interface Bindings {
        @Binds
        fun bindWindStateProvider(impl: IdokepWindStateProvider) : WindStateProvider
    }

}