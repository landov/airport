package hu.landov.airport.common.di.activities.main

import dagger.Subcomponent
import dagger.android.AndroidInjector
import hu.landov.airport.MainActivity
import hu.landov.airport.airportdetails.AirportDetailsFragment
import hu.landov.airport.common.di.scopes.ActivityScope

@Subcomponent(
    modules = [
        ActivityModule::class,
        AirportDetailsFragment::class
    ]
)
@ActivityScope
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainActivity>

}