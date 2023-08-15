package hu.landov.airport.common.di

import android.app.Activity
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import hu.landov.airport.MainActivity
import hu.landov.airport.common.di.scopes.ActivityScope

@Subcomponent(modules = [ActivityModule::class])
@ActivityScope
interface ActivityComponent {

    fun fragmentComponent(): FragmentComponent

    fun inject(activity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: Activity) : ActivityComponent
    }
}