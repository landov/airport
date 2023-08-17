package hu.landov.airport.common.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import hu.landov.airport.common.location.GeoLocationPermissionChecker
import hu.landov.airport.common.location.GeoLocationPermissionCheckerImpl

@Module
@InstallIn(ActivityComponent::class)
object LocationModule{
    @Provides
    @ActivityScoped
    fun provideGeoLocationPermissionChecker(@ActivityContext context : Context): GeoLocationPermissionChecker {
        return GeoLocationPermissionCheckerImpl(context)
    }
}
