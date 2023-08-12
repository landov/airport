package hu.landov.airport

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import hu.landov.airport.common.data.RoomAirportRepository
import hu.landov.airport.common.di.ServiceLocator
import hu.landov.airport.common.di.ServiceLocatorImpl
import hu.landov.airport.common.domain.airport.AirportRepository

class AirportApplication : Application() {

    private var REPO_INSTANCE: AirportRepository? = null
    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()
        serviceLocator = ServiceLocatorImpl(this)
    }

    fun getAirportRepository(): AirportRepository {
        return REPO_INSTANCE ?: synchronized(this) {
            val instance = RoomAirportRepository(this)
            REPO_INSTANCE = instance
            return instance
        }
    }
}

internal fun <A : Any> AppCompatActivity.lookup(name: String): A =
    (applicationContext as AirportApplication).serviceLocator.lookUp(name)

internal fun <A : Any> AndroidViewModel.lookup(application: Application, name: String): A =
    (application as AirportApplication).serviceLocator.lookUp(name)

