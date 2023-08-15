package hu.landov.airport.common.di

import dagger.Subcomponent
import hu.landov.airport.airportdetails.AirportDetailsFragment
import hu.landov.airport.common.di.scopes.FragmentScope

@Subcomponent(modules = [FragmentModule::class])
@FragmentScope
interface FragmentComponent {

    fun inject(fragment: AirportDetailsFragment)

}