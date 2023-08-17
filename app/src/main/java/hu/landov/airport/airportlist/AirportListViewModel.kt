package hu.landov.airport.airportlist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.landov.airport.common.domain.airport.AirportRepository
import javax.inject.Inject

@HiltViewModel
class AirportListViewModel @Inject constructor(val repository: AirportRepository) : ViewModel() {
    val airports = repository.getAirports()
}