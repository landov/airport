package hu.landov.airport

import android.app.Application
import android.content.Context
import hu.landov.airport.common.data.RoomAirportRepository
import hu.landov.airport.common.di.ApplicationComponent
import hu.landov.airport.common.di.DaggerApplicationComponent
import hu.landov.airport.common.domain.airport.AirportRepository

class AirportApplication : Application() {

    lateinit var appComponent: ApplicationComponent
    //TODO this shoud be injected as well
    private var REPO_INSTANCE: AirportRepository? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent
            .factory()
            .create(this)

    }

    fun getAirportRepository(): AirportRepository {
        return REPO_INSTANCE ?: synchronized(this) {
            val instance = RoomAirportRepository(this)
            REPO_INSTANCE = instance
            return instance
        }
    }
}

val Context.appComp: ApplicationComponent
get() = (applicationContext as AirportApplication).appComponent

