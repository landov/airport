package hu.landov.airport

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import hu.landov.airport.common.data.RoomAirportRepository
import hu.landov.airport.common.domain.airport.AirportRepository

@HiltAndroidApp
class AirportApplication : Application() {


    //TODO this shoud be injected as well?
    private var REPO_INSTANCE: AirportRepository? = null

    fun getAirportRepository(): AirportRepository {
        return REPO_INSTANCE ?: synchronized(this) {
            val instance = RoomAirportRepository(this)
            REPO_INSTANCE = instance
            return instance
        }
    }
}


