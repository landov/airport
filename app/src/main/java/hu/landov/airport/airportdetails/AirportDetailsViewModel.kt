package hu.landov.airport.airportdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.landov.airport.common.data.AirportEntity
import hu.landov.airport.common.domain.Airport

class AirportDetailsViewModel(val airport : Airport) : ViewModel() {



    init {
        Log.d("VIEWMODEL","is live with $airport")
    }
}

class AirportDetailsViewModelFactory(private val airportEntity: Airport) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirportDetailsViewModel::class.java)){
            return AirportDetailsViewModel(airportEntity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}