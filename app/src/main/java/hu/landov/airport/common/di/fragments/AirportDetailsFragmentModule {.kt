package hu.landov.airport.common.di.fragments

import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import hu.landov.airport.airportdetails.AirportDetailsFragment

@Module(
    subcomponents = [AirportDetailsFragmentSubcomponent::class]
)
interface AirportDetailsFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(AirportDetailsFragment::class)
    fun bindAirportDetailsFragmentSubcomponentFactory(
        factory : AirportDetailsFragmentSubcomponent.Factory
    )
}