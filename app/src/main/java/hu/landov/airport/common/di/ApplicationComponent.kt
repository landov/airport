package hu.landov.airport.common.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import hu.landov.airport.AirportApplication
import hu.landov.airport.MainActivity
import hu.landov.airport.airportdetails.AirportDetailsFragment
import hu.landov.airport.common.di.activities.main.MainActivityModule
import hu.landov.airport.common.di.scopes.ApplicationScope
import hu.landov.airport.common.location.GeoLocationPermissionChecker
import hu.landov.airport.common.location.GeoLocationPermissionCheckerImpl
import javax.inject.Singleton

//TODO Qualifiers and a generic interface for providers

@Component(
    modules = [
        ApplicationModule::class,
        MainActivityModule::class
    ]
)
@ApplicationScope
interface ApplicationComponent {

    fun inject(application: AirportApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application):
                ApplicationComponent
    }

}