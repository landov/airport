package hu.landov.airport.common.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class GeoLocationPermissionCheckerImpl(val context: Context) : GeoLocationPermissionChecker{

    override val isPermissionGiven: Boolean
        get() = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
}