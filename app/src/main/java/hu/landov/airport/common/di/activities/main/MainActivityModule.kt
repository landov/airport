package hu.landov.airport.common.di.activities.main

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import hu.landov.airport.MainActivity

@Module(
    subcomponents = [MainActivitySubcomponent::class]
)
interface MainActivityModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class) // 2
    fun bindMainActivitySubcomponentFactory(
        factory: MainActivitySubcomponent.Factory // 3
    ): AndroidInjector.Factory<MainActivity>
}