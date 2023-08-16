package hu.landov.airport

import android.app.Application
import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import hu.landov.airport.common.data.RoomAirportRepository
import hu.landov.airport.common.di.ApplicationComponent
import hu.landov.airport.common.di.DaggerApplicationComponent
import hu.landov.airport.common.domain.airport.AirportRepository
import javax.inject.Inject

class AirportApplication : Application(), HasAndroidInjector {


    //TODO this shoud be injected as well?
    private var REPO_INSTANCE: AirportRepository? = null
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent
            .factory()
            .create(this)
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any>{
        return dispatchingAndroidInjector
    }

    fun getAirportRepository(): AirportRepository {
        return REPO_INSTANCE ?: synchronized(this) {
            val instance = RoomAirportRepository(this)
            REPO_INSTANCE = instance
            return instance
        }
    }
}


