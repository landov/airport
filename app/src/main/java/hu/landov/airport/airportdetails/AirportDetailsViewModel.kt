package hu.landov.airport.airportdetails

import android.util.Log
import androidx.lifecycle.*
import hu.landov.airport.common.domain.airport.Airport
import hu.landov.airport.common.domain.location.LocationStateProvider
import hu.landov.airport.common.domain.wind.WindState
import hu.landov.airport.common.domain.wind.WindStateProvider


class AirportDetailsViewModel(
    val airport: Airport,
    private val locationStateProvider: LocationStateProvider,
    private val windStateProvider: WindStateProvider

) : ViewModel() {

    val TAG = "AIRPORTDETAILSVIEWMODEL"

    val windState: LiveData<WindState> = windStateProvider.getWindState(airport)
    val locationState = locationStateProvider.getLocationState(airport)

    init {
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