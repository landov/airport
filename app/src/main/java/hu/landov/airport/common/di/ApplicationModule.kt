package hu.landov.airport.common.di

import android.app.Application
import android.content.Context
import android.location.LocationManager
import android.util.Log
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import hu.landov.airport.common.di.scopes.ApplicationScope
import hu.landov.airport.common.domain.location.LocationStateProvider
import hu.landov.airport.common.domain.wind.WindStateProvider
import hu.landov.airport.common.location.GeoLocationPermissionChecker
import hu.landov.airport.common.location.GeoLocationPermissionCheckerImpl
import hu.landov.airport.common.providers.GpsLocationStateProvider
import hu.landov.airport.common.providers.IdokepWindStateProvider

@Module(
    includes = [
        ApplicationModule.Bindings::class,
        AndroidSupportInjectionModule::class
    ]
)
object ApplicationModule {

    @Provides
    @ApplicationScope
    fun provideLocationManager(application: Application): LocationManager {
        return application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @ApplicationScope
    @Provides
    fun provideGeoLocationPermissionChecker(application: Application): GeoLocationPermissionChecker {
        Log.d("PROVIDEING", "permissionchecker")
        return GeoLocationPermissionCheckerImpl(application)
    }

    @Module
    interface Bindings {
        @Binds
        @ApplicationScope
        fun bindWindStateProvider(impl: IdokepWindStateProvider): WindStateProvider

        @Binds
        @ApplicationScope
        fun bindLocationStateProvider(impl: GpsLocationStateProvider): LocationStateProvider
    }

}