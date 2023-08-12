package hu.landov.airport

import android.app.Application
import hu.landov.airport.common.data.RoomAirportRepository
import hu.landov.airport.common.domain.airport.AirportRepository

class AirportApplication : Application() {

    private var REPO_INSTANCE : AirportRepository? = null

    fun getAirportRepository() : AirportRepository {
        return REPO_INSTANCE ?: synchronized(this){
            val instance = RoomAirportRepository(this)
            REPO_INSTANCE = instance
            return instance
        }
    }


}