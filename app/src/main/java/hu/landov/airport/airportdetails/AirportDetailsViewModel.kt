package hu.landov.airport.airportdetails

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.landov.airport.common.domain.airport.Airport
import hu.landov.airport.common.domain.location.LocationStateProvider
import hu.landov.airport.common.domain.wind.WindState
import hu.landov.airport.common.domain.wind.WindStateProvider
import javax.inject.Inject

@HiltViewModel
class AirportDetailsViewModel @Inject constructor(
    locationStateProvider: LocationStateProvider,
    private val windStateProvider: WindStateProvider,
    state: SavedStateHandle
) : ViewModel() {

    val TAG = "AIRPORTDETAILSVIEWMODEL"

    val airport: Airport = state.get("airport") ?: throw IllegalStateException()

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
