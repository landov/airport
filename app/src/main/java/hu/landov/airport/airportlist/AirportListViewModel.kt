package hu.landov.airport.airportlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import hu.landov.airport.AirportApplication

class AirportListViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = (app as AirportApplication).getAirportRepository()

    val airports = repo.getAirports()

}