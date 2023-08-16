package hu.landov.airport.common.di.fragments

import dagger.BindsInstance
import dagger.Subcomponent
import dagger.android.AndroidInjector
import hu.landov.airport.airportdetails.AirportDetailsFragment
import hu.landov.airport.common.di.scopes.FragmentScope

@Subcomponent(
    modules = [FragmentModule::class]
)
@FragmentScope
interface AirportDetailsFragmentSubcomponent : AndroidInjector<AirportDetailsFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<AirportDetailsFragment> {
        override fun create(@BindsInstance instance: AirportDetailsFragment): AirportDetailsFragmentSubcomponent
    }
}
