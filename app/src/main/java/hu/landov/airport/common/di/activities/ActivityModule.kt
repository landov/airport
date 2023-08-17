package hu.landov.airport.common.di.activities

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
object ActivityModule{
    @Provides
    @ActivityScoped
    fun provideGeoLocationPermissionChecker(@ActivityContext context : Context): GeoLocationPermissionChecker {
        return GeoLocationPermissionCheckerImpl(context)
    }
}
