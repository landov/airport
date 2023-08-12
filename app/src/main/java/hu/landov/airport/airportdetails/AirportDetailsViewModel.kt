package hu.landov.airport.airportdetails

import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.*
import hu.landov.airport.common.domain.airport.Airport
import hu.landov.airport.common.domain.location.LocationStateProvider
import hu.landov.airport.common.domain.wind.WindStateProvider
import hu.landov.airport.common.providers.GpsLocationStateProvider
import hu.landov.airport.common.providers.IdokepWindStateProvider


class AirportDetailsViewModel(
    val airport: Airport,
    locationManager: LocationManager,
) : ViewModel() {

    val TAG = "AIRPORTDETAILSVIEWMODEL"

    //TODO will be injected in constructor
    private val windStateProvider: WindStateProvider = IdokepWindStateProvider()
    val windState = windStateProvider.getWindState(airport)

    //TODO will be injected
    private val locationStateProvider : LocationStateProvider = GpsLocationStateProvider(locationManager)
    val locationState = locationStateProvider.getLocationState(airport)


    init {
        //TODO ????
        windStateProvider.start()
        Log.d(TAG, "is live with $airport \n${airport.windLink}")

    }

    fun onStart() {
        Log.d(TAG, "Starting..")
        windStateProvider.start()
    }

    fun onStop() {
        Log.d(TAG, "Stopping..")
        windStateProvider.stop()
    }

    override fun onCleared() {
        windStateProvider.stop()
        super.onCleared()
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