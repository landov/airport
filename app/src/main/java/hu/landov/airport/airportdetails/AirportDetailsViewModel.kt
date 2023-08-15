package hu.landov.airport.airportdetails

import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.*
import hu.landov.airport.common.domain.airport.Airport
import hu.landov.airport.common.domain.location.LocationStateProvider
import hu.landov.airport.common.domain.wind.WindState
import hu.landov.airport.common.domain.wind.WindStateProvider
import hu.landov.airport.common.providers.GpsLocationStateProvider



class AirportDetailsViewModel(
    val airport: Airport,
    private val locationStateProvider: LocationStateProvider,
    private val windStateProvider: WindStateProvider

) : ViewModel() {

    val TAG = "AIRPORTDETAILSVIEWMODEL"

    //TODO will be injected in constructor
    //private val windStateProvider: WindStateProvider = IdokepWindStateProvider()
    //private val windStateProvider: WindStateProvider = DaggerAppComponent.create().windStateProvider()
    val windState: LiveData<WindState> = windStateProvider.getWindState(airport)

    //TODO will be injected
  //  private val locationStateProvider: LocationStateProvider =
//        GpsLocationStateProvider(locationManager)
    val locationState = locationStateProvider.getLocationState(airport)


    init {

        // windState = windStateProvider?.getWindState(airport)!!
        // windStateProvider?.start()
        Log.d(TAG, "is live with $airport \n${airport.windLink}")
    }


    override fun onCleared() {
        windStateProvider.stop()
        super.onCleared()
    }

}


@Suppress("UNCHECKED_CAST")
class AirportDetailsViewModelFactory(
    private val airportEntity: Airport,
    private val locationStateProvider: LocationStateProvider,
    private val windStateProvider: WindStateProvider
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AirportDetailsViewModel::class.java)) {
            return AirportDetailsViewModel(
                airport = airportEntity,
                locationStateProvider = locationStateProvider,
                windStateProvider = windStateProvider
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}