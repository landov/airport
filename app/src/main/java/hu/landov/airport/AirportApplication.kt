package hu.landov.airport

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import hu.landov.airport.common.data.RoomAirportRepository
import hu.landov.airport.common.domain.AirportRepository

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