package hu.landov.airport.common.di

import android.content.Context
import hu.landov.airport.common.location.GeoLocationPermissionCheckerImpl

const val LOCATION_MANAGER = "LocationManager"
const val GEO_PERMISSION_CHECKER = "GeoPermissionChecker"

class ServiceLocatorImpl(val context: Context) : ServiceLocator {
    @Suppress("UNCHECKED_CAST")
    override fun <A : Any> lookUp(name: String): A = when (name) {
        LOCATION_MANAGER -> context.getSystemService(Context.LOCATION_SERVICE)
        GEO_PERMISSION_CHECKER -> GeoLocationPermissionCheckerImpl(context)
        else -> throw IllegalArgumentException("No component lookup the key: $name")
    } as A


}