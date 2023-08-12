package hu.landov.airport.common.domain.location

import androidx.lifecycle.LiveData
import hu.landov.airport.common.domain.airport.Airport

interface LocationStateProvider {

    fun getLocationState(airport: Airport) : LiveData<LocationState>

}