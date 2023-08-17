package hu.landov.airport.common.di

import android.app.Application
import android.content.Context
import android.location.LocationManager
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.landov.airport.common.location.GeoLocationPermissionChecker
import hu.landov.airport.common.location.GeoLocationPermissionCheckerImpl
import javax.inject.Singleton

@Module(
    includes = [
        AndroidSupportInjectionModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideLocationManager(@ApplicationContext context: Context): LocationManager {
        return context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

}