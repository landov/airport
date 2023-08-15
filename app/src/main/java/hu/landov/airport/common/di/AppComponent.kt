package hu.landov.airport.common.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import hu.landov.airport.MainActivity
import hu.landov.airport.airportdetails.AirportDetailsFragment
import hu.landov.airport.airportdetails.AirportDetailsViewModel
import hu.landov.airport.common.domain.wind.WindStateProvider
import javax.inject.Singleton

//TODO Qualifiers and a generic interface for providers

@Component(
    modules = [
        ProviderModule::class
    ]
)
@Singleton
interface AppComponent {
    //fun windStateProvider(): WindStateProvider

    fun inject(detailsFragment: AirportDetailsFragment)

    fun inject(activity: MainActivity)

    @Component.Builder
    interface  Builder {
        @BindsInstance
        fun context(context: Context) : Builder

        fun build(): AppComponent
    }
}