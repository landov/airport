package hu.landov.airport.common.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import hu.landov.airport.airportdetails.AirportDetailsFragment
import hu.landov.airport.common.di.fragments.FragmentModule
import hu.landov.airport.common.di.scopes.FragmentScope

@Module
interface FragmentBindingModule {

    @ContributesAndroidInjector(
        modules = [
            FragmentModule::class
        ]
    )
    @FragmentScope
    fun airportDetailsFragment() : AirportDetailsFragment
}