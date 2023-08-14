package hu.landov.airport.common.di

import dagger.Component
import hu.landov.airport.common.domain.wind.WindStateProvider
import javax.inject.Singleton

//TODO Qualifiers and a generic interface for providers

@Component(
    modules = [
        AppModule::class,
        ContextModule::class
    ]
)
@Singleton
interface AppComponent {
    fun windStateProvider(): WindStateProvider
}