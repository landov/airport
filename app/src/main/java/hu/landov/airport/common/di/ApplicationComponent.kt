package hu.landov.airport.common.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import hu.landov.airport.AirportApplication
import hu.landov.airport.common.di.scopes.ApplicationScope

//TODO Qualifiers and a generic interface for providers

@Component(
    modules = [
        ApplicationModule::class,
        ActivityBindingModule::class
    ]
)
@ApplicationScope
interface ApplicationComponent : AndroidInjector<AirportApplication> {



    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application):
                ApplicationComponent
    }

}