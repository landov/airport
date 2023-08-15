package hu.landov.airport.common.providers

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationProvider
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hu.landov.airport.common.domain.airport.Airport
import hu.landov.airport.common.domain.location.LocationStateProvider
import hu.landov.airport.common.domain.location.LocationState
import hu.landov.airport.common.domain.wind.WindState
import hu.landov.airport.util.calcDirection
import hu.landov.airport.util.calcDistance
import javax.inject.Inject

//TODO loactionManager to be injected
class GpsLocationStateProvider @Inject constructor (val locationManager: LocationManager) : LocationStateProvider {

    private val TAG = "GpsLocationStateProvider"

    private var airport: Airport? = null
    private val locationStateLiveData = MutableLiveData<LocationState>()

    val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            val lat = location.latitude
            val long = location.longitude
            val alt = location.altitude
            val speed = if (location.hasSpeed()) (location.speed) * 3.6 else 0.0
            val distance =
                if (airport != null)
                    calcDistance(
                        airport!!.latitude.toDouble(),
                        airport!!.longitude.toDouble(),
                        lat,
                        long
                    )
                else 0.0
            val direction =
                if (airport != null) calcDirection(
                    lat,
                    long,
                    airport!!.latitude.toDouble(),
                    airport!!.longitude.toDouble()
                )
                else 0.0

            val locationState = LocationState(
                altitude = alt,
                speed = speed,
                distance = distance,
                direction = direction
            )

            locationStateLiveData.postValue(locationState)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            when (status) {
                LocationProvider.OUT_OF_SERVICE -> {}
                LocationProvider.TEMPORARILY_UNAVAILABLE -> {}
            }
        }
    }

    init {
        try {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                100L,
                3F,
                locationListener
            )
        } catch (e: SecurityException) {
            Log.e(TAG, "Missing location premission")
        }
    }

    override fun getLocationState(airport: Airport): LiveData<LocationState> {
        this.airport = airport
        return locationStateLiveData
    }


}