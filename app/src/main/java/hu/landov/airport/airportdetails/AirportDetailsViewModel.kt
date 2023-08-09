package hu.landov.airport.airportdetails

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationProvider
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.*
import hu.landov.airport.common.data.AirportEntity
import hu.landov.airport.common.domain.Airport
import hu.landov.airport.common.domain.MPSZEL_URL
import hu.landov.airport.util.calcDirection
import hu.landov.airport.util.calcDistance
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.MalformedURLException
import java.net.URI
import java.util.Timer
import kotlin.concurrent.timer

const val TAG = "AIRPORTDETAILSVIEWMODEL"

//TODO Factor out Location
//TODO Factor out Socket
class AirportDetailsViewModel(
    val airport: Airport,
    locationManager: LocationManager,
) : ViewModel(), LocationListener {

    private var socket: Socket? = null

    val windSpeed = MutableLiveData(" - ")
    val windDirection = MutableLiveData(" - ")
    val landspeed = MutableLiveData(" - ")
    val altitude = MutableLiveData(" - ")
    val distance = MutableLiveData(" - ")
    val direction = MutableLiveData(" - ")


    init {
        Log.d(TAG, "is live with $airport")
        Log.d(TAG, "is live with ${airport.windLink}")
        //TODO refractor listening socket
        if (airport.windLink.isNotBlank()) {
            val uri = URI.create("https:" + airport.windLink)
            Log.d(TAG, uri.toString())
            try {
                socket = IO.socket(uri)
                socket?.on("w") { args ->
                    val argString = args[0].toString()
                    val strings = argString.substring(1, argString.length - 1).split(",")
                    windSpeed.postValue(strings[0] + " km/h")
                    windDirection.postValue(strings[1] + "\u00B0")
                }
                socket?.connect()
            } catch (e: MalformedURLException) {
                //TODO handle
                e.printStackTrace()
            }
        }
        //TODO refractor listening location
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100L, 3F, this)
        } catch (e: SecurityException) {
            Log.e(TAG, "Missing location premission")
        }
    }

    override fun onCleared() {
        //  timer.cancel()
        socket?.close()
        super.onCleared()
    }

    override fun onLocationChanged(location: Location) {
        val lat = location.latitude
        val long = location.longitude
        val alt = location.altitude
        val speed = if (location.hasSpeed()) (location.speed) * 3.6 else 0f

        landspeed.postValue(String.format("%.2f km/h", speed))
        altitude.postValue(String.format("%.2f m", alt))
        val dist =
            calcDistance(airport.latitude.toDouble(), airport.longitude.toDouble(), lat, long)
        distance.postValue(
            if (dist > 10000) String.format("%.2f km", dist / 1000)
            else String.format("%.0f m", dist)
        )
        direction.postValue(
            String.format(
                "%.0f\u00b0",
                calcDirection( lat, long, airport.latitude.toDouble(), airport.longitude.toDouble(),)
            )
        )

        //val  laltString = if(lalt >= 0) angleString(lalt)+"N" else angleString(lalt)+"S"
        //val longString = if(long >= 0) angleString(long)+"E" else angleString(long)+"W"
        /*    binding.speeds.textFieldAltitude.setText(String.format("%.2f m",alt))
            binding.speeds.textFieldSpeed.setText(String.format("%.2f km/h",speed))
            binding.speeds.textFieldAlt.setText(laltString)
            binding.speeds.textFieldLong.setText(longString)*/
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {


       // Log.d("STATSUS CHANGED", provider!! + "status ${status}")
        when (status) {
         //   LocationProvider.AVAILABLE -> Log.d("STATSUS CHANGED", "Available")
            LocationProvider.OUT_OF_SERVICE -> {

                noGps()
            }
            /*LocationProvider.TEMPORARILY_UNAVAILABLE -> Log.d(
                "STATSUS CHANGED",
                "Temporary unavailable"
            )*/
        }
    }

    private fun noGps() {
        landspeed.postValue("no GPS")
        windDirection.postValue("no GPS")
        altitude.postValue("no GPS")
    }
}


@Suppress("UNCHECKED_CAST")
class AirportDetailsViewModelFactory(
    private val airportEntity: Airport,
    private val locationManager: LocationManager,
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirportDetailsViewModel::class.java)) {
            return AirportDetailsViewModel(airportEntity, locationManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}