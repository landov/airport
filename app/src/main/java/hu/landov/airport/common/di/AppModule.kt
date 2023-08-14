package hu.landov.airport.common.di

import dagger.Module
import dagger.Binds
import hu.landov.airport.common.domain.wind.WindStateProvider
import hu.landov.airport.common.providers.IdokepWindStateProvider

@Module(includes = [AppModule.Bindings::class])
object AppModule {

    @Module
    interface Bindings {
        @Binds
        fun bindWindStateProvider(impl: IdokepWindStateProvider) : WindStateProvider
    }
}