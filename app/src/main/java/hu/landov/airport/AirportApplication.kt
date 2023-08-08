package hu.landov.airport

import android.app.Application
import hu.landov.airport.common.data.RoomAirportRepository
import hu.landov.airport.common.domain.AirportRepository

class AirportApplication : Application() {

    val airportRepository : AirportRepository = RoomAirportRepository(this)

}