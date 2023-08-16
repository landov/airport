package hu.landov.airport.common.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import hu.landov.airport.MainActivity
import hu.landov.airport.common.di.activities.ActivityModule
import hu.landov.airport.common.di.scopes.ActivityScope

@Module
interface ActivityBindingModule {

    @ContributesAndroidInjector(
        modules = [
            ActivityModule::class,
            FragmentBindingModule::class
        ]
    )
    @ActivityScope
    fun mainActivity(): MainActivity
}